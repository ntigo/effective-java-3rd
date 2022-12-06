# 아이템 1. 생성자 대신 static 팩토리 메소드 사용을 고려하자.

일반적인 인스턴스 생성 방식은 생성자(Constructor)를 이용하는 방법이 있다.
이 외에도 정적 팩터리 메소드(static factory method)를 제공 할 수 있다.
아래는 정적 팩터리 메소드 사용 시 장점과 단점에 대해 열거한다.

### 장점
* 이름을 가질 수 있다.
* 동일한 시그니처를 갖는 복수의 생성자를 갖는 효과를 눌린다.
* 호출될 때마다 매번 새로운 객체를 생성할 필요가 없다.
(Immutable 재사용 가능, Singleton)
* 반환 타입에 하위 타입 객체를 반환할 수 있다.
* 입력 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다.
* 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

### 단점
* 정적 팩터리 메소드만 제공하면 하위 클래스를 만들 수 없다. (public, protected 생성자의 필요)
* 해당 기능을 찾는데 어려움이 있다. (생성자처럼 설명이 명시적이지 않음.)

```java 
// 호출될 때마다 매번 새로운 객체를 생성할 필요가 없다.
public static Boolean valueOf(boolean b) {
    return (b ? TRUE : FALSE);
}

// 입력 매개변수에 따른 클래스의 다른 객체 반환 예제
public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
    Enum<?>[] universe = getUniverse(elementType);
    if (universe == null)
        throw new ClassCastException(elementType + " not an enum");

    if (universe.length <= 64)
        return new RegularEnumSet<>(elementType, universe);
    else
        return new JumboEnumSet<>(elementType, universe);
}
```
