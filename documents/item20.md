# item 20. 추상클래스 보다는 인터페이스를 우선하라
인터페이스와 추상 클래스는 자바가 제공하는 다중 구현 메커니즘이다. 자바 8부터 인터페이스도 디폴트 메서드(default method)를 제공할 수 있게 되어, 이제는 두 메커니즘 모두 인스턴스 메서드를 구현 형태로 제공할 수 있다.

### 인터페이스와 추상 클래스의 가장 큰 차이는 결합의 정도이다.

* 추상 클래스가 정의한 타입을 구현하는 클래스는 ``반드시 추상 클래스의 하위 클래스가 되어야 한다는 점``이다. 자바는 단일 상속만 지원하니, 추상 클래스 방식은 새로운 타입을 정의하는 데 커다란 제약을 갖는다.
* 인터페이스가 선언한 메서드를 모두 정의하고 그 일반 규약을 잘 지킨 클래스라면 ``다른 어떤 클래스를 상속했든 같은 타입으로 취급``된다.

````java
public class SingerSongWriter extends Song implements Singer {
   ...
}

public class SingerSongWriter2 extends Song2 implements Singer {
   ...
}

//Main
public static void main(String[] args) {
   Singer s = new SingerSongWriter();
   Singer s2 = new SingerSongWriter2();
}
````
즉, 인터페이스의 경우 Singer라는 인터페이스를 정의한 클래스라면 모두 Singer 타입으로 취급할 수 있다.

### 인터페이스 장점
 * 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다.
 * 믹스인(mixin) 정의에 안성맞춤이다
 * 계층구조가 없는 타입 프레임워크를 만들 수 있다.
 * 래퍼 클래스 관용구(아이템 18)와 함께 사용하면 기능을 향상시키는 안전하고 강력한 수단이 된다.

-----
1. 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다.
-----
인터페이스가 요구하는 메서드를 추가하고, 클래스 선언에 implements 구문만 추가하면 끝이다.
 * 자바 플랫폼에서도 Comparable, Iterable, AutoCloseable 인터페이스가 새로 추가됐을 때 표준 라이브러리의 수많은 기존 클래스가 이 인터페이스들을 구현한 채 릴리스됐다.

반면 기존 클래스 위에 새로운 추상 클래스를 끼워넣기는 어려운 게 일반적이다.
 * 만약 두 클래스가 같은 추상 클래스를 상속받길 원한다면 계층구조상 두 클래스의 공통 조상이어야 한다.
 * 이 방식은 클래스 계층구조에 혼란을 일으킨다. 새로 추가된 추상 클래스의 모든 자손이 이를 상속하게 되는 것이다.
-----
2. 인터페이스는 믹스인(mixin) 정의에 안성맞춤이다.
-----
믹스인(mixin)이란 클래스가 구현할 수 있는 타입으로, 믹스인을 구현한 클래스에 원래의 ‘주된 타입’ 외에도 특정 선택적 행위를 제공한다고 선언하는 효과를 준다.
 * 예컨대 Comparable은 자신을 구현한 클래스의 인스턴스들끼리는 순서를 정할 수 있다고 선언하는 믹스인 인터페이스다.
 * 이처럼 ``주된 기능에 선택적 기능을 ‘혼합(mixed in)’``한다고 해서 믹스인이라 부른다.

추상 클래스로는 믹스인을 정의할 수 없다.
 * 기존 클래스에 덧씌울 수 없기 때문이다.
 * 클래스는 두 부모를 섬길 수 없고, 클래스 계층구조에는 믹스인을 삽입하기에 합리적인 위치가 없기 때문이다.
-----
3. 인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있다.
-----
타입을 계층적으로 정의하면 수많은 개념을 구조적으로 잘 표현할 수 있지만, 현실에는 계층을 엄격히 구분하기 어려운 개념도 있다.
예를 들어 가수(Singer) 인터페이스와 작곡가(Songwriter) 인터페이스가 있다고 해보자.
````java
public interface Singer {
AudioClip sing(Song s);
}

public interface Songwriter {
Song compose(int chartPosition);
}
````
하지만 현실에는 작곡도 하는 가수도 많다. 이 코드처럼 타입을 인터페이스로 정의하면 가수 클래스가 Singer와 Songwriter 모두를 구현해도 전혀 문제되지 않는다.
 * 심지어 Singer와 Songwriter 모두를 확장하고 새로운 메서드까지 추가한 제 3의 인터페이스를 정의할 수도 있다.
