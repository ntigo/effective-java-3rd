## 인덱스

```
데이터를 정의한 기준에 따라 정렬하는 것.
데이터의 위치를 빠르게 찾게 도와줌으로 성능을 향상시킨다.
```

---

## 인덱스의 종류

<table>
    <tr>
        <td>스토리지</td>
        <td>인덱스 유형</td>
    </tr>
    <tr>
        <td rowspan="4">rowstore</td>
        <td>클러스터</td>
    </tr>
    <tr>
        <td>비 클러스터</td>
    </tr>
    <tr>
        <td>유니크</td>
    </tr>
    <tr>
        <td>Filtered</td>
    </tr>
    <tr>
        <td rowspan="2">columnstore</td>
        <td>클러스터</td>
    </tr>
    <tr>
        <td>비 클러스터</td>
    </tr>
    <tr>
        <td rowspan="2">메모리 최적화</td>
        <td>Hash</td>
    </tr>
    <tr>
        <td>비 클러스터</td>
    </tr>
</table>

---

## 클러스터 인덱스

```
데이터를 물리적으로 정렬한 인덱스. 물리적으로 정렬하기 때문에 테이블은 클러스터 인덱스를 한개만 생성할 수 있다.

클러스터 인덱스로 검색하게 되면 모든 컬럼의 데이터를 조회할 수 있음으로 key lookup( 랜덤 엑세스 )이 발생하지 않아  
모든 컬럼에 대해 검색하는 쿼리에서도 성능이 떨어지지 않는다.
( 클러스터 인덱스가 없는 힙 테이블은 rid lookup 발생 )

기본키를 클러스터 인덱스로 생성한다면 join 검색등에서 해당 컬럼이 자연스럽게 조건에 적용됨으로
key lookup이 발생하지 않게 되어 좋은 성능을 기대할 수 있다.
```

<details>
<summary>[ 쿼리 ]</summary>

-- KEY LOOKUP  
SELECT * FROM [ORDER] WHERE ORDER_NUMBER = '202303260200000';  
-- RID LOOKUP ( HEAP_TABLE )  
SELECT * FROM ORDER_HEAP_TABLE WHERE ORDER_NUMBER = '202303260010836';  
SELECT * FROM ORDER_NORMAL WHERE ORDER_NUMBER = '202303260010836';
</details>

---

```
MSSQL 에서는 기본키가 아닌 컬럼을 클러스터 인덱스로 지정할 수 있음으로
범위 검색에 강점을 두기 위해 기본키가 아닌 컬럼을 클러스터 인덱스로 지정하는 것을 고려할 수 있다.
( 하지만 대부분은 이것이 좋은 방법은 아님을 명심 )

만약 이렇게 진행하는 경우가 있다면 기본키는 유니크한 비 클러스터 인덱스로 생성하는 것이 좋다.
```

<details>
<summary>[ 쿼리 ]</summary>

-- 상세 내용은 SSMS 확인
SET STATISTICS PROFILE OFF
EXEC SP_HELPINDEX 'ORDER'
</details>

---

```
클러스터 인덱스는 선택된 컬럼으로 데이터가 유니크해야 한다. 그렇지 않으면 자동으로 4 byte 크기의 식별 데이터가 추가됨으로 모든 인덱스의 크기가 커진다.
비 클러스터 인덱스 리프 레벨에는 클러스터 인덱스로 지정한 컬럼의 값이 같이 저장됨으로 클러스터 인덱스가 용량이 작을 수록 비 클러스터 인덱스 성능에 좋다.  
```

<details>
<summary>[ 쿼리 ]</summary>

-- 테이블에 클러스터 인덱스로 지정된 컬럼의 중복 데이터 확인
SELECT COUNT( 1 ) FROM [ORDER]
SELECT COUNT( 1 ) FROM ORDER_CLUSTER_OVERLAP

