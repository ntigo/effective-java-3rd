# 1. 트랜잭션(Transaction)이란 ?
트랜잭션은 데이터베이스의 상태를 변환시키는 하나의 논리적 기능을 수행하기 위한 작업의 단위 또는 한꺼번에 수행되어야할 일련의 연산들을 의미한다.

트랜잭션은 작업의 완전성을 보장해준다. 즉, 논리적인 작업 셋을 모두 완벽하게 처리하거나 또는 처리하지 못할 경우에는 원 상태로 복구해서 작업의 일부만 적용되는 현상이 발생하지 않게 만들어주는 기능이다. 사용자의 입장에서는 작업의 논리적 단위로 이해를 할 수 있고 시스템의 입장에서는 데이터들을 접근 또는 변경하는 프로그램의 단위가 된다.

트랜잭션은 `SELECT`, `UPDATE`, `INSERT`, `DELETE`와 같은 연산을 수행하여 데이터베이스의 상태를 변화시키는 작업의 단위다.

## 1.1. 트랜잭션의 특징(ACID)
### 1.1.1. Atomicity(원자성)
* 트랜잭션이 데이터베이스에 모두 반영되던지, 아니면 전혀 반영 되지 않아야 한다.
트랜잭션 내의 모든 명령은 반드시 완벽히 수행되어야 하며, 모두가 완벽히 수행되지 않고 어느 하나라도 오류가 발생하면 트랜잭션 전부가 취소되어야 한다.
### 1.1.2. Consistency(일관성)
* 트랜잭션의 작업 처리 결과가 항상 일관성이 있어야 한다.
시스템이 가지고 있는 고정요소는 트랜잭션 수행 전과 수행 완료 후의 상태가 같아야 한다.
### 1.1.3. Isolation(독립성)
* 둘 이상의 트랜잭션이 동시에 실행되고 있을 경우 어떤 하나의 트랜잭션이라도 다른 트랜잭션의 연산에 끼어들수 없다.
수행 중인 트랜잭션은 완전히 완료될 때까지 다른 트랜잭션에서 수행 결과를 참조 할 수 없다.
### 1.1.4. Durability(지속성)
* 트랜잭션이 성공적으로 완료되었을 경우, 결과는 영구적으로 반영되어야 한다.

## 1.2. 트랜잭션의 Commit 과 Rollback
### 1.2.1. Commit
* commit 연산은 하나의 트랜잭션이 성공적으로 끝났고, 데이터베이스가 일관성있는 상태에 있을 때 하나의 트랜잭션이 끝났음을 알려주기 위해 사용하는 연산이다.

### 1.2.2. Rollback
* Rollback 연산은 하나의 트랜잭션 처리가 비정상적으로 종료되어 데이터베이스의 일관성을 깨뜨렸을 때, 이 트랜잭션의 일부가 정상적으로 처리되었더라도 트랜잭션의 원자성을 구현하기 위해 이 트랜잭션이 행한 모든 연산을 취소(Undo)하는 연산이다.

## 1.3. 트랜잭션의 상태
* 활동(`Active`) : 트랜잭션의 활동 상태. 트랜잭션이 실행중이며 동작중인 상태를 말한다.
* 부분완료(`Partially Committed`) : 트랜잭션의 Commit 명령이 도착한 상태. 트랜잭션의 commit 이전 sql문이 수행되고 commit만 남은 상태를 말한다.
* 완료(`Committed`) : 트랜잭션 완료 상태. 트랜잭션이 정상적으로 완료된 상태를 말한다.
* 실패(`Failed`) : 트랜잭션 실패 상태. 트랜잭션이 더 이상 정상적으로 진행될 수 없는 상태를 말한다.
* 취소(`Aborted`) : 트랜잭션 취소 상태. 트랜잭션이 취소되고 트랜잭션 실행 이전 데이터로 돌아간 상태를 말한다.

