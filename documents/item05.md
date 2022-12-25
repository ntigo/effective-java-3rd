# 아이템 5.자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

## 유연하지 못한 정적 유틸리티 클래스
많은 클래스들은 하나 이상의 자원에 의존한다. `이번 미션을 진행 하며 사전을 이용해서 맞춤법 검사기를 만든다고 했을 때 맞춤법 검사기는 사전에 의존`하도록 구현해야 한다고 판단했다.
<br>

아래는 맞춤법 검사기 `SpellChecker`는 사전`Dictionary`에 의존하고 있는 `정적 유틸리티 클래스` 이다.

```java 

public class SpellChecker {
    // Dictionary 객체가 고정 -> 변경이 어렵움
    private static final Lexicon dictionary = new KoreaDictionary();

    private SpellChecker() {
        // 객체 생성 방지
    }

    public static boolean isValid(String word) { return true; }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }

    // 실행
    public static void main( String[] args ) {
        // 정적 유틸리티 클래스 구현
        boolean isChecked = SpellChecker.isValid("hello");
        System.out.println("isChecked = " + isChecked);
    }
}

```
정적 유틸리티 클래스로 구현 시 `KoreaDictionary` 객체로 고정되어 있기 때문에 다른 객체 (`EnglishDictionary`) 와 같은 객체로 변경하기 쉽지 않다.<br>
자연스럽게 코드는 유연하지 않을 뿐 아니라 테스트시에도 객체 변경이 자유롭지 못해 테스트 하기 어려운 문제도 가지고 있다.

## SingleTon 구현
ITEM3에서 언급한 Singleton 으로 구현한다면 괜찬을까? 한번 살펴보자 

```java

public class SpellChecker_V2 {

    //Singleton Instance 생성
    public static final SpellChecker_V2 INSTANCE = new SpellChecker_V2();
    private SpellChecker_V2() {
        //객체 생성 방지
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }

    // 실행
    public static void main( String[] args ) {
        // Singleton 구현
        boolean isChecked_V2 = SpellChecker_V2.INSTANCE.isValid("hello");
        System.out.println("isChecked_V2 = " + isChecked_V2);
    }
}

```

Singleton 구현도 다른 객체를 대체하기가 쉽지 않은 구조로 되어있다. 책에서 표시하길 `사용하는 자원에 따라 동작이 달라지는 클래스에는 정적 유틸리티 클래스나 싱글톤 방식이 적합하지 않다`고 언급하고 있다.

## 의존 객체 주입
제시하는 해결책으로는 인스턴스 생성 시 생성자에 필요한 자원을 넘겨주는 의존 객체 주입방식을 소개하고 있다. 아래 코드를 살펴보자.

```java

public class SpellChecker_V4 {

    private final Lexicon dictionary;

    public SpellChecker_V4(Supplier<Lexicon> dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary.get());
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }

    // 실행
    public static void main( String[] args ) {
        // 의존성 구현
        SpellChecker_V3 spellChecker_V3 = new SpellChecker_V3(new EnglishDictionary());
        boolean isChecked_V3 = spellChecker_V3.isValid("hello");
        System.out.println("isChecked_V3 = " + isChecked_V3);
    }
}



```

자원을 사용하는 `Main Method`에서 생성하여 클래스에 넘겨주는 방식 `생성자로 주입`이다 여기서의 의존 관계는 객체의 사용관계라 생각하면 된다.<br>
이런 구조라면 `SpellChecker`클래스를 수정하지 않고 여러 `Dictionary`객체를 변경할 수 있게 된다.

## 변형 패턴인 팩토리 메서드 패턴
생성자에 자원 팩토리를 넘겨주는 방식이다. 팩토리란 호출할 때마다 특정 타입의 인스턴스를 반복해서 만들어주는 객체를 말한다.<br>
팩토리라는 이름에서 알 수 있듯이 객체를 생산하는 공장이라 생각하면 이해가 쉽다. 팩토리라는 이름에서 알 수 있듯이 객체를 생산하는 공장이라 생각하면 이해가 쉽다.<br>
자바8에서 이러한 팩토리 메서드를 표현한 <b>`Supplier<T>`</b> 인터페이스가 있다.

```java

public class SpellChecker_V4 {

    private final Lexicon dictionary;

    public SpellChecker_V4(Supplier<Lexicon> dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary.get());
    }

    public static boolean isValid(String word) {
        return true;
    }

    public static List<String> suggestions(String typo) {
        return new ArrayList<>();
    }

    // 실행
    public static void main( String[] args ) {
        // Factory Method 패턴
        Supplier<Lexicon> dictionary = new Supplier<Lexicon>() {
            @Override
            public Lexicon get() {
                return new KoreaDictionary();
            }
        };
        SpellChecker_V4 spellChecker_V4 = new SpellChecker_V4(dictionary);
        boolean isChecked_V4 = spellChecker_V4.isValid("hello");
        System.out.println("isChecked_V4 = " + isChecked_V4);
    }
}

```

`Supplier<Lexicon>` 은 메서드 레퍼런스(`람다`) 형태로 표현 가능하다. 대형 프로젝트에서는 스프링(`Spring`), 대거(`Dagger`)와 같은 의존 객체 주입 프레임워크를 활용해 보는 것도 좋은 대안이 될 수 있다.

## 그래서 무슨 장점이 있는가?
의존 객체 주입 패턴은 해당 객체에게 `유연성을 부여`해주고 `테스트 용이성을 개선` 해준다.

## 의존 객체 주입의 단점은?
의존 객체 주입이 유연성과 테스트 용이성을 개선해주지만, 의존성이 수 천 개나 되는 큰 프로젝트에서는 코드를 어지럽게 만들 수 있다.


## 의존성 주입(Dependency Injection)
의존 객체 주입 방식을 활용한 디자인 패턴으로 의존성 주입(Dependency Injection)이 존재한다. 의존성 주입은 `Spring 프레임워크의 3가지 핵심 프로그래밍 모델` 중 하나이다._(IoC/DI, PSA, AOP)_ 외부에서 `두 객체간의 관계를 결정`해주는 디자인 패턴으로, 인터페이스를 사이에 두어 클래스 레벨에서 의존 관계가 고정되지 않도록 도와준다. 이러한 방식은 `객체의 유연성`을 늘려주고 `객체간의 결합도`를 낮출 수 있는 효과를 가지고 있다.
<br>
