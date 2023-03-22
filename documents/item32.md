# 아이템 32. 제네릭과 가변인수를 함께 쓸 때는 신중하라

## 가변인수(varargs) 메서드 란?

메서드에 넘기는 인수의 개수를 클라이언트가 조절할 수 있게 하는 것 (네릭과 같이 자바 5때 함께 추가됨)

---
하지만, 가변인수 메서드를 호출하면 가변인수를 담기 위한 배열이 자동으로 만들어지는데 이 때 varargs 매개변수에 제네릭이나 매개변수화 타입이 포함되면 아래와 같은 컴파일 경고가 발생한다. <br /> 
(제네릭과 매개변수화 타입은 실체화 되지 않으므로 )

```java
warning : [unchecked] Possible heap pollution from
parameterized vararg type List<String>
```

## 제네릭과 varargs을 혼용하면 타입 안전성이 깨진다

매개변수화 타입의 변수가 타입이 다른 객체를 참조하면 힙 오염이 발생한다.

```java
static void dangerous(List<String>... stringLists){
List<Integer> intList = List.of(42);
Object[] objects = stringLists;
objects[0] = intList; // 힙 오염 발생
String s = stringLists[0].get(0); // ClassCastException
}
```
마지막 줄에 컴파일러가 생성한 (보이지 않는) 형변환이 숨어 있기 때문이다. 이처럼 타입 안전성이 깨지니 `제네릭 varargs 배열 매개변수에 값을 저장하는 것은 안전하지 않다.`

## 제네릭 varargs 매개변수를 받는 메서드를 선언할 수 있게 한 이유는?

```java
// 제네릭 배열 - 생성 불가 컴파일 에러
static void dangerous_item28(List<String>[] stringLists){
List<Integer> intList = List.of(42);
// ...
}

// 제네릭 가변인수 - heap pollution 경고
static void dangerous(List<String>... stringLists){
List<Integer> intList = List.of(42);
// ...
}
```

제네릭이나 매개변수화 타입의 varagrs 매개변수를 받는 메서드는 실무에서 매우 유용하기 때문에, 모순을 허용하였다.

`대표적인 자바 라이브러리 매서드`
 - Arrays.asList(T... a) <br />
 - Collections.addAll(Collection<? super T> c, T... elements) <br />
 - Enumset.of(E first, E...rest) <br />

## @SafeVarargs 애너테이션 - 메서드 타입 안전함을 보장하는 장치
`자바 7 전 -> @SuppressWarnings(”unchecked”)`
 - 지루한 작업이며 가독성을 떨어뜨리고, 때로는 진짜 문제를 알려주는 경고마저 숨기는 안 좋은 결과

`자바 7 -> @SafeVarargs`
 - 메서드 작성자가 그 메서드가 타입 안전한지 보장할 수 있게 되었다.(컴파일러 경고 X)

### 메서드가 안전한지 확신하는 방법

---

1) varargs 매개변수 배열에 아무것도 저장하지 않는다.
2) varargs 매개변수 배열 혹은 복제본의 참조가 밖으로 노출되지 않는다. (신뢰할 수 없는 코드가 배열에 접근하지 않는다)

## 힙 오염 전염 - 타입 안전성 깨짐 주의

`[ 자신의 제네릭 매개변수 배열의 참조를 노출한다. - 안전하지 않다! ]`
```java
static <T> T[] toArray(T... args){
return args;
}
```
 - 컴파일러는 toArray에 넘길 T 인스턴스 2개를 담을 varargs 매개변수 배열을 만드는 코드를 생성
 - pickTwo에 어떤 타입의 객체를 넘기더라도 담을 수 있는 가중 구체적인 타입이기 때문에 이 코드가 만드는 배열의 타입은 Object[]
 - toArray가 돌려준 이 배열이 그대로 pickTwo를 호출한 클라이언트까지 전달
 - pickTwo는 항상 Object[] 타입 배열을 반환