````java
   public interface SingerSongwriter extends Singer, Songwriter{
   AudioClip strum();
   void actSensitive();
}
````
같은 구조를 클래스로 만들려면 가능한 조합 전부를 각각의 클래스로 정의한 고도비만 계층구조가 만들어질 것이다. 속성이 n개라면 지원해야 할 조합의 수는 2^n개나 된다.
 * 간단한 예로, 위의 인터페이스로 구현한 싱어송라이터를 상속으로 구현하려면 (Singer, AbstractSinger), (Songwriter, AbstractSongwriter), (SingerSongWriter, AbstractSingerSongWriter)를 만들어야 한다.
 * 아니면 상속을 받지않고 중복 메서드를 구현하는 방법이 있다.
-----
4. 래퍼 클래스 관용구(아이템 18)와 함께 사용하면 기능을 향상시키는 안전하고 강력한 수단이 된다.
-----
타입을 추상 클래스로 정의해두면 그 타입에 기능을 추가하는 방법은 상속뿐이다. 상속해서 만든 클래스는 래퍼 클래스보다 활용도가 떨어지고 깨지기는 더 쉽다.
````java
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) { this.s = s; }

    public void clear()               { s.clear();            }
    public boolean contains(Object o) { return s.contains(o); }
    public boolean isEmpty()          { return s.isEmpty();   }
    ...
 }
````
### 인터페이스 구현할 때 디폴트 메서드, 추상 골격 구현 클래스를 고려해보자.``

인터페이스의 메서드 중 구현 방법이 명백하다면 디폴트 메서드로 구현하자. 이 기법의 예는 Collection 인터페이스의 removeIf 메서드를 보면 된다.
 * 디폴트 메서드를 제공할 때는 @implSpec 자바독 태그를 붙여 문서화해야 한다.

````java
default boolean removeIf(Predicate<? super E> filter) {
    Objects.requireNonNull(filter);
    boolean removed = false;
    final Iterator<E> each = iterator();
    while (each.hasNext()) {
        if (filter.test(each.next())) {
            each.remove();
            removed = true;
        }
    }
    return removed;
}
````

디폴트 메서드에도 제약은 있다.
 * 많은 인터페이스가 equasl와 hashCode 같은 Object 메서드를 정의하고 있지만, 이들은 디포트 메서드로 제공해서는 안 된다.

### 인터페이스 설계가 복잡할 경우 추상 골격 구현 클래스를 함께 제공하자  (템플릿 메서드 패턴)``
인터페이스와 추상 골격 구현(skeletal implementation)클래스를 함께 제공하는 식으로 인터페이스와 추상 클래스의 장점을 모두 취하는 방법도 있다.
 * 인터페이스로는 타입을 정의하고, 필요하면 디폴트 메서드 몇 개도 함께 제공한다.
 * 골격 구현 클래스는 나머지 메서드들까지 구현한다.

인터페이스로는 타입을 정의하고, 필요하면 디폴트 메서드 몇 개도 함께 제공한다.
 * 인터페이스로는 타입을 정의하고, 필요하면 디폴트 메서드 몇 개도 함께 제공한다.
 * 좋은 예로, AbstractCollcetion, AbstractSet, AbstractList, AbstractMap 각각이 바로 핵심 컬렉션 인터페이스의 골격 구현이다.

다음 코드는 완벽히 동작하는 List 구현체를 반환하는 정적 팩터리 메서드로, AbstractList 골격 구현으로 활용했다.
 * List 구현체가 제공하는 기능들을 생각하면, 이 코드는 골격 구현의 힘을 잘 보여주는 인상적인 예라 할 수 있다.
 * 그리고 이 예는 int 배열을 받아 List<Integer>로 형태로 보여주는 어댑터(Adapter)이기도 하다.
 * int값과 Integer 인스턴스 사이의 변환(박싱, 언박싱) 때문에 성능은 그리 좋지 않다.

### [ 골격 구현을 사용해 완성한 구체 클래스 ]
````java
static List<Integer> intArrayList(int[] a){
   Objects.requireNonNull(a);

   // 다이아몬드 연산자를 이렇게 사용하는 건 자바 9부터 가능하다.
   // 더 낮은 버전을 사용한다면 <Integer>로 수정하자.
   return new AbstractList<>() {
      @Override public Integer get(int index) {
         return a[index]; // 오토박싱 (아이템 6)
      }

      @Override
      public Integer set(int index, Integer element) {
         int oldval = a[index];
         a[index] = element;
         return oldval;
      }

      @Override public int size() {
         return a.length;
      }
   };
}
````
골격 구현 클래스의 아름다움은 추상 클래스처럼 구현을 도와주는 동시에, 추상 클래스로 타입을 정의할 때 따라오는 심각한 제약에서 자유롭다는 점이다.
 * 골격 구현 확장하는 것으로 인터페이스 구현이 거의 끝나지만, 꼭 이렇게 해야 하는 것은 아니다.

