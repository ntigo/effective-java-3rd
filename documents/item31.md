
# 아이템31.한정적 와일드 카드를 사용해 API 유연성을 높이라

### 불공변 방식의 유연하지 못한 점

불공변성은 제네릭 타입 매개변수가 정확히 일치해야만 하는 것을 의미합니다. 
즉, 제네릭 타입 매개변수가 서로 다른 타입일 경우, 컴파일 오류가 발생합니다. 
이러한 불공변성은 제네릭 API의 유연성을 제한하는데, 이는 불필요한 제약으로 작용할 수 있습니다.

### 한정적 와일드 카드
한정적 와일드 카드는 제네릭 타입 매개변수를 선언할 때 사용되며, 해당 매개변수의 타입이 특정 상한선을 가지는 경우 사용할 수 있습니다. 이를 통해 API를 보다 유연하게 만들 수 있습니다.


|     와일드카드     | 네이밍 | 설명                                             |
|:-------------:|-----|------------------------------------------------|
|      <?>      | Unbounded wildcards | 제한 없음 (모든 타입이 가능)                              |
| <? extends E> | Upper Bounded Wildcards | 상한 경계 와일드카드 (해당 타입 파라미터가 받아들일 수 있는 최대 타입을 지정)  |
|  <? super E>  | Lower Bounded Wildcards | 하한 경계 와일드카드  (해당 타입 파라미터가 받아들일 수 있는 최소 타입을 지정) |


### PECS(Producer-Extends, Consumer-Super) 공식
PECS: Producer-Extends, Consumer-Super의 줄임말이다.  
와일드 카드 타입의 객체를 생성할때는 extends  
와일드 카드 타입의 객체를 사용 또는 소비할때는 super 

### 와일드 카드 사용시 주의 사항
```java
   public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }
```
반환 타입은 여전히 Set<E>임을 주목해야합니다. 반환타입에 한정적 타입을 사용을 하면 안됩니다.
유연성을 높여기보다 클라이언트 코드에서도 와일드카드를 써야되기 때문입니다.

### Comparator와 Comparable은 소비자 

Comparable은 안에 있는 값을 꺼내어 비교하기 때문에 언제나 소비자이므로, 일반적으로 Comparable<E>보다는 Comparable<? super E>를 사용하는 편이 낫다.
Comparator도 마찬가지다.  
일반적으로 Comparator보다는 Comparator<? super E>를 사용하는 편이 낫다.

### 메서드를 정의할 때는 타입 매개변수와 와일드카드 중 어떤 것을 사용해도 괜찮을 때가 많다.

```java
  public static <E> void swap(List<E> list, int i, int j);

    public static void swap(List<?> list, int i, int j);
```
타입 매개변수와 와일드카드는 서로 공통되는 부분이 있기 때문에 두 방식 모두 괜찮다.  
public API라면 간단한 두 번째가 낫다. 신경 써야 할 타입 매개변수도 없다.   따라서, 기본 규칙은 이렇게 말할 수 있다.
메서드 선언에 타입 매개변수가 한 번만 나오면 와일드 카드로 대체하라.   
비한정적 타입 매개변수라면 비한정적 와일드카드로 바꾸고, 한정적 타입 매개변수라면 한정적 와일드카드로 바꾸면 된다.

단, List<?>라는 타입의 리스트에는 null 외에는 어떤 값도 넣을 수 없다. 이 경우, 도우미 메서드를 따로 작성하여 활용하는 방법으로 사용할 수 있다. 이때, 실제 타입을 알아내려면 이 도우미 메서드는 제네릭이어야 한다.
