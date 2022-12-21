# 아이템 6. 불필요한 객체 생성을 피하라

* 똑같은 기능의 객체를 매번 생성하기보다는 객체 하나를 재사용하는 편이 나을 때가 많다.
* 특히 불변 객체는 언제든 재사용할 수 있다.

```java
// 잘못된 예시
String s = new String("hello");

// 개선된 버전
String s = "hello"
```

* 새로운 인스턴스를 매번 만드는 대신 하나의 String 인스턴스를 사용한다.
* ``같은 가상 머신 안에서 같은 객체를 재사용함이 보장된다.``(이와 똑같은 문자 리터럴 사용 시)

## 정적 팩토리 메서드를 제공하는 불변 클래스

```java
Boolean(string);
Boolean.valuef(string)
```

* 팩터리 메서드에서는 호출할 때 마다 새로운 객체를 만들지 않는다.
* 불변 객체만이 아니라 가변 객체라 해도 사용 중에 변경되지 않음을 안다면 재사용할 수 있다.

## 정규 표현식을 사용한 메서드 예제

```java
static boolean isRomanNumeral(String s) {
    return s.matches()
    // s.matches() 내부는 다음과 같다.

    // Pattern p = Parttern.compile(REGEX);
    // Matcher m = p.matcher(s);
    // return m.matches();
}
```

이 방식의 문제는 String.matches 메서드를 사용한다는데 있다. ``String.matches는 정규표현식으로 문자열 형태를 확인하는 가장 쉬운 방법이지만, 성능이 중요한 상황에서 반복해 사용하기엔 적합하지 않다.``

* 이 메서드가 내부에서 만드는 정규표현식용 Pattern 인스턴스는 한 번 쓰고 버려져서 곧바로 가비지 컬렉션 대상이 된다.
* Pattern은 입력받은 정규표현식에 해당하는 유한 상태 머신을 만들기 때문에 인스턴스 생성 비용이 높다.

## 성능 개선 방법
* Pattern 인스턴스 클래스 초기화 시 직접 생성해서 캐싱, isRomanNumeral2 메서드가 호출될 때마다 이 인스턴스를 재사용한다.

###  값비싼 객채룰 재사용한 성능 개선

```java
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile(
        "^(?=.)M*(C[MD]|D?C{0,3})"
		+ "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    static boolean isRomanNumeral2(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```

* 이렇게 개선하면 isRomanNumeral2이 번번히 호출되는 상황에서 성능을 상당히 끌어올릴 수 있다.
* 개선 전에서는 존재조차 몰랐던 Pattern 인스턴스의 코드 의미가 훨씬 잘 드러난다.

## 지연 초기화(lazy initialization)

* 개선된 isRomanNumeral3 메서드가 처음 호출 될 때 필드를 초기화하는 지연 초기화로 불필요한 초기화를 없앨 수 있다는 장점이 있다.
* 한 번도 호출하지 않는다면 쓸데 없는 초기화가 되고 코드를 복잡하게 만드는데, 성능은 크게 개선되지 않은 경우가 많기 때문에 권하지는 않는다.

```java
public class Roman {
	private static Pattern romanInstance;
	// 객체 지연 초기화
	static boolean isRomanNumeral3(String s){
	   if(romanInstance== null){
				romanInstance= Pattern.compile(
                    "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";);
	   }
	   return romanInstance.matcher(s).matches();
	}
}
```

## 어댑터를 사용한 불필요한 객체 생성 방지

* 어댑터는 실제 작업은 뒷단 객체에 위임하고, 자신은 제 2의 인터페이스 역할을 해주는 객체다.
* 어댑터는 뒷단 객체만 관리하면 된다.
* 즉, 뒷단 객체 외에는 관리할 상태가 없으므로 뒷단 객체 하나당 어댑터 하나씩만 만들어지면 충분하다.

### Map의 keySet() 어댑터
* Map 인터페이스의 keySet 메서드는 Map 객체 안의 키 전부를 담은 Set 뷰를 반환한다.
* keySet메서드는 호출할 때마다 매번 같은 Set 인스턴스를 반환한다.
* 반환한 객체 중 하나를 수정하면 다른 모든 객체가 따라서 바뀐다. 모두가 똑같은 Map 인터페이스를 대변하기 때문이다.
 
 ```java
Set<Integer> set1 = map.keySet(); // 10
Set<Integer> set2 = map.keySet(); // 10

set1.remove(1);
System.out.println(set1.size()); // 9
System.out.println(set2.size()); // 9
 ```

keySet()으로 반환되는 Set객체가 독립적으로 생성하여 외부의 변경에 영향을 받지 않게 하려면 [Item50] 방어적 복사 방식을 사용하면 된다.

### 기본 타입을 이용한 불필요 객체 생성 방지
* 오토 박싱(auto boxing)은 프로그래머가 기본 타입과 박싱된 기본 타입을 섞어 쓸 때 자동으로 상호 변환해주는 기술이다.
* ``오토 박싱은 기본 타입과 그에 대응하는 박싱된 기본 타입의 구분을 흐려주지만, 완전히 없애주는 것은 아니다.``

```java
private static long sum(){
		long sum = 0L; // Long, long
		for(long i=0; i<=Integer.MAX_VALUE; i++){
			sum += i;
		}
		return sum;
	}
```
sum 변수를 long이 아닌 Long으로 선언해서 불필요한 Long 인스턴스가 2^31개나 만들어진 것

Long으로 실행할 경우 [5391ms] long으로 실행할 경우 [861ms]  (약 6.2배 차이)

* ``박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의하자.``

# 정리
* 요즘 JVM에서는 작은 객체 생성, 회수에 대해 부담이 적다.
* 객체 생성은 비싸니 피해야 한다가 아닌 프로그램의 명확성, 간결성, 기능에 필요한 객체라면 오히려 좋은 일이다.

## 객체 풀 만들지말자
* 단순히 객체 생성을 피하고자 객체 풀(pool)을 만들지 말자.
* 코드를 헷갈리게 만들고 메모리 사용량은 늘고 성능이 떨어짐(DB 경우 제외)
* JVM의 가비지 컬렉터는 잘 최적화되어 직접 만든 객체 풀보다 빠르다.

## 불필요한 객체 생성 방지 vs 방어적 복사
* 이번 아이템은 방어적 복사(defensive copy)를 다루는 아이템50과 대조적이다.
* 이번 아이템이 ``"기존 객체를 재사용해야 한다면 새로운 객체를 만들지 마라"``
* 아이템 50은 ``"새로운 객체를 만들어야 한다면 기존 객체를 재사용하지 마라"``