## 1.4. 부분완료와 완료의 차이점
commit요청이 들어오면 상태는 부분완료 상태가 된다. 이후 commit을 문제없이 수행 할 수 있다면 완료 상태로 전이되고, 만약 오류가 발생하면 실패 상태가 된다. 즉, 부분완료는 commit요청이 들어왔을 때를 말하며, 완료 상태는 commit을 정상적으로 완료한 상태를 말한다.


## 1.5. 트랜잭션을 사용할 때 주의할 점
트랜잭션은 꼭 필요한 최소의 코드에만 적용하는 것이 좋다. 즉 트랜잭션의 범위를 최소화하라는 의미다. 일반적으로 데이터베이스 커넥션은 개수가 제한적이다. 그런데 각 단위 프로그램이 커넥션을 소유하는 시간이 길어진다면 사용 가능한 여유 커넥션의 개수는 줄어들게 된다. 그러다 어느 순간에는 각 단위 프로그램에서 커넥션을 가져가기 위해 기다려야 하는 상황이 발생할 수도 있는 것이다

# 2. 트랜잭션의 격리 수준(Isolation Level) 이란?
트랜잭션의 격리 수준이란 트랜잭션들끼리 일관성 있는 데이터를 얼마나 허용할 것인지 정해놓은 수준이다.  즉, 트랜잭션 수행 중 다른 트랜잭션이 해당 데이터를 조회하는 것이 가능한 정도를 결정해 놓은 것이다. 격리 수준이 높을수록 일관성은 보장되지만 그만큼 동시성이 떨어져 성능이 하락한다.

## 2.1. 트랜잭션 이상 현상의 종류
* `Dirty Read` : 어떤 트랜잭션에서 아직 실행이 끝나지 않은 트랜잭션에 의한 변경사항을 보게 되는 경우
* `Non Repeatable Read` : 어떤 트랜잭션이 같은 쿼리를 2번 실행하는데 그 사이에 다른 트랜잭션이 수정/삭제를 하여 같은 쿼리에 다른 값이 나오는 경우
* `Phantom Read` : 어떤 트랜잭션이 같은 쿼리를 2번 실행하는데 그 사이에 없던 레코드가 추가되어 같은 쿼리에 다른 값이 나오는 경우

### 2.1.1. 선행 지식
* S Lock: 공유 잠금(Shared Lock)<br>
읽기 잠금(Read lock)이라고도 불린다. 데이터를 주로 읽어올때 주로 걸리는 Lock이다.

* X Lock: 베타적 잠금(Exclusive Lock)<br>
쓰기 잠금(Write lock)이라고도 불린다. 데이터를 입력/수정/삭제 할때 걸리는 Lock이다. X Lock의 경우 혼자만 잠글 수 있기 때문에 다른 어떤 Lock랑도 호환되지 않는다. 하지만 S Lock의 경우 다른 S Lock과 호환이 되기 때문에 서로 공유 가능하다.


## 2.2. 트랜잭션 격리 수준의 종류
### 2.2.1. READ uncommitted (LEVEL 0)
각 트랜잭션에서의 변경 내용이 COMMIT이나 ROLLBACK여부에 상관없이 다른 트랜잭션에서 값을 읽을 수 있다.
정합성에 문제가 많은 격리 수준이므로 사용하지 않는 것이 좋다.
이때 Dirty Read, Non Repeatable Read, Phantom Read 문제가 모두 발생할 수 있다. 
아래 예제를 통해 살펴보자

1번 세션
```sql
BEGIN TRAN
UPDATE TB_MAIN SET NAME = '박'
```

2번 세션
```sql
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
SELECT * FROM TB_MAIN WITH(NOLOCK)

-- 결과

IDX         NAME
----------- --------------------------------------------------
1           박
2           박
3           박
4           박
5           박
6           박
7           박
8           박
9           박
10          박
11          박
12          박
13          박
14          박
```



데이터를 SELECT 할때 S Lock으로 잠근다고 위에서 설명을 했는데 `READ uncommitted`의 경우 'S Lock' 조차 걸지 않기 때문에  다른 세션에서 커밋되지 않은 수정하는(`X Lock`) 데이터를 볼수 있다.<br>
결과를 확인 시 1번 세션에서 커밋을 하지 않았는데 2번 세션에서 박으로 업데이트 된 값을 볼수 있다 이러한 케이스를 `Dirty Read`라 한다