```java
static <T> T[] pickTwo(T a, T b, T c){
switch (ThreadLocalRandom.current().nextInt(3)){
case 0: return toArray(a, b);
case 1: return toArray(a, c);
case 2: return toArray(b, c);
}
throw new AssertionError(); // 도달할 수 없다.
}
```
컴파일러는 pickTwo의 반환값을 attributes에 저장하기 위해 String[]으로 형변환하는 코드를 자동 생성하게 되고, Object[]는 String[]의 하위 타입이 아니므로 이 형변환은 실패하게 된다.

```java
public static void main(String[] args) {
String[] attributes =pickTwo("good", "fast", "cheap");
}
```

## 제네릭 varargs 매개변수 배열에 다른 메서드가 접근해도 안전한 경우

아래 예는 `제네릭 varargs 매개변수 배열에 다른 메서드가 접근하도록 허용하면 안전하지 않다`는 점
- 예외사항
1) @SafeVarargs로 제대로 에노테이트된 또 다른 varargs 메서드에 넘기는 것은 안전하다.
2) 그저 이 배열 내용의 일부 함수를 호출만 하는 (varargs를 받지 않는) 일반 메서드에 넘기는 것도 안전하다.

```java
@SafeVarargs
static <T> List<T> flatten(List<? extends T>... lists){
List<T> result = new ArrayList<>();
for(List<? extends T> list : lists){
result.addAll(list);
}
return result;
}
```

## @SafeVarargs 애너테이션 사용 규칙
`제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 모든 메서드에 @SafeVarargs를 달라` <br /> 
이 말은 안전하지 않은 varargs 메서드는 절대 작성해서는 안 된다는 뜻 <br /> 
정리하자면, 다음 두 조건을 모두 만족하는 제네릭 varargs 메서드는 안전하다.
 - varargs 매개변수 배열에 아무것도 저장하지 않는다.
 - 그 배열(혹은 복제본)을 신뢰할 수 없는 코드에 노출하지 않는다.

### @SafeVarargs가 유일한 답은 아니다 - 타입 교체하기

---

`[ 제네릭 varargs 매개변수를 List로 대채한 예 - 타입 안전하다. ]`
```java
static <T> List<T> flatten_typesafe(List<List<? extends T>> lists){
List<T> result = new ArrayList<>();
for(List<? extends T> list : lists){
result.addAll(list);
}
return result;
}

List<String> friends = List.of("friend");
List<String> romans = List.of("abc");
List<String> countrymen = List.of("zzz");
List<List<String>> audience = flatten(List.of(friends, romans, countrymen));
```

`위 코드는 List.of에도 @SafeVarargs가 달려있기 떄문에 가능` <br /> 
 장점
 - 컴파일러가 이 메서드의 타입 안전성을 검증
 - @SafeVarargs 애너테이션을 우리가 직접 달지 않아도 된다
 - 실수로 안전하다고 판단할 걱정도 없다

단점
 - 클라이언트 코드가 살짝 지저분해지고 속도가 조금 느려질 수도 있다

이 방식을 pickTwo에 적용하면
```java
static <T> List<T> pickTwo(T a, T b, T c){
switch (ThreadLocalRandom.current().nextInt(3)){
case 0: return List.of(a, b);
case 1: return List.of(a, c);
case 2: return List.of(b, c);
}
throw new AssertionError();
}

List<String> attributes =pickTwo("good", "fast", "cheap");
```

## 핵심 정리

---
가변인수와 제네릭은 궁합이 좋지 않다. <br /> 
가변인수 기능은 배열을 노출하여 추상화가 완벽하지 못하고, 배열과 제네릭의 타입 규칙이 서로 다르기 때문이다. <br />
제네릭 varargs 매개변수는 타입이 안전하지는 않지만, 허용된다. <br />
메서드에 제네릭(혹은 매개변수화된) varargs 매개변수를 사용하고자 한다면, 먼저 그 메서드가 타입 안전한지 확인한 다음 @SafeVarargs 애너테이션을 달아 사용하는데 불편함이 없게끔 하자.

