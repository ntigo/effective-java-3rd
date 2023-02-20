# 멤버 클래스는 되도록 static으로 만들라

### # 중첩 클래스( nested class )란
 - 다른 클래스 안에 정의된 클래스
 - 중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하며, 그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 한다.

### # 중첩 클래스 종류
 - 정적 멤버 클래스
 - 내부 클래스 ( inner class )
   - 비정적 멤버 클래스
   - 익명 클래스
   - 지역 클래스

### # 정적 멤버 클래스
 - 클래스 안에 선언되고, 바깥 클래스의 private 멤버에도 접근할 수 있다. 
 - 정적 멤버 클래스는 다른 정적 멤버와 똑같은 접근 규칙을 적용받는다.
   - private으로 선언하면 바깥 클래스에서만 접근할 수 있다.
 - 보통 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.
   - 예를들어 Operation 열거 타입이 Calculator 클래스의 public 정적 멤버 클래스( enum )로 사용
```java
// item24.exam1
public class Calculator {
    public enum Operation {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    public int execute( Operation operation, int first, int second ) {
        switch ( operation ) {
            case PLUS:
                return first + second;
            case MINUS:
                return first - second;
            case MULTIPLY:
                return first * second;
            case DIVIDE:
                return first / second;
            default:
                return 0;
        }
    }
}

public class CalculatorExample {
    /**
     * 바깥 클래스와 함께 쓰일 때 유용한 public 도우미 클래스
     */
    public void calculatorTest() {
        Calculator calculator = new Calculator();
        int result = calculator.execute( Calculator.Operation.PLUS, 1, 3 );
    }
}
```
 - 중첩 클래스의 인스턴스가 바깥 인스턴스와 독립적으로 존재할 수 있다면 정적 멤버 클래스로 만들어야 한다.
   - 비 정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없기 때문.
```java
// item24.exam1
public class OutsideClass {
    public void print() {
        System.out.println( "outside class ~" );
    }
    public class NonStaticClass {
        public void print() {
            System.out.println( "non static class ~" );
            OutsideClass.this.print(); // 바깥 클래스 호출
        }
    }
    public static class StaticClass {
        public void print() {
            System.out.println( "static class ~" );
        }
    }
}

public class OutsideClassExample {
   /**
    * 비 정적 멤버 클래스는 바깥 인스턴스 없이는 생성할 수 없음
    */
   public void nonStaticClass() {
      OutsideClass outsideClass = new OutsideClass();
      OutsideClass.NonStaticClass nonStaticClass = outsideClass.new NonStaticClass();
      outsideClass = null;
      nonStaticClass.print();
   }

   /**
    * 정적 클래스는 바깥 인스턴스 생성 없이 생성 가능
    */
   public void staticClass() {
      OutsideClass.StaticClass staticClass = new OutsideClass.StaticClass();
      staticClass.print();
   }
}
```

### # 비 정적 멤버 클래스
 - 어댑터를 정의할 때 자주 쓰인다.
   - 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 뷰로 사용
   - Map 인터페이스의 구현체들은 자신의 컬렉션 뷰를 구현할 때 비정적 멤버 클래스를 사용한다.
   - Set, List 같은 컬렉션 인터페이스 구현들도 자신의 반복자를 구현할 때 비정적 멤버 클래스를 주로 사용한다.
```java
// @author Josh Bloch... 진짜다!
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
   public Iterator<E> iterator() {
      return new Itr();
   }

   private class Itr implements Iterator<E> {
      public E next() {
      }
      public void remove() {
      }
      @Override
      public void forEachRemaining(Consumer<? super E> consumer) {
      }
      final void checkForComodification() {
      }
   }
}
```

 - 멤버 클래스에서 바깥 인스턴스에 접근할 일이 없다면 무조건 정적 멤버 클래스로 만드는것이 좋다.
   - 바깥 인스턴스로의 숨은 외부 참조를 가지게 된다.
     - 이 참조를 저장하려면 시간과 공간이 소비된다.
   - 정적 클래스는 가비지 컬렉션이 바깥 클래스의 인스턴스를 수거하지 못하는 메모리 누수가 생길 수 있다.
     - OutsideClassExample 클래스의 nonStaticClass 메서드 참조
 - private 정적 멤버 클래스는 흔히 바깥 클래스가 표현하는 객체의 한 부분( 구성요소 )을 나타낼 때 쓴다.
   - 많은 Map 구현체는 각각의 키-값 쌍을 표현하는 엔트리( Entry ) 객체들을 가지고 있다.
   - Entry는 Map과 연관되어 있지만 엔트리의 메서드들 getKey, getValue, setValue는 Map을 직접 사용하지 않는다.
   - Entry를 비 정적 클래스로 구현해도 정상 동작하겟지만 모든 Entry가 바깥 클래스인 Map의 참조를 갖게 되어 공간과 시간을 낭비한다.

```java
public class HashMap<K,V> extends AbstractMap<K,V> {
    // 이 친구가 비 정적 클래스였다면...? 
    static class Node<K,V> implements Map.Entry<K,V> {
    }
}
```

 - 멤버 클래스가 공개된 클래스의 public이나 protected 멤버라면 정적이냐 아니냐는 더 중요해진다.
   - 멤버 클래스 역시 공개 API가 되니, 혹시라도 향후 릴리스에서 정적 클래스로 변경하면 하위 호환성이 깨진다.
   
### # 익명 클래스
 - 바깥 클래스의 멤버 클래스가 아님
 - 쓰이는 시점에 선언과 동시에 인스턴스가 만들어진다. 재사용이 불가능하다.
 - 코드의 어디서든 만들 수 있다.
 - 비 정적인 문맥에서 사용될 때만 바깥 클래스의 인스턴스를 참조할 수 있다.
 - 정적 문맥에서라도 상수 변수 이외의 정적 멤버는 가질 수 없다.
 - 인터페이스를 구현할 수 없다. 다른 클래스를 상속할 수 없다.
 - 익명 클래스가 바깥 클래스가 상속한 멤버 외에는 호출할 수 없다.
 - 표현식 중간에 등장하므로 짧지 않으면 가독성이 떨어진다.
 - 람다를 지원하기 전에는 즉석에서 작은 함수 객체나 처리 객체를 만드는 데 익명 클래스를 주로 사용했지만 이제는 람다를 사용한다.
 - 정적 팩터리 메서드를 구현할 때 사용된다. ( item 20-1 )
   - 이렇게 구현할... 이유가 있는가? 

### # 지역 클래스
 - 지역 변수를 선언할 수 있는 곳이면 어디서든 선언할 수 있다. 유효 범위도 지역 변수와 같다.
 - 이름이 있고 반복해서 사용할 수 있다.
 - 익명 클래스처럼 비 정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있다.
 - 정적 멤버는 가질 수 없다.
 - 가독성을 위해 짧게 작성해야 한다.

### # 핵심 정리
 - 메서드 밖에서도 사용해야 하거나 메서드 안에 정의하기엔 너무 길다면 멤버 클래스로 구현한다.
 - 멤버 클래스의 인스턴스 각각이 바깥 인스턴스를 참조한다면 비 정적으로, 그렇지 않으면 정적으로 구현한다.
 - 중첩 클래스가 한 메서드 안에서만 쓰이면서 그 인스턴스를 생성하는 지점이 단 한곳이고 해당 타입으로 쓰기에 적합한 클래스나 인터페이스가 이미 있다면 익명 클래스로, 그렇지 않으면 지역 클래스로 구현한다.

