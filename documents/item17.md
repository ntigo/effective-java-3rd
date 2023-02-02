# 아이템 17. 변경가능성을 최소화하라

### 불변 클래스란
인스턴스 내부 값을 수정할수 없는 클래스이다 이러한 불변클래스에 저장된 정보는 객체가 파괴될 때까지 변경되지 않는다. 
불변 클래스는 가변 클래스보다 설계,구현하여 사용하기 쉬우며 오류가 생길여지도 적고 휠씬 안전하다. 

### 불변 클래스 5가지 규칙

* 객체의 상태를 변경하는 메서드(변경자)를 제공하지 않는다.
* 클래스를 확장할 수 없도록 한다.
* 모든 필드를 final 로 선언한다.
* 모든 필드는 private 으로 선언한다.
* 자신의 외에는 내부의 가변 컴포넌트가 접근할수 없도록 한다.

### 불변 클래스의 장점 
* **불변객체는 단순하다.**<br>
  불변객체는 생성된 시점에서 상태를 파괴될때까지 그대로 간직한다.


* **불변객체는 근본적으로 스레드에 안전하여 따로 동기화할 필요가 없다.** <br> 
  따라서 불변 클래스라면 한번 만든 인스턴스를 재활용하기를 권장한다.가장 쉬운 재활용 방법은 상수로 제공하는 것이다.

````java
public static final ImmutablePoint ZERO = new ImmutablePoint(0, 0);
public static final ImmutablePoint ONE = new ImmutablePoint(1, 0);
public static final ImmutablePoint I = new ImmutablePoint(0, 1);
````

* **복사 생성자가 필요없다.**  
  불변 클래스는 공유에 자유롭기 때문에 방어적 복사도 필요가 없다. 
그렇게 때문에 clone 메서드나 복사 생성자를 제공하지 않는것이 좋다.
불변 객체는 불변 객체끼리는 내부 데이터를 공유할수 있다. 

````java
 public class BigInteger extends ... {
    final int signum;
    final int[] mag;
    // ... 생략
    public BigInteger negate() {
        return new BigInteger(this.mag, -this.signum);
    }
````
BigInteger 클래스의 내부이다. signum은 부호를, mag는 크기(절대값)를 의미한다. 한편 negate 메서드는 크기는 같지만 부호만 다른 새로운 BigInteger를 생성하는데, 이때 배열은 가변이지만 복사하지 않고 원본 인스턴스를 그대로 공유하고 있다.

* **객체를 만들때 다른 불변 객체들을 구성요소로 사용하면 이점이 많다.**<br>
  생성될 객체가 복잡한 구조라도 구성요소로 불변 객체가 사용된다면 불변식을 유지하기 훨씬 수월하기 때문이다. 가령 맵의  키와 집합(Set)의 원소로 쓰기 좋다.


* **불변 객체는 그 자체로 실패 원자성을 제공한다.**<br>
  상태가 절대로 변하지 않으니 잠깐이라도 불일치 상태에 빠질 가능성이 없다.
  예를 들어 배열하나를 제거하는 기능의 객체에서 에러가 발생 했을때 배열이 하나 제거가 되면 안된다

### 불변 클래스의 단점
* 값이 다르면 반드시 독립된 객체로 만들어야 한다는 것이다. 

### 대안책
  다단계 연산(multistep operation)들을 예측해 기본 기능으로 제공하기  
  Ex: BigInteger는 모듈러 지수같은 다단계 연산 속도를 높혀주는 가변 동반 클래스(companion class)를 package-private로 두고 있다.   
  다른 예로는 String과 String의 가변 동반 클래스인 StringBuilder가 있다.


### 불변 클래스를 만드는 설계 방법
* 클래스를 final로 선언하는 것.
* 모든 생성자를 private(or package-private)로 만들고 public 정적 팩토리를 제공하는 방법
```java
public class ImmutablePointV2 {
    private final double x;
    private final double y;

    private ImmutablePointV2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static ImmutablePointV2 valueOf(double x, double y) {
        return new ImmutablePointV2(x, y);
    }
		...
}
```

### 결론
최대한 불변 클래스로 만들자.
불변으로 만들 수 없는 클래스도 변경할 수 있는 부분을 최소한으로 줄이자.
다른 합당한 이유가 없다면 모든 필드는 private final이어야 한다.
생성자는 불변식 설정이 모두 완료된, 초기화가 끝난 상태의 객체를 생성해야 한다.
