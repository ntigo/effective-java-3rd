# 아이템 28. 배열보다는 리스트를 사용하라

## Part 1. 배열과 리스트의 특징

### 1. 배열과 리스트
배열
> [1] 실체화 타입  
> [2] 공변

리스트
> [1] 제네릭  
> [2] 소거  
> [3] 실체화 불가 타입  
> [4] 불공변  
> : C#과 Java의 차이점

### 2. 실체화 타입이란?
컴파일타임과 런타임에 동일한 타입 정보를 가지는 타입  
반대로 실체화 불가 타입이란, 런타임에는 타입 정보가 `소거`되어 컴파일타임보다 타입 정보를 적게 가지는 타입
> [1] 실체화 타입  
> : int, byte[], List<?>  
> [2] 실체화 불가 타입  
> : List&lt;String&gt;, Map&lt;String, Object&gt;

### 3. 공변이란?
상위 타입으로 변경을 허용하는 특징  
반대로 불공변이란, 타입의 상속 관계와 상관없이 변경을 허용하지 않는 특징
```java
// 공변
Object[] objectArray = new Long[1];
objectArray[0] = "타입이 달라 넣을 수 없다.";

// 불공변
List<Object> ol = new ArrayList<Long>();
ol.add("타입이 달라 넣을 수 없다.");
```

## Part 2. 배열과 리스트 혼용시 문제점

### 1. 잘 어우러지지 못한다
매우 다른 타입 규칙 적용  
> [1] 배열은 공변이고 실체화되며, 런타임에 타입 안전하다  
> : 런타임에 ArrayStoreException 및 ClassCastException 발생 가능  
> [2] 리스트(제네릭)는 불공변이고 실체화 불가하며, 컴파일타임에 타입 안전하다  
> : 타입 안전성과 상호운용성이 좋음

### 2. 혼용 케이스
제네릭 배열 생성   
> [1] 문제점  
> : 보통의 경우 제네릭 배열 생성 불가하며, 우회 가능하나 타입 안전성 보장 필요  
> [2] 우회 방법  
> ```java
> public class GenericArray<E> {
>   private final E[] genericArray;
> 
>   @SuppressWarnings("unchecked")
>   public GenericArray( int size ) {
>       genericArray = (E[]) new Object[size];
>   }
> 
>   public E get( int index ) {
>       return genericArray[index];
>   }
> 
>   public void set( int index, E element ) {
>       genericArray[index] = element;
>   }
>   //...
> }
> ```

제네릭 타입의 가변인수 메서드
> [1] 문제점  
> : 실체화 불가 관련 경고 발생, 우회 가능하나 타입 안정성 보장 필요  
> [2] 우회 방법  
> : @SafeVarags 어노테이션

### 3. 제네릭 배열을 만들지 못하게 막은 이유
타입 안전하지 않아 `ClassCastException` 발생 가능
> 제네릭 배열 사고 실험 예제
> ```java
> List<String>[] stringLists = new List<String>[1];
> List<Integer> intList = List.of(42);
> Object[] objects = stringLists;
> objects[0] = intList;
> String s = stringLists[0].get(0);
> ```

### 4. 혼용 문제 해결 방안
제네릭 배열 생성 오류나 비검사 형변환 경고가 뜨는 경우, 배열인 E[] 대신 컬렉션인 List&lt;E&gt;를 사용하여 해결

## Part 3. 리스트 사용 방법

### 1. 배열 대신 리스트를 사용하기
Chooser 클래스 구현 실습
> [1] 제네릭을 쓰지 않고 구현  
> [2] 제네릭 타입으로 변경  
> [3] 제네릭 배열로 변경  
> [4] 제네릭 타입 또는 매개변수화 타입의 리스트로 변경

## 결론
> 배열과 제네릭에는 매우 다른 타입 규칙이 적용된다. 배열은 공변이고 실체화되는 반면, 제네릭은 불공변이고 타입 정보가 소거된다.
> 그 결과 배열은 런타임에는 타입 안전하지만 컴파일타임에는 그렇지 않다. 제네릭은 반대다. 그래서 둘을 섞어 쓰기란 쉽지 않다.
> 둘을 섞어 쓰다가 컴파일 오류나 경고를 만나면, 가장 먼저 배열을 리스트로 대체하는 방법을 적용해보자.