### 2.2.2. READ committed (LEVEL 1)
트랜잭션 수행이 완료되고 COMMIT 된 데이터만 다른 트랜잭션에서 READ 하도록 허용하는 수준이다. 일반적으로 DBMS에서 기본으로 설정하는 레벨이다. READ UNCOMMITTED와 다른 점은 실제 SELECT 할때 `S Lock`을 소유 한다 하지만 SELECT 가 끝나는 동시에 `S Lock`을 해제 한다 아래 예제를 통해 살펴 보자

1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
SELECT * FROM TB_MAIN
GO
-- 트랜잭션이 커밋되지 않았지만 SELECT로 이미 결과를 반환 (S LOCK 해제 상태)

-- 결과
IDX         NAME
----------- --------------------------------------------------
1           가
2           나
3           다
4           라
5           마
6           바
7           사
8           아
9           자
10          차
11          카
12          타
13          파
14          하
```

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
UPDATE TB_MAIN_IDX SET
	NAME = '배'
WHERE IDX = 1
COMMIT --수정한 데이터를 커밋(1번 세션에서 S LOCK이 해제 되어서 UPDATE가능)


--다시 1번 세션 전환 후 
SET TRANSACTION ISOLATION LEVEL READ COMMITTED
SELECT * FROM TB_MAIN_IDX WHERE IDX = 1
GO

-- 결과
IDX         NAME
----------- --------------------------------------------------
1           배

```
만약 SELECT 하더라도 COMMIT 되기 전까지 `S Lock`을 소유 한다AUS다른 세션에선 업데이트가 불가능 하다 (사실 이게 `REPEATABLE READ` 이다 뒤에서 설명 예정)<br>

다음 예제로는 `SELECT`가 아닌 `UPDATE`에선 어떻게 다른지 살펴보자

1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
UPDATE TB_MAIN_IDX SET
	NAME = '가'
WHERE IDX = 1
GO
```

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
SELECT * FROM TB_MAIN_IDX WHERE IDX = 1
-- 결과 : 대기 상태....................
```
READ COMMITTED의 경우 COMMIT된 데이터만 읽기가 가능 하다. 1번 세션에선 아직 COMMIT을 하지 않았기 때문에 2번세션에선 IDX = 1의 데이터를 읽지 못하고 1번 세션의 커밋을 기다리게 된다. 그렇다면 2번 세션에서 아래와 같은 예제를 실행하면 어떻게 되는지 알아보자

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
SELECT * FROM TB_MAIN_IDX WHERE IDX = 2

-- 결과
IDX         NAME
----------- --------------------------------------------------
2           나
```

1번 세션에선 수정으로 인해 IDX = 1 ROW를 X LOCK 을 걸은 상태이기 때문에 다른 컬럼 조회시 문제가 없다 그렇다면 아래도 실행 가능한지 확인해보자 

```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
UPDATE TB_MAIN_IDX SET
	NAME = '가'
WHERE IDX = 2
ROLLBACK 
GO
```
IDX = 1에게만 X LOCK을 걸은 상태라서 정상적으로 동작한다. 해당 테이블은 기본키와 인덱스가 있는 테이블이다 인덱스가 없는 테이블에서는 어떻게 될까?

1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
UPDATE TB_MAIN SET
	NAME = '가'
WHERE IDX = 1
GO
```

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
SELECT * FROM TB_MAIN WHERE IDX = 2
-- 결과 : 대기 상태....................
```
테이블에 인덱스가 없기 때문에 범위를 알 수 없어서 모두 LOCK이 걸린다.

다음은 조회 시 `Non Repeatable Read` 나오는 경우를 살펴 보자
1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL READ COMMITTED
BEGIN TRAN
UPDATE TB_MAIN_IDX SET
	NAME = '배'
WHERE IDX = 1
GO
```

2번 세션
```sql
SELECT * FROM TB_MAIN_IDX WITH(NOLOCK) WHERE IDX = 1
-- 결과
IDX         NAME
----------- --------------------------------------------------
1           배
```

