# 아이템 5.자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

## 유연하지 못한 정적 유틸리티 클래스
많은 클래스들은 하나 이상의 자원에 의존한다. `이번 핀 발급 미션을 진행 하며 핀을 발급하기 위해서는 핀 번호를 생성하기 위한 클래스에 의존`하도록 구현해야 한다고 판단했다.
<br>

아래는 핀 발급을 자동으로 생성하기 위한 `정적 유틸리티 클래스` 이다.

```java 

public class AutoRandomPinGenerator {

	private AutoRandomPinGenerator() {
	}

	public static List< String > pinGenerator( String preFix, int size ) {
		List< String > pinList = new ArrayList<>();
		StringBuilder pinNo = new StringBuilder();

		for ( int i = 0; i < size; i++ ) {
			Random random = new Random();
			StringBuilder sb = new StringBuilder();
			pinNo = new StringBuilder();

			for ( int j = 0; j < 12; j++ ) {
				int value = random.nextInt( 10 );
				sb.append( value );
			}

			pinNo.append( preFix );
			pinNo.append( sb.toString() );

			pinList.add( pinNo.toString() );
		}

		return pinList;
	}
}

```

Pin 클래스는 12자리의 핀 번호 리스트를 가진 일급 컬렉션이다. Pin 번호를 생성하는 시점에 위에서 작성한 유틸리티 클래스를 활용하여 랜덤으로 번호를 생성한 뒤 활용한다.

```java

public class Pin {
  private final Set< String > pinNumbers;

  public Pin( String prefix, int size) {
    List< String > lists = AutoRandomPinGenerator.pinGenerator( prefix, size );
    this.pinNumbers = lists.stream().map( String::new ).collect( toSet() );

  }

  public Set< String > getLottoNumbers() {
    return Collections.unmodifiableSet(pinNumbers);
  }

}

```

하지만 위와 같은 방법은 자동 생성을 위한 `AutoRandomPinGenerator`에만 의존하고 있다. 만약 요구사항이 추가되어 `핀번호를 수동으로`을 추가해야 한다면 `Pin 클래스를 직접 수정`하여 반영해야 한다. 이것이 의미하는 것은 비즈니스 로직의 핵심 도메인을 수정 해야만 반영이 가능하다는 의미이다.
<br>
이렇게 <b>사용하는 자원에 따라 동작이 달라 지는 클래스에는 정적 유틸리티 클래스나 싱글턴 방식은 적합하지 않다.</b>
<br>

Pin 클래스는 다양한 `Pin 생성 전략`을 가질 수 있어야 한다. 이것을 이뤄내기 위해서는 `인스턴스를 생성할 때 생성자에 필요한 자원을 넘겨주는 방식`을 활용해야 한다.

## 의존 객체 주입
이제 위 예제를 생성자를 통해 의존 객체를 주입하는 방식으로 변경하였다. 우선 번호 생성 전략을 구현하기 위한 `RandomPinGenerator` 인터페이스이다.

```java

public interface RandomPinGenerator {
	List< String > pinGenerator( String preFix, int size );
}


```

추상 메서드를 오직 1개만 가진 인터페이스이기 때문에 `함수형 인터페이스`로 활용이 가능하다. 이제 해당 인터페이스를 구현하여 자동 생성 기능을 작성한다.


```java

public class DIAutoRandomPinGenerator implements RandomPinGenerator {

	public List< String > generator( String preFix, int size ) {
		List< String > pinList = new ArrayList<>();
		StringBuilder pinNo = new StringBuilder();

		for ( int i = 0; i < size; i++ ) {
			Random random = new Random();
			StringBuilder sb = new StringBuilder();
			pinNo = new StringBuilder();

			for ( int j = 0; j < 12; j++ ) {
				int value = random.nextInt( 10 );
				sb.append( value );
			}

			pinNo.append( preFix );
			pinNo.append( sb.toString() );

			pinList.add( pinNo.toString() );
		}

		return pinList;
	}

	@Override
	public List< String > pinGenerator( String preFix, int size ) {
		List< String > lists = generator( preFix, size );
		return lists.stream().limit(size).collect(toList());
	}
}

```

이전과 대부분의 구현은 유사하지만 더 이상 정적으로 `generate` 메서드를 사용하지 않는다. 이제 `DIPIn` 클래스는 생성 시점에 해당 전략을 주입받도록 수정한다.

```java

public class DIPin {
	private static final String DEFAUTL_PREFIX = "8443";
	private static final int DEFAUTL_ISSUE_SIZE = 10;

	private final Set< String > pinNumbers;

	public DIPin( RandomPinGenerator randomPinGenerator ) {
		List< String > lists = randomPinGenerator.pinGenerator( DEFAUTL_PREFIX, DEFAUTL_ISSUE_SIZE );
		this.pinNumbers = lists.stream().map( String::new ).collect( toSet() );
	}

	public Set< String > getPinNumbers() {
		return Collections.unmodifiableSet( pinNumbers );
	}
}

```

## 그래서 무슨 장점이 있는가?
의존 객체 주입 패턴은 해당 객체에게 `유연성을 부여`해주고 `테스트 용이성을 개선` 해준다. 유틸리티 클래스에 의존성을 가진 기존 코드를 테스트 하기 위해서는 랜덤으로 생성되는 핀번호를 활용해야 한다. 이것은 확실한 테스트를 진행하는 방법이 아니다.


## 의존성 주입(Dependency Injection)
의존 객체 주입 방식을 활용한 디자인 패턴으로 의존성 주입(Dependency Injection)이 존재한다. 의존성 주입은 `Spring 프레임워크의 3가지 핵심 프로그래밍 모델` 중 하나이다._(IoC/DI, PSA, AOP)_ 외부에서 `두 객체간의 관계를 결정`해주는 디자인 패턴으로, 인터페이스를 사이에 두어 클래스 레벨에서 의존 관계가 고정되지 않도록 도와준다. 이러한 방식은 `객체의 유연성`을 늘려주고 `객체간의 결합도`를 낮출 수 있는 효과를 가지고 있다.
<br>