SELECT COUNT( 1 ) FROM ( SELECT ORDER_DATE FROM [ORDER] GROUP BY ORDER_DATE ) AS RESULT
SELECT COUNT( 1 ) FROM ( SELECT ORDER_DATE FROM ORDER_CLUSTER_OVERLAP GROUP BY ORDER_DATE ) AS RESULT

-- 두 테이블 인덱스 체크
SET STATISTICS PROFILE OFF
EXEC SP_HELPINDEX 'ORDER'
EXEC SP_HELPINDEX 'ORDER_CLUSTER_OVERLAP'

-- 테이블 용량 확인
DBCC Ind( 'RODONG', 'ORDER', 1 )
DBCC Ind( 'RODONG', 'ORDER_CLUSTER_OVERLAP', 1 )

-- 클러스터 인덱스가 중복된 데이터라면 용량이 커지고 성능에도 좋지 않다.
SET STATISTICS PROFILE ON
SET STATISTICS IO ON
SET STATISTICS TIME ON

SELECT * FROM [ORDER] WHERE ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-03-27 00:00:00'
SELECT * FROM ORDER_CLUSTER_OVERLAP WHERE ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-03-26 07:43:30'
</details>

<details>
<summary>[ 왜 용량이 커지는가? ]</summary>

출처 : http://www.btechsmartclass.com/data_structures/b-trees.html  
![b-tree](./B-Tree_Example.jpg)
</details>

---

## 힙 테이블

```
클러스터 인덱스가 없는 테이블, 클러스터 인덱스가 없음으로 정렬에 기준이 없어 insert 하는 순서대로 데이터가 저장됨.
이때 저장되는 영역을 힙(Heap)이라고 함.
```

```
insert, update등의 처리에서 정렬이 발생하지 않으므로 일반적인 경우 속도가 빠름.  
select 처리에서는 데이터가 정렬되어 있지 않아 성능이 일반적으로 느림.
```

```
비 클러스터 인덱스로 검색 속도를 향상 시킬 수 있지만 용량이 커지거나 데이터의 업데이트가 많아지는 경우  
데이터가 다른 페이지(리프 레벨)로 이동하는 과정에서 RID를 추적하는 데이터의 기록이 추가되어 힙이 조각화되는 문제로 성능이 나빠질 가능성이 높다.
```  

```
힙 테이블은 비 클러스터 인덱스로 검색할 때 key lookup 이 아닌 rid lookup 으로 데이터가 검색된다.
대부분의 경우에 클러스터 인덱스가 있는 테이블에 비해 좋지 않음으로 힙 테이블은 사용하지 않는것이 좋다.
```

```
RID는 행 식별자, 데이터의 물리적 주소를 가르키는 값이다.  
RID 구성 -> FileID:PageID:SlotID  
RID 예시 -> 4:7029:0
```

<details>
<summary>[ 쿼리 ]</summary>

-- 힙 테이블은 테이블 스캔
SELECT TOP 10 * FROM ORDER_HEAP_TABLE;
-- 클러스터 인덱스가 있는 테이블은 클러스터 인덱스 스캔
SELECT TOP 10 * FROM [ORDER]
</details>

---

## 비 클러스터

```
데이터를 논리적으로 정렬한 인덱스. 논리적으로 정렬하기 때문에 테이블은 여러개의 비 클러스터 인덱스를 생성할 수 있다.  
논리적으로 정렬했다고 하여 디스크에 저장되지 않는다는것은 아니다. 비 클러스터 인덱스로 정렬된 데이터도 디스크에 저장된다.

비 클러스터 인덱스로 검색할 때, 비 클러스터 인덱스에 포함되지 않은 열을 검색하게되면
인덱스의 데이터 페이지( 리프 레벨 )에 검색하고자 하는 열의 값이 존재하지 않기 때문에
클러스터 인덱스를 통해 해당 열을 가지고 있는 행에 대해서 검색을 시도한다.  

이 과정을 key lookup 이라고 한다. key lookup 이 발생하는 쿼리의 경우 검색된 데이터 개수가 많아지면
그 개수만큼 key lookup 검색을 시도함으로 엄청난 부하를 발생시킬 수 있다.
( 다른 DB에서는 이것을 랜덤 액세스라고 한다. )
```

