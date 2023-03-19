
# 아이템31.한정적 와일드 카드를 사용해 API 유연성을 높이라

### 공변 불공변 
공변  
타입 간의 상속 관계가 있는 경우를 말합니다.

불공변   
불공변성은 타입 간의 상속 관계가 없는 경우를 말합니다.

### 한정적 와일드 카드가 나온 이유
```java
public void pushAll(Iterable<E> src){
    for(E e : src)
        push(e)
}

public void popAll(Collection<E> dst){
    while(!isEmpty())
        dst.add(pop())
}
```
제네릭의 매개변수화 타입은 불공변이다. 그래서 상속관계를 이용하지 못한다. 


### 한정적 와일드 카드

|     와일드카드     | 네이밍 | 설명                             |
|:-------------:|-----|--------------------------------|
|      <?>      | Unbounded wildcards | 제한 없음 (모든 타입이 가능)              |
| <? extends E> | Upper Bounded Wildcards | 상한 경계 와일드카드 (E와 E를 상속한 클래스 허용) |
|  <? super E>  | Lower Bounded Wildcards | 하한 경계 와일드카드  (E와 E를 상속된 클래스 허용) |

```java
public void pushAll(Iterable<? extends E> src){
    for(E e : src)
        push(e)
}

public popAll(Collection<? super E> dst){
        while(!isEmpty())
        dst.add(pop())
        }
```

### PECS(Producer-Extends, Consumer-Super) 공식
PECS: Producer-Extends, Consumer-Super의 줄임말이다.  
와일드 카드 타입의 객체를 생성할때는 extends  
와일드 카드 타입의 객체를 사용 또는 소비할때는 super 



