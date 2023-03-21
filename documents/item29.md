# 아이템 29. 이왕이면 제네릭 타입으로 만들라

JDK가 제공하는 제네릭 타입과 메서드를 사용하는 일은 일반적으로 쉬운 편이지만, `제네릭 타입`을 새로 만드는 일은 조금 더 어렵다.<br>


```java
public interface PhysicalConstants {
	static final double AVOGADROS_NUMBER   = 6.022_140_857e23;
	static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
	static final double ELECTRON_MASS      = 9.109_383_56e-31;
}
```
Object 기반 스택 - `제네릭`이 가장 절실한 강력 후보

```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITiNAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITiNAL_CAPACITY];
    }

    public  Object push(Object item) {
        ensureCapacity();
        elements[size++]= item;
        return item;
    }

    public Object pop() {
        if (size == 0) {
            throw  new EmptyStackException();
        }

        Object result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == 0) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}

// result : test
```
위는 제네릭이 절실하다. 제네릭으로 만들어보자.<br><br>


일반 클래스를 제네릭 클래스로 만드는 첫 단계는 클래스 선언에 타입 매개변수를 추가하는 일이다. 아래 코드 에서는 스택이 담을 원소의 타입 하나만 추가하면 된다. 이때 타입 이름으로는 보통 `E`를 사용한다.
```java
public class Stack<E> {

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITiNAL_CAPACITY = 16;

    public Stack() {
        elements = new E[DEFAULT_INITiNAL_CAPACITY]; // 컴파일 에러 발생
    }

    public  void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E Pop() {
        if (size == 0) {
            throw  new EmptyStackException();
        }

        E result = elements[--size];
        elements[size] = null; // 다 쓴 참조 해제
        return result;
    }
    // isEmpty와 ensureCapacity 메서드는 그대로다.
}
```
`아이템 28`에 나오는 것처럼 E와 같은 실체화 불가 타입으로 배열 생성 안된다. 즉 `Generic`은 실체화 할 수 없다.

## 1. 제네릭 배열 생성을 금지하는 제약을 우회하는 방법

```java
public class Stack<E> {

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITiNAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITiNAL_CAPACITY];
    }
    ...
}
```
배열 elements가 private 필드에 저장되고 클라이언트에 반환되거나 다른 메서드에 전달되지 않아서 완전하긴하다.<br>
가독성이 더 좋다. 배열의 타입을 `E[]`로 선언하며 E 타입 인스턴스만 받읆을 어필한다. 형변환을 배열 생성시에만 해주면 된다. 하지만 (`E가 Object`)가 아닌한 배열의 런타임 타입이 컴파일타임 타입과 달라 `힙 오염`을 일으킨다.

## 2. elements 필드의 타입을 E[]에서 Object[]로 바꾸는 방법

```java
public E Pop() {
    if (size == 0) {
        throw  new EmptyStackException();
    }

    @SuppressWarnings("unchecked")
    E result = (E) elements[--size];

    elements[size] = null; // 다 쓴 참조 해제
    return result;
}
```
E는 실체화 불가 타입이므로 컴파일러는 런타임에 이뤄지는 형변환이 안전한지 증명할 방법이 없다. 컴파일러가 런타임에 이뤄지는 형변환이 안전한지 알 수없다.`형변환을 원소를 읽을때마다 해줘야 한다.`<br>
그런데 지금까지 설명은 아이템 `28. 배열보다는 리스트를 사용하라`와 모순되어보인다.

## 힙 오염(Heap Pollution)이란
매개변수 유형이 다른 서로 다른 타입을 참조할 때 생기는 문제


## 정리
클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편하다. 그러니 새로운 타입을 설계할 때는 형변환 없이도 사용할 수 있도록 하라. 그렇게 하려면 제네릭 타입으로 만들어야 할 경우가 많다. 기존 타입 중 제네릭이었어야 하는 게 있다면 제네릭 타입으로 변경하자.