<details>
<summary>[ 쿼리 ]</summary>

-- key lookup이 발생하는 쿼리, 적은 범위의 데이터를 검색하지만 굉장히 성능에 좋지 않다.
SELECT
*
FROM ORDER_NORMAL AS ONL
INNER JOIN [ORDER] AS OD ON OD.ORDER_NUMBER = ONL.ORDER_NUMBER
WHERE
ONL.ORDER_DATE >= '2023-03-26 00:00:00' AND ONL.ORDER_DATE < '2023-03-26 00:07:00'

-- key lookup이 발생하지 않는 쿼리, 성능면에서 차이가 많이 난다.
SELECT
ONL.ORDER_DATE
,	OD.ORDER_NUMBER
FROM ORDER_NORMAL AS ONL
INNER JOIN [ORDER] AS OD ON OD.ORDER_NUMBER = ONL.ORDER_NUMBER
WHERE
ONL.ORDER_DATE >= '2023-03-26 00:00:00' AND ONL.ORDER_DATE < '2023-03-26 00:07:00'
</details>

---

```
비 클러스터 인덱스의 검색에서 key lookup 을 발생시키지 않게 하기 위해서는 비 클러스터 인덱스에 포괄 열을 추가해야된다.
포괄 열을 추가하면 검색에서 좋은 성능을 낼 수 있으나 인덱스 유지 보수 비용이 커져
검색 외 다른 처리( insert, update, delete )에서는 오히려 성능이 떨어지게 된다.  

따라서 인덱스 유지 보수 비용보다 검색 비용이 높다고 판단되는 경우 포괄 열에 대한 적용을 검토해야한다.
포괄 열을 추가하여 비 클러스터 인덱스를 만드는 경우 비 클러스터 데이터 페이지( 리프 레벨 )에 포괄 열로 지정한 열의 값이 저장되어 key lookup이 발생하지 않는다.
```

<details>
<summary>[ 쿼리 ]</summary>

-- 비 클러스터에 포괄 열(INCLUDE)을 추가하여 key lookup이 발생하지 않는 쿼리
-- 검색 범위가 넓어지면 풀 스캔할 수도 있다...!
SELECT
ONL.*
,	OD.ORDER_NUMBER
FROM ORDER_NORMAL_INCLUDE_COLUMN AS ONL
INNER JOIN [ORDER] AS OD ON OD.ORDER_NUMBER = ONL.ORDER_NUMBER
WHERE
-- 범위를 좀더 늘리면 비 클러스터 풀 스캔으로 변경된다.
ONL.ORDER_DATE >= '2023-03-26 00:00:00' AND ONL.ORDER_DATE < '2023-03-26 00:07:00'

SELECT
ONL.*
,	OD.ORDER_NUMBER
,	OD.SALES_COMPANY_CODE
FROM ORDER_NORMAL_INCLUDE_COLUMN AS ONL
INNER JOIN [ORDER] AS OD ON OD.ORDER_NUMBER = ONL.ORDER_NUMBER
WHERE
-- KEY LOOKUP을 발생시킨 상태에서 범위를 좀더 늘리면 클러스터 풀 스캔으로 변경된다
ONL.ORDER_DATE >= '2023-03-26 00:00:00' AND ONL.ORDER_DATE < '2023-03-26 00:10:00'
</details>

---

## 선택도, 카디널리티

