# 아이템 2. 생성자에 매개변수가 많다면 빌더를 고려하라.

정적 팩터리 메소드와 생성자는 공통적인 제약이 있음.
선택적 매개변수가 많을 경우 표현 방식의 유연성이 떨어지고 대응하기 어려움.

점층적 생성자 패턴(Telescoping Constructor pattern), 자바빈즈 패턴(JavaBeans Pattern) 을 사용할 수 있으나 사용성에 아래 제약이 따름.

### 점층적 생성자 패턴 (Telescoping Constructor Pattern)
* 가독성이 떨어짐
* 코드 작성 시 마다 의미 인식이 필요
* 프로그램 유지 보수의 문제

### 자바빈즈 패턴 (JavaBeans Pattern)
* 콜스택의 낭비 (하나의 객체 생성을 위해 여러 메소드를 호출해야 함)
* 일관성(Consistency)이 무너짐
* 클래스를 불변으로 만들 수 없음 (단, freezing 을 이용할 수 있음)
* thread safety 할 수 있도록 별도의 조정이 필요

객체의 불변성(Immutable)을 보장해야 하고, 직관적으로 이해할 수 있는 코드가 필요하면 아래가 대안이 될 수 있음.

### 빌더 패턴 (Builder Pattern)
* 직관적으로 이해할 수 있다.
* 일관성(Consistency)을 보장한다.
* 객체를 불변(Immutable)으로 만들 수 있다.
* Method Chaining(Fluent API) 을 구현할 수 있다.
* 단, 코드량이 많아지고 구현 및 이해 난이도가 올라가는 문제가 있다.

### 롬복 빌더 (Lombok's Builder)
* Boilerplate 코드를 효과적으로 줄일 수 있다.
* 필수 값을 제어하기 위한 조치가 필요하다. (아래 예제는 RunTime 중 Exception 으로만 확인 가능)
* Lombok 의 제작자가 밝힌 것과 같이 Hack 코드로 언제 사용이 중지될지 모른다.

```java 
@Builder(builderMethodName = "Builder")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class LMember {
    @NonNull
    private final String name; // Requirement
    @NonNull
    private final String birth; // Requirement

    private final String bloodType;
    private final int tall;
    private final String cellular;
}

```
