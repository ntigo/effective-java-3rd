# 아이템 22. 인터페이스는 타입을 정의하는 용도로만 사용하라

## 인터페이스란?
인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다. 즉, 자신의 인스턴스로 무엇을 할 수 있는지 클라이언트에 얘기해주는 것이다.<br>
인터페이스는 오직 `이 용도로만` 사용되어야 한다.<br>
이 지침에 맞지 않는 예로 소위 상수 인터페이스라는 것이 있다. 상수 인터페이스란 `메서드 없이 상수를 뜻하는 static final 필드로만 가득 찬 인터페이스`를 말한다

```java
public interface PhysicalConstants {
	static final double AVOGADROS_NUMBER   = 6.022_140_857e23;
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
	static final double ELECTRON_MASS      = 9.109_383_56e-31;
}
```
클래스가 어떤 상수 인터페이스를 사용하든 사용자에게는 아무런 의미가 없다. 오히려 사용자에게 혼란을 주기도 하며, 더 심하게는 클라이언트 코드가 내부 구현에 해당하는 이 상수 들에 종속되게 한다.
final이 아닌 클래스가 상수 인터페이스를 구현한다면 `모든 하위 클래스의 이름 공간이 그 인터페이스가 정의한 상수들로 오염되어 버린다`.

```java
public interface TestInterface {
	static String NAME = "bong";
}

public class Test implements TestInterface {

	public static void main(String[] args) {
		System.out.println(NAME);
	}
}

// result : bong

public class Test implements TestInterface {
	public static final String NAME = "test";

	public static void main(String[] args) {
		System.out.println(NAME);
	}
}

// result : test
```
`java.io.ObjectStreamContants` 등, 자바 플랫폼 라이브러리에도 상수 인터페이스가 몇 개 있으나, 인터페이스를 잘못 활용한 예이니 따라 해서는 안된다.

```java
public interface ObjectStreamConstants {

	/**
	 * Magic number that is written to the stream header.
	 */
	final static short STREAM_MAGIC = ( short ) 0xaced;

	/**
	 * Version number that is written to the stream header.
	 */
	final static short STREAM_VERSION = 5;

	/* Each item in the stream is preceded by a tag
	 */

	/**
	 * First tag value.
	 */
	final static byte TC_BASE = 0x70;

	/**
	 * Null object reference.
	 */
	final static byte TC_NULL = ( byte ) 0x70;
	...

}
```

상수를 공개할 목적이라면 더 합당한 선택지가 몇 가지 있다. `특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가해야 한다`. 모든 숫자 기본 타입의 박싱 클래스가 대표적으로, `Integer`와 `Double`에 선언된 `MIN_VALUE`와 `MAX_VALUE` 상수가 이런 예다.

```java
public final class Integer extends Number implements Comparable<Integer> {
	/**
	 * A constant holding the minimum value an {@code int} can
	 * have, -2<sup>31</sup>.
	 */
	@Native
	public static final int MIN_VALUE = 0x80000000;

	/**
	 * A constant holding the maximum value an {@code int} can
	 * have, 2<sup>31</sup>-1.
	 */
	@Native
	public static final int MAX_VALUE = 0x7fffffff;
}
```

적합한 상수라면 열거 타입으로 만들어 공개하면 된다. 그것도 아니라면, 인스턴스화 할 수 없는 유틸리티 클래스에 담아 공개하자.

```java
public class PhysicalConstants {
	private PhysicalConstants() {} // 인스턴스화 방지

	// 아보가드로 수 (1/몰)
	static final double AVOGADROS_NUMBER = 6.022_140_857e23;

	// 볼츠만 상수 (J/K)
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

	// 전자 질량 (kg)
	static final double ELECTRON_MASS = 9.109_383_56e-31;
}

```

유틸리티 클래스의 상수를 빈번히 사용한다면 정적 임포트(static import)하여 클래스 이름을 생략할 수 있다

```java
import static com.coopnc.effectivejava3rd.item22.PhysicalConstants.AVOGADROS_NUMBER;

public class Test {
	double atoms(double mols) {
		return AVOGADROS_NUMBER * mols;
	}

    ...
}
```

## 정리
인터페이스는 타입을 정의하는 용도로만 사용해야 한다. 상수 공개용 수단으로 사용하지 말자.