```
비 클러스터 인덱스를 설계할 때는 지정하려는 컬럼들의 조합으로 카디널리티, 선택도 값을 예상한 후 생성하는것이 좋다.  

카디널리티는 지정하려는 컬럼들의 조합에서 유니크한 데이터의 개수를 말한다.
카디널리티 = 유니크한 데이터의 개수

선택도는 아래 공식으로 계산된다.  
선택도 = 카디널리티 / 총 데이터 행 개수

서울, 경기, 대전, 대구에 각기 다른 홍길동이 살고 이것을 지역, 이름이라는 컬럼에 저장되어있다고 가정한다. 

| 지역 | 이름  |
| 서울 | 홍길동 |
| 경기 | 홍길동 |
| 대전 | 홍길동 |
| 대구 | 홍길동 |

지역, 이름 2개의 조합으로 비 클러스터 인덱스를 만들면 아래와 같이 계산된다.
(T)총 데이터 수 : 4, (C)카디널리티 : 4, (S)선택도 : 1
S = C / T
1 = 4 / 4

이름 1개의 조합으로 비 클러스터 인덱스를 만들면 아래와 같이 계산된다.
(T)총 데이터 수 : 4, (C)카디널리티 : 1, (S)선택도 : 0.25
S = C / T
0.25 = 1 / 4

선택도는 1에 가까울수록 좋다. 따라서 위 케이스는 첫번째 케이스로 비 클러스터 인덱스를 생성해야한다.
NULL 로 저장된 컬럼은 모두 같은 값으로 보기 때문에 NULL 로 저장된 데이터는 모두 합산해서 카디널리티를 1로 보면된다. 
```

---

## 유니크 인덱스

```
비 클러스터 인덱스와 동일하지만 유니크 인덱스로 지정해서 얻는 이점이 있다.
실혱 계획을 생성할 때 해당 인덱스의 선택도가 1이라는것을 알고 있는 상태에서 실행 계획을 생성하기 때문에 좋은 실행 계획이 적용될 가능성이 높아진다.

또한 유니크 인덱스 생성 시 적용된 컬럼들은 무결성이 보장된다....!  
```

---

## 필터링된 인덱스

```
비 클러스터 인덱스 생성 시 특정 조건의 데이터만으로 인덱스를 만드는 방식이다.
중복이 많은 데이터를 제외하고 인덱스를 생성하여 효율을 높일 수 있다. 예를 들면 NULL인 데이터를 제외하고 만드는 방식이 있다.
```

<details>
<summary>[ 쿼리 ]</summary>

-- 비 클러스터 인덱스에 필터를 걸게되면 인덱스가 작아지고 속도가 빨라진다.
SELECT SALES_COMPANY_CODE, BRAND_CODE, ORDER_DATE FROM ORDER_BRAND_CODE_FILTER WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' AND ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-04-01 00:00:00'
SELECT SALES_COMPANY_CODE, BRAND_CODE, ORDER_DATE FROM ORDER_BRAND_CODE_NO_FILTER WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' AND ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-04-01 00:00:00'
</details>

---

## 복합 인덱스의 컬럼 순서

```
여러개의 컬럼으로 인덱스를 생성하는 경우 지정한 컬럼 순서대로 정렬하게된다.
검색하는 조건이 해당 컬럼 순서와 맞지 않으면 해당 인덱스는 사용되지 않는다.

주문 정보 테이블에서 회사 코드, 브랜드 코드, 상품 코드 순서로 인덱스를 생성했다고 가정했을 때
브랜드 코드, 상품 코드, 2가지만 입력하여 검색하게 되면 회사 코드가 없어 생성한 인덱스는 아예 사용되지 않거나 비 효율적으로 사용된다.

범위 검색을 목적으로 한다면 단일 컬럼으로 봤을 때 선택도가 낮은것을 순서대로 적용하는것이 좋다.
주문 정보 테이블 -> 회사 코드, 브랜드 코드, 상품 코드, 구매 일자 

단건 검색을 목적으로 한다면 단일 컬럼으로 봤을 때 선택도가 높은것을 순서대로 적용하는것이 좋다.
유저 ID, 상품 코드, 브랜드 코드, 회사 코드

단, 위와 같은 경우에서 더 적은 컬럼으로 데이터가 유니크한 경우가 있다면 필요없는 컬럼은 제외한다.
예) 주문 번호가 유니크하면 주문 번호 외 다른 컬럼은 모두 제외
```

<details>
<summary>[ 쿼리 ]</summary>