결과를 조회 시 현재 NAME 컬럼이 `베`로 업데이트 된 데이터를 받아 온다 WITH(NOLOCK)의 격리 수준은 (`ISOLATION LEVEL`)의 `READ UNCOMMITED`와 같다고 보면 된다. 즉 IDX = 1이 작업중이여도 기다리지 않고 조회 (`DIRTY READ`)하겠다는 의미와 같다. 즉 사용자가 롤백 되기전 데이터를 조회 한다면 `배`라는 업데이트 된 데이터로 조회가 되는데 롤백이 되는 순간 `가`로 돌아 간다 따라서 `Non Repeatable Read` 문제가 발생한다

### 2.2.3. REPEATABLE READ (LEVEL 2)
이론상 특정 트랜잭션에서 읽고 있는 데이터는 다른 트랜잭션에서 수정/삭제가 불가능하다. 삽입(INSERT)은 가능하다. 실제로 동작하는 방식은 자신의 트랜잭션 번호(ID) 보다 낮은 트랜잭션 번호에서 변경된(Commit 된) 것만 읽게 되고, 자신의 트랜잭션 번호보다 높은 트랜잭션에서 변경된 것은 UNDO 영역에 백업된 레코드를 읽게 된다. UNDO 영역에 백업된 레코드가 많아지면 성능이 떨어질 수 있다.
트랜잭션내에서 읽은 녀석은 다른 세션에서 변경이 불가능 하지만 `S LOCK`이기때문에 읽는 것은 가능하다. 아래 예제 쿼리를 통해 살펴보자


1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN
SELECT * FROM TB_MAIN_IDX
GO

--결과
IDX         NAME
----------- --------------------------------------------------
1           가
2           가
3           다
4           라
5           마
6           바
7           사
8           아
9           자
10          차
11          카
12          타
13          파
14          하
--트랜잭션이 커밋되지 않았지만 S Lock은 계속 유지된다.
```

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

BEGIN TRAN
UPDATE TB_MAIN_IDX
	SET NAME = '가'
WHERE IDX = 7

--결과
---------- 대기 상태
```
이런 경우 1번세션에서 이미 S Lock을 걸고 있기 때문에 수정 할 수가 없다. 하지만 읽는것은 가능하다.

2번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN
SELECT * FROM TB_MAIN_IDX
GO

--결과
IDX         NAME
----------- --------------------------------------------------
1           가
2           가
3           다
4           라
5           마
6           바
7           사
8           아
9           자
10          차
11          카
12          타
13          파
14          하

--읽는건 정상적으로 가능하다 S Lock은 S Lock 끼리 공유가 가능하다.
```

그럼 다음 예제를 위하여 1, 5만 제외 하고 지워보자.
```sql
--2번 세션
IF(@@TRANCOUNT > 0) ROLLBACK -- 혹시 모를 롤백 처리
GO

--1번 세션
IF(@@TRANCOUNT > 0) ROLLBACK
GO

DELETE FROM TB_MAIN_IDX WHERE IDX NOT IN (1,5)
GO
```

다음 예제는 `PHANTOM READ`의 예제이다. REPEATABLE READ는 처음 읽은놈은 끝까지 유지한다고 했는데 중간에 생성 되는 데이터를은 어떻게 될까? 다음 예제를 통해 알아보자

1번 세션
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL REPEATABLE READ
BEGIN TRAN

SELECT * FROM TB_MAIN_IDX

--결과
IDX         NAME
----------- --------------------------------------------------
1           가
5           마
```

2번 세션
```sql
INSERT INTO TB_MAIN_IDX (NAME) VALUES ('배성진')

--결과
--(1개 행 적용됨)
```

1번 세션
```sql
SELECT * FROM TB_MAIN_IDX

--결과
IDX         NAME
----------- --------------------------------------------------
1           가
5           마
20          배성진
```

