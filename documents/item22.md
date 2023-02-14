# 아이템 22. 인터페이스는 타입을 정의하는 용도로만 사용하라

## 인터페이스란?
인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다. 즉, 자신의 인스턴스로 무엇을 할 수 있는지 클라이언트에 얘기해주는 것이다.<br>

인터페이스는 오직 `이 용도로만` 사용되어야 한다.

```java
// 잘못된 예시
public interface Walkable {
	void walk();
}

public class Dog implements Walkable {

	@Override
	public void walk() {
            ...
	}
}
```

## 위 지침에 맞지 않는 상수 인터페이스(안티패턴)

* 아래 코드는 인터페이스를 잘못 사용한 예

```java
public interface PhysicalConstants {
	static final double AVOGADROS_NUMBER   = 6.022_140_857e23;
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
	static final double ELECTRON_MASS      = 9.109_383_56e-31;
}
```

## 상수 인터페이스 왜 쓰지 말아야 할까?
1. 사용하지 않을 수도 있는 상수를 포함하여 모두 가져오기 때문에 계속 가지고 있어야 한다.
2. 클라이언트가 상수 인터페이스 값에 직접 접근할 수 있다. 이때, 불필요한 정보를 노출하는 것이 오히려 클라이언트에게 혼란을 야기할 수 있다.
   - 인터페이스에 private이나 protected를 사용할 수 없다
   - 클래스가 내부적으로 사용하는 상수들은 구현 세부사항인데, 모든 클래스에서 접근이 가능해 캡슐화를 깨뜨린다.
3. 클래스가 어떤 상수 인터페이스를 사용하든 사용자에게는 아무런 의미가 없다.
4. 열거형 상수를 표현하기에 서툴다.
   - 의미없는 상수 `(0, 1, 2, 3)`가 사용됨
    ```java
        public interface WeatherConstants {
            int SUNNY = 0;
            int CLOUDY = 1;
            int RAINY = 2;
            int THUNDERSTORM = 3;
        }
    ```
5. 바이너리 호환성을 위해 추후에 해당 상수들이 사용되지 않더라도 인터페이스를 구현해야 한다.
	> 바이너리 호환성이란?
	> 클래스를 변경할 때 해당 클래스를 사용하는 클래스를 다시 컴파일할 필요가 없음을 의미한다.

### 마지막으로
인터페이스(interface)란 다른 클래스를 작성할 때 기본이 되는 틀을 제공하면서, 다른 클래스 사이의 중간 매개 역할을 담당하는 것인데 이를 상수를 위해 사용하는 것은 인터페이스를 올바르게 사용하는 것이 아니게 된다.

### 대안
* 클래스나 인터페이스 자체에 추가
```java
public final class Integer extends Number
        implements Comparable<Integer>, Constable, ConstantDesc {

	@Native
	public static final int MIN_VALUE = 0x80000000;
	Integer

	@Native
	public static final int MAX_VALUE = 0x7fffffff;
    
    ...
}
```
