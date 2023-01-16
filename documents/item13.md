# 아이템 13. clone 재정의는 주의해서 진행하라

----

## clone 이란??

 - 나 자신을 복제하는 메서드, Object에 선언되어있음

```java
// Object 선언 내용
public class Object {
    protected native Object clone() throws CloneNotSupportedException;
}
```

## clone 구현 / 제공

 - Cloneable 인터페이스를 상속받아 구현하여 제공할 수 있음
 - Cloneable 인터페이스를 상속받지 않고 clone을 override하는 경우는 CloneNotSupportedException을 발생시킨다.
 - 정상적으로 구현된 clone은 예외를 발생시지 않음으로 비 검사 예외를 적용하여 사용하기 편리하게 구현하는것이 좋다
 - 하위 클래스에서 clone을 구현하지 못하게 final로 재정의 할 수도 있다.

```java
// 하위 클래스에서 clone을 구현하지 못하게 차단
@Override
protected final Object clone() throws CloneNotSupportedException {
  throw new CloneNotSupportedException();
}
```

## Object 클래스 명세에서의 clone 내용

```java
// 일반적인 의도, 하지만 꼭 이것을 지킬 필요는 없다 ( 필수가 아니다...! )
// 서로 다른 인스턴스(주소)인가? -> true
x.clone() != x

// 모든 상위 클래스(Object 제외)가 clone을 구현할 때 인스턴스를 super.clone을 사용하여 생성했다면 아래는 true 보장
// 같은 class type인가? -> 일반적으로 true
x.clone().getClass() == x.getClass()
        
// 서로 같은 인스턴스(논리)인가? -> true
x.clone().equals(x)
```

 - 관례상 clone으로 복사된 인스턴스와 원본 인스턴스는 독립적이어야 한다.
 - 독립적이기 위해 super.clone으로 얻은 인스턴스에서 일부 필드의 값을 변경하고 반환해야 할 수 있다.
 
## clone 구현 시 얕은 복사( Shallow Copy )를 주의 하라

 - clone을 실행하는 개발자의 경우 반환되는 인스턴스가 깊은 복사( Deep Copy ) 처리되는것으로 예상한다.
   - ( x.clone() != x )는 일반적으로 true이다... 깊은 복사를 요구한다...!
   - 깊은 복사로 처리하지 않는다면 clone을 사용하지 않고 그냥 변수를 대입하면 된다.... clone을 사용할 필요가 없는것이다...!
 - reference type의 필드는 clone에서 별도 처리하지 않으면 얕은 복사로 처리되기 때문에 주의를 요한다...!
 - final로 선언된 reference type의 필드는 깊은 복사 처리할 때 새로운 값의 할당이 불가함으로 final을 제거해야 된다.

## clone 보다는 복사 생성자( 변환 생성자 ), 복사 팩토리( 변환 팩토리 )가 더 좋다

 - 생성자를 쓰지 않아 인스턴스를 생성하는 과정에 오류가 발생할 위험이 있다.
 - clone은 규약이 정확하지 않다.
 - final 필드를 재대로 사용할 수 있다.
 - 불필요한 예외가 애초에 없고, 형변환도 필요가 없다. ( 그냥 더 편리하다...! )
 - 인터페이스 타입 또는 하위 클래스를 인수로 받을 수 있다.

```java
public class Main {
   public static void main(String[] args) {
      HashSet<String> hashSet = new HashSet<>();
      hashSet.add("이것은 값입니다.");
      // 책에서 변환 생성자의 이점을 알려준 예제 코드 
      TreeSet<String> treeSet = new TreeSet<>(hashSet);
   }
}
```

## 하위 클래스의 clone에서는 재정의한 메서드를 호출하면 안된다.

하위 클래스에서 정의된 clone은 재정의한 메서드를 호출해서 데이터를 처리한 경우 원본과 다른 데이터로 처리될 수 있다...!
데이터를 저장( set,add,put등 )하는 메서드가 변경되면 안되는 경우 final, private를 통해 재정의를 못하게 하자

## thread safe한 clone

clone 처리 중 다른 스레드로 인해 데이터가 변경되면 원하는 데이터를 얻지 못할 수 있다.  
상세한 내용은 [아이템 78 - 공유 중인 가변 데이터는 동기화해 사용하라]에서 다룰 예정이다...!

## 마무리

 - Cloneable은 단점이 많기 때문에 사용해서는 안된다. 복제 기능은 생성자와 팩토리를 이용해라.
 - 배열 타입의 경우 clone 메서드 방식이 예외적으로 사용하는데 좋다고 할 수 있다.
 - 성능 최적화가 필요한 상태에서는 clone을 허용할 수는 있다. 하지만 그러한 경우는 아마도 거의 없다...!
   - [아이템 67 - 최적화는 신중히 하라]에서 상세히 다룰 예정이다...!