REPEATABLE READ는 처음 읽었을 당시에 존재하는 데이터에 대해서만 Lock을 걸게 된다. 따라서 2번 세션에서 입력된 배성진의 값은 1번 세션에서 SELECT 할 당시엔 없어서 LOCK 걸리지 않아 INSERT가 되는 것이다. 이러한 경우를 `PHANTOM READ` 부른다. 그럼 해당 케이스도 막는 방법을 아래에서 알아보자

### 2.2.4. SERIALIZABLE (LEVEL 3)
가장 단순하고 엄격한 격리 수준이다. 모든 동작이 직렬화하게 작동한다. <BR>
특정 트랜잭션에서 읽고 있는 데이터는 다른 트랜잭션에서 수정/삭제/삽입이 불가능하다. <BR>
동시성이 떨어져서 성능이 하락하여 잘 쓰이지 않는다.<BR>
Dirty Read, Non Repeatable Read, Phantom Read 문제가 발생하지 않는다. <BR>
아래 예제 쿼리를 통해 살펴 보자

```sql
--동일 하게 1번 세션에서 1,5를 제외하고 삭체 처리 한다.

--2번 세션
IF(@@TRANCOUNT > 0) ROLLBACK -- 혹시 모를 롤백 처리
GO

--1번 세션
IF(@@TRANCOUNT > 0) ROLLBACK
GO

DELETE FROM TB_MAIN_IDX WHERE IDX NOT IN (1,5)
GO
```

1번 세션에서 SERIALIZABLE 해달 레벨로 실행 한다
```sql
IF(@@TRANCOUNT > 0) ROLLBACK
GO

SET TRANSACTION ISOLATION LEVEL SERIALIZABLE
BEGIN TRAN
SELECT * FROM TB_MAIN_IDX 
GO
```

이후 2번 세션에서 아래의 INSERT 쿼리를 실행 한다.
```sql
INSERT INTO TB_MAIN_IDX (NAME) VALUES ('배성진')
--결과
---------- 대기 상태
```
결과를 보면 `REPATABLE READ`와 다르게 막히는 것을 볼 수 있다.

## 3. 정리
Read Uncommitted : Dirty Read, Non-Repeatable Read, Phantom Read<BR>
Read Committed : Non-Repeatable Read, Phantom Read <BR>
Repeatable Read : Phantom Read<BR>
Serializable : x

### 3.1 DB별 Default isolation
MSSQL : Read Committed<BR>
MYSQL : Repeatable Read<BR>
ORACLE : Read Committed


## 4. Sample data
```sql
 
CREATE TABLE [TB_MAIN] (
	[IDX] [INT] NOT NULL IDENTITY (1,  1), 
	[NAME] [VARCHAR](50) 
)
GO

CREATE TABLE [TB_MAIN_IDX] (
	[IDX] [INT] NOT NULL IDENTITY (1,  1), 
	[NAME] [VARCHAR](50) 
)
GO

ALTER TABLE [TB_MAIN_IDX]
	ADD
		CONSTRAINT [PK_TABLE_IDX]
		PRIMARY KEY CLUSTERED (
			[IDX] ASC
		)
GO

 
 INSERT INTO TB_MAIN (NAME) VALUES ('A')
 INSERT INTO TB_MAIN (NAME) VALUES ('B')
 INSERT INTO TB_MAIN (NAME) VALUES ('C')
 INSERT INTO TB_MAIN (NAME) VALUES ('D')
 INSERT INTO TB_MAIN (NAME) VALUES ('E')
 INSERT INTO TB_MAIN (NAME) VALUES ('F')
 INSERT INTO TB_MAIN (NAME) VALUES ('G')
 INSERT INTO TB_MAIN (NAME) VALUES ('H')
 INSERT INTO TB_MAIN (NAME) VALUES ('I')
 INSERT INTO TB_MAIN (NAME) VALUES ('J')
 INSERT INTO TB_MAIN (NAME) VALUES ('K')

 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('A')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('B')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('C')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('D')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('E')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('F')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('G')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('H')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('I')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('J')
 INSERT INTO TB_MAIN_IDX (NAME) VALUES ('K')

 SELECT * FROM TB_MAIN
 SELECT * FROM TB_MAIN_IDX
```