### 시뮬레이트한 다중 상속(simulated multiple inheritance)
구조상 골격 구현을 확장하지 못하는 처지라면 인터페이스를 직접 구현해야 한다.
 * 이런 경우라도 인터페이스가 직접 제공하는 디폴트 메서드의 이점을 여전히 누릴 수 있다. 또한, 골격 구현 클래스를 우회적으로 이용할 수도 있다.
 * 인터페이스를 구현한 클래스에서 해당 골격 구현을 확장한 private 내부 클래스를 정의하고, 각 메서드 호출을 내부 클래스의 인스턴스에 전달하는 것이다.

아이템 18에서 다룬 래퍼 클래스와 비슷한 이 방식을 시뮬레이트한 다중 상속(simulated multiple inheritance)이라 한다.
 * 직접 상속하는 대신 내부 클래스가 상속하도록 만들기 때문에 다중 상속과 같은 효과를 발생시킨다.
 *  따라서 다중 상속의 많은 장점을 제공하는 동시에 단점은 피하게 해준다.

### 골격 구현 작성
골격 구현 작성은 상대적으로 쉽다.
1. 가장 먼저 인터페이스를 잘 살펴 다른 메서드들의 구현에 사용되는 기반 메서드를 선정한다. 이 기반 메서드들은 골격 구현에서는 추상 메서드가 될 것이다.
2. 그다음으로, 기반 메서드들을 사용해 직접 구현할 수 있는 메서드를 모두 디폴트 메서드로 제공한다.  (단, equals와 hashCode 같은 Object의 메서드는 디폴트 메서드로 제공하면 안 된다는 사실을 항상 유념하자.)
3. 만약 인터페이스의 메서드 모두가 기반 메서드와 디폴트 메서드가 된다면 골격 구현 클래스를 별도로 만들 이유는 없다.
4. 기반 메서드나 디폴트 메서드로 만들지 못한 메서드가 남아 있다면, 이 인터페이스를 구현하는 골격 클래스를 하나 만들어 남은 메서드들을 작성해 넣는다.
5. 골격 구현 클래스에는 필요하면 public이 아닌 필드와 메서드를 추가해도 된다.

### [Map.entry 인터페이스]
````java
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V>{

   // 변경 가능한 엔트리는 이 메서드를 반드시 재정의해야 한다.
   @Override public V setValue(V value) {
      throw new UnsupportedOperationException();
   }

   // May.Entry.hashCode 일반 규약을 구현한다.
   @Override public int hashCode() {
      return Objects.hashCode(getKey())
         ^ Objects.hashCode(getValue());
   }

   // May.Entry.equals 일반 규약을 구현한다.
   @Override public boolean equals(Object o) {
      if(o == this) return true;
      if(!(o instanceof Map.Entry)) return false;
      Map.Entry<?,?> e = (Map.Entry) o;
      return Objects.equals(e.getKey(), getKey())
         && Objects.equals(e.getKey(), getValue());
   }

   @Override public String toString() {
      return getKey()+ " = " + getValue();
   }
}
````

단순 구현(simple implementation)은 골격 구현의 작은 변종으로, Abstract.Map.SimpleEntry가 좋은 예다.
 * 단순 구현도 골격 구현과 같이 상속을 위해 인터페이스를 구현한 것이지만, 추상 클래스가 아니란 점이 다르다.
 * 쉽게 말해 동작하는 가장 단순한 구현이다.
 * 이한 단순 구현은 그냥 그대로 써도 되고 필요에 맞게 확장해도 된다.

-----
### 핵심정리
 * 일반적으로 다중 구현용 타입으로는 인터페이스가 가장 적합하다. 복잡한 인터페이스라면 구현하는 수고를 덜어주는 골격 구현을 함께 제공하는 방법을 꼭 고려해보자.
 * 골격 구현은 ‘가능한 한’ 인터페이스의 디폴트 메서드를 제공하여 그 인터페이스를 구현한 모든 곳에서 활용하도록 하는 것이 좋다.
 * ‘가능한 한’이라고 한 이유는 , 인터페이스에 걸려 있는 구현상의 제약 때문에 골격 구현을 추상 클래스로 제공하는 경우가 더 흔하기 때문이다.