-- 범위 검색 : 선택도가 낮은 컬럼 순서
SELECT SALES_COMPANY_CODE, BRAND_CODE, ORDER_DATE FROM ORDER_BRAND_CODE_FILTER WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' AND ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-03-27 00:00:00' ORDER BY ORDER_DATE ASC

-- 범위 검색 : 선택도가 높은 컬럼 순서
SELECT SALES_COMPANY_CODE, BRAND_CODE FROM ORDER_BRAND_CODE_OPPOSITE WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' AND ORDER_DATE >= '2023-03-26 00:00:00' AND ORDER_DATE < '2023-03-27 00:00:00' ORDER BY ORDER_DATE ASC


-- 단일 검색 : 선택도가 높은 순서
SELECT [USER_ID], [GOODS_CODE], [BRAND_CODE], [SALES_COMPANY_CODE] FROM ORDER_USER_ID WHERE [USER_ID] = 'alsfkqqpv'

-- 단일 검색 : 선택도가 낮은 순서
SELECT [USER_ID], [GOODS_CODE], [BRAND_CODE], [SALES_COMPANY_CODE] FROM ORDER_USER_ID_OPPOSITE WHERE SALES_COMPANY_CODE = 'COUPANG'

-- 단일 검색 : 선택도가 높은 순서, 인덱스 컬럼 모두 적용
SELECT [USER_ID], [GOODS_CODE], [BRAND_CODE], [SALES_COMPANY_CODE] FROM ORDER_USER_ID WHERE [USER_ID] = 'alsfkqqpv' AND SALES_COMPANY_CODE = 'COUPANG' AND BRAND_CODE = 'ASROCK' AND GOODS_CODE = '000212'

-- 단일 검색 : 선택도가 낮은 순서, 인덱스 컬럼 모두 적용
SELECT [USER_ID], [GOODS_CODE], [BRAND_CODE], [SALES_COMPANY_CODE] FROM ORDER_USER_ID_OPPOSITE WHERE [USER_ID] = 'alsfkqqpv' AND SALES_COMPANY_CODE = 'COUPANG' AND BRAND_CODE = 'ASROCK' AND GOODS_CODE = '000212'
</details>

---

## 정렬된 인덱스

```
데이터를 검색 할때 정렬 할 때 ( ORDER BY ) 인덱스 마지막 컬럼이 아니라면 sort 비용이 발생하게 된다.  
또한 해당 인덱스를 사용하지 않을 위험 또한 존재한다.

유니크하지 않은 데이터를 인덱스로 생성했을 때 해당 데이터에 순서를 지정할 수 있는 컬럼이 있다면 인덱스의 마지막 컬럼으로 추가한다.
sort 비용을 제거할 수 있고 범위 검색 시 유용할 수 있다.

주로 ID(identity)값이나 날짜등이 사용된다.

예) 쿠폰 사용 테이블 : 브랜드 코드, 쿠폰 번호, 사용/취소 일자 ( 정렬...! )
예) 쿠폰 발급 테이블 : 브랜드 코드, 쿠폰 번호, 발급 일자 ( 정렬...! )
```

<details>
<summary>[ 쿼리 ]</summary>

-- 비 클러스터로 지정한 컬럼이 유니크하지 않을 때 정렬이 가능한 적절한 컬럼이 있다면 추가하는것이 좋다.
SELECT SALES_COMPANY_CODE, BRAND_CODE, ORDER_DATE FROM ORDER_BRAND_CODE_FILTER WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' ORDER BY ORDER_DATE ASC

-- 인덱스에 ORDER_DATE가 없는 경우 sort 비용이 발생하고, 잘못되면 인덱스가 사용되지 않는다.
SELECT SALES_COMPANY_CODE, BRAND_CODE FROM ORDER_BRAND_CODE_NO_SORT WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE'
SELECT SALES_COMPANY_CODE, BRAND_CODE FROM ORDER_BRAND_CODE_NO_SORT WHERE SALES_COMPANY_CODE = 'KAKAO' AND BRAND_CODE = 'INNISFREE' ORDER BY ORDER_DATE ASC
</details>