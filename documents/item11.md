# 아이템 11. equals를 재정의하려거든 hashcode도 재정의하라 

## 재정의 이유
hashCode 일반규약을 어기게 되어 해당 클래스의 인스턴스를 hashMap이나 hashSet같은 컬렉션의 원소로 사용할 때 문제를 일으킨 것이다.

## hashMap 이란 
데이터 저장은 key, valus 형태가 된다. key값의 hashCode를 index로 Array에 값을 저장한다. 
해싱검색을 사용하기 때문에 대용량 데이터 관리에서 좋은 성능을 보여준다. 
해시 테이블은 빠른 검색 속도를 제공하는데 이유는 내부적으로 버킷(배열)을 사용하여 데이터를 저장하기 때문이다. 해시 테이블은 각각의 key 값에 해시 함수를 적용해 배열의 고유한 인덱스를 생성한 후 인덱스를 이용해 값을 저장한다. 실제 값이 저장되는 장소를 버킷이라 한다.

## HashMap get 동작 방식
우선 hashCode() 메소드를 실행해서 리턴된 해시코드 값이 같은지를 본다. 해시 코드값이 다르면 다른 객체로 판단하고, 해시 코드값이 같으면
equals() 메소드로 다시 비교한다. 이 두 개가 모두 맞아야 동등 객체로 판단한다. 즉, 해시코드 값이 다른 엔트리끼리는 동치성 비교를 시도조차 하지 않도록 최적화되어 있기 때문이다. 

## hashCode 일반규약
* equals 비교에 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행하는 동안 그 객체의 hashCode 메서드는 몇번을 호출해도 일관되게 항상 같은 값을 반환해야한다. 단, 애플리케이션을 다시 실행한다면 이 값이 달라져도 상관없다.
* 두 객체를 같다고 판단했다면, 두객체의 hashCode는 똑같은 값을 반환해야한다.
* 두 객체를 다르다고 판단했더라도, 두객체의 hashCode가 서로 다른 값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다. 

>hashCode 재정의를 잘못했을 때 크게 문제가 되는 조항은 두번째이다, 즉 논리적으로 같은 객체는 같은 해시코드를 반환해야한다. 하지만 Object의 기본 
hashCode 메서드는 이둘이 전혀 다르다고 판단하여, 규약과 달리(무작위처럼 보이는) 서로 다른 값을 반환한다. 

## 모두 똑같은 hashCode 제공하는 경우
>  이 구현은 모든 객체가 동일한 단일 버킷에 저장되기 때문에 해시 테이블의 기능을 기본적으로 0으로 저하시킵니다.

## 좋은 hashCode 작성하는 방법
1. int 변수 result를 선언후 값을 c로 초기화 한다. c는 해당 객체의 equals 비교에 사용된 첫번째 필드를 타입에 따라서 해시코드로 계산한다.
2. 해당 객체의 equals 비교에 사용된 나머지 필드를 각각 계산하여 c선언 후 result = 31 * result + c로 result를 갱신한다. 
3. result를 반환한다.

>필드 타입별 계산
>* 기본 타입이라면 Type.hashCode(f)를 수행한다. 여기서 Type은 해당하는 기본 타입의 박싱 클래스이다
>* 참조 타입 필드면 클래스의 equals 메서드가 필드의 equals 를 재귀적으로 호출해 비교한다면, 필드의 hashCode를 재귀적으로 호출한다.
  계산이 복잡해 질것 같으면 이 필드의 표준형을 만들어 호출한다, 필드값이 null이면 0을 사용한다.
>* 필드가 배열이라면 핵심 원소 각각 별도의 필드처럼 다룬다. 배열에 핵심요소가 없다면 단순히 상수 0을 추천한다. 모든 원소가 핵심 원소라면 Arrays.hashCode를 사용한다. 


## 전형적인 hashCode 메서드

```java
@Override
public int hashCode() {
int result = Integer.hashCode(firstNumber);
result = 31 * result + Integer.hashCode(middleNumber);
result = 31 * result + Integer.hashCode(lastNumber);
return result;
}
```

## Objects 클리스의 hashCode 메서드 
```java
public int hashCode(){
  return Objects.hash(lineNum, prefix, areaCode)
}
```

입력 인수를 담기 위한 배열이 만들어지고, 입력 중 기본 타입이 있다면 박싱 과 언박싱도 거친다. 속도가 느려진다. 

## 31을 곱하는 이유
컴퓨터 입장에서는 곱하기 연산을 하는 것보다 비트를 한칸 옴기는 것이 더 빠르다 그래서 JVM에서는 이러한 계산을 최적화를 자동으로 도와준다.
(31 * i는 (i << 5) -1 과 동일하다.)
쉬프트 연산시 짝수를 곱해 오버플로우가 일어날시 오른쪽은 모두 0으로 차게되고 이경우 정보 손실이 일어날수 있기 때문에 홀수를 곱해줘야한다. 

## 지연 초기화 전략
클래스가 불변이고 해시코드를 계산하는 비용이 크다면 매번 새로 계산하지 말고 캐싱을 고려해야 한다. 
지연 초기화 전략을 사용할 경우에는 스레드에 안전하도록 고려해야한다 (아이템 83)

## 해시 충돌이 더욱 적은 방법이 필요한 경우 
구아바의 com.google.common.hash.Hashing을 참고


## 주의할 점
* equals 비교에 사용되지 않는 필드는 반드시 제외 해야한다.
* 해시코드 계산시 핵심필드를 생략해서는 안된다.
* hashCode가 반환하는 값의 생성규칙을 API 사용자에게 공표하지 않아야 한다. 그래야 클라이언트가 이 값에 의지하지 않게 되고,추후에 계산방식을 바꿀수도있다.

## 결론 
* equals를 재정의 할때는 hashCode도 재정의 하라. 재정의한 hashCode는 일반 규약을 따라야하며 서로 다른 인스턴스일 경우 hashCode가 다르게 구현해야 한다.







