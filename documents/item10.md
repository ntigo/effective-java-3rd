# 아이템 10. equals는 일반 규약을 지켜 재정의 하라.

### 다음 열거 상황 중 해당 시 재정의하지 않는 것이 최선이다.
* 각 인스턴스가 본질적으로 고유하다. (Thread)
  * 값이 아닌 동작하는 개체를 표현하는 클래스
* 인스턴스의 '논리적 동치성'을 검사할 일이 없다. (java.utils.regex.Pattern의 equals)
  * 이 경우 정규식이 일치하는지 확인하도록 수정가능하나, 필요없다면 기본 equals로 가능.
* 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다. (Set, Map, List의 경우)
  * Abstract(Type)의 equals 사용
* 클래스가 private 이거나 package-private이고 equals 메서드를 호출할 일이 없다.

### equals를 재정의 해야할 때. 
* 객체 식별성이 아닌 논리적 동치성을 확인해야 하는데, `상위 클래스의 equals가 논리적 동치성을 비교하도록 재정의되지 않았을 때`( 주로 값 클래스 )
* 값 클래스라 해도 값이 같은 인스턴스가 둘 이상 만들어지지 않음을 보장하는 인스턴스 통제 클래스라면 재정의하지 않아도 된다.
* Enum의 경우, 논리적으로 같은 인스턴스가 2개이상 만들어지지 않으니, 논리적 동치성과 객체 식별성이 사실상 똑같은 의미가 된다.



equals 메서드를 재정의할 때는 반드시 일반 규약을 따라야 한다.
### Object 명세에 적힌 규약.
### equals 메서드는 동치관계를 구현하며, 다음을 만족한다.
동치관계?
집합을 같은원소로 부분집합으로 나누는 연산에서 서로 교환 가능한 같은 원소끼리의 관계
* 반사성(reflexivity): null이 아닌 모든 참조 값 x에 대해, `x.equals(x)는 true다.`
  - 객체는 자기 자신과 같아야 한다는 뜻이다.
* 대칭성(symmetry): null이 아닌 모든 참조 값 x, y에 대해, `x.equals(y)가 true이면, y.equals(x)도 true이다.`
  - 두 객체는 서로에 대한 동치 여부에 똑같이 답해야 한다는 뜻이다.
* 추이성(transitivity): null이 아닌 모든 참조 값 x, y, z에 대해, `x.equals(y)가 true이고, y.equals(z)가 true이면 x.equals(z)도 true이다.`
  - 첫 번째 객체와 두 번째 객체가 같고, 두 번째 객체와 세 번째 객체가 같다면, 첫 번째 객체와 세 번째 객체도 같아야 한다는 뜻이다.
* 일관성(consistency): null이 아닌 모든 참조 값 x, y에 대해, `x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.`
  - 두 객체가 같다면 둘 중 어느 하나가 수정되지 않는 한, 앞으로도 영원히 같아야 한다는 뜻이다.
* null-아님: null이 아닌 모든 참조 값 x에 대해, `x.equals(null)은 false다`
  - 모든 객체가 null과 같지 않아야 한다는 뜻이다.

이 규약을 어기면 프로그램이 이상동작을 하거나 종료되고, 원인 코드를 찾기가 어려워진다.

수많은 클래스는 전달받은 객체가 equals 규약을 지킨다고 가정하고 동작한다.

```java
// 대칭성이 위배된 잘못된 코드
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString( String s ) {
        this.s = Objects.requreNonNull(s);
    }

    // 대칭성 위배
    @Override public boolean equals(Object o) {
        if ( o instanceof CaseInsensitiveString )
            return s.equalsIgnoreCase( ( (CaseInsensitiveString)o).s );
        if( o instanceof String)    // 한 방향으로만 작동
            return s.equalsIgnoreCase((String) o);

        return false;
    }
    ...
}
```
`equals 규약을 어기면 그 객체를 사용하는 다른 객체들이 어떻게 반응할지 알 수 없다.`

### 추이성 예제
```java
// 대칭성 위배
@Override public boolean equals(Object o) {
       // ColorPoint일때만 수행
       if(!(o instanceof ColorPoint))
           return false;

       return super.equals(o) && this.color == ((ColorPoint) o).color;
   }
```
Point와 ColorPoint equals 결과가 다를 수 있음


```java
// 추이성 위배
@Override public boolean equals(Object o) {
        if(!(o instanceof Point))
            return false;

        // o가 일반 Point면 색상은 무시, x,y만 비교
        if(!(o instanceof ColorPoint))
            return o.equals(this);

        // o가 ColorPoint면 색상까지 비교
        return super.equals(o) && this.color == ((ColorPoint) o).color;
    }
```
대칭성은 지켜주지만 추이성이 깨진다.
다른 타입의 사용자 클래스와 equals 동작 시 무한재귀에 빠질 위험도 있음.

instanceof 검사를 getClass로 바꾸면 equals 규약을 만족시킬까.
```java
// 리스코프 치환 원칙 위배
@Override public boolean equals(Object o) {
    if(o == null || o.getClass() != this.getClass()) {
        return false;
    }

    Point p = (Point) o;
    return this.x == p.x && this.y == p.y;
}
```
리스코프 치환원칙
- 상위 클래스의 하위 클래스는 여전히 상위 클래스이므로, 상위 클래스의 모든 메서드가 하위 클래스에서도 동일하게 작동해야한다.

###  우회 방법
- 상속 대신 컴포지션을 사용하라.(아이템18)

#### equals 규약 지키면서 값 추가하기
```java
public class ColorPoint {
  private Point point;
  private Color color;

  public ColorPoint(int x, int y, Color color) {
    this.point = new Point(x, y);
    this.color = Objects.requireNonNull(color);
  }

  public Point asPoint() {
    return this.point;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof ColorPoint)) {
      return false;
    }

    ColorPoint cp = (ColorPoint) o;
    return this.point.equals(cp) && this.color.equals(cp.color);
  }
}
```

### 일관성
- 두 객체가 같다면 앞으로도 영원히 같아야 한다.
- 가변객체는 비교시점에 따라 서로 달라질 수 있지만 불변객체라면 한번 다르면 끝까지 달라야 한다.
- 그러나 신뢰할 수 없는 자원이 끼어들면 안된다.
- java.net.URL 클래스는 매핑된 host의 IP주소를 이용해 equals를 비교.
```java
        URL url1 = new URL("http://google.com");
        URL url2 = new URL("http://google.com");

        System.out.println(url1.equals(url2) );
        System.out.println(url2.equals(url1) );
```

### null 아님
- 모든 객체는 null과 같지 않아야 함.
```java
@Override 
public boolean equals(Object o) {
  if(o == null) //명시적 null검사는 불필요
      return false;
        ...
}
```
equals 비교를 하려면 파라미터의 올바른 타입 검사부터 해야 한다.
```java
//묵시적 null검사
@Override
public boolean equals(Object o) {
  if(!(o instanceof MyClass)) 
      return false;
  MyClass myClass = (MyClass) o;
  return this.member == myClass.member;
}
```

### 양질의 equals 구현 방법
- == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
- instanceof 연산자로 입력이 올바른 타입인지 확인한다.
- 입력을 올바른 타입으로 형변환한다.
- 입력 객체와 자기 자신의 대응되는 '핵심' 필드들이 모두 일치하는지 하나씩 검사한다.
- float, double을 제외한 기본타입은 == 연산자로 비교한다.
- float, double은 Float.compare(float, float)와 Double.compare(double, double)로 비교한다.

### equals를 다 구현했다면 세 가지를 자문하자.
- 대칭적인지, 추이성이 있는지, 일관적인지?

### 주의사항
- equals를 재정의할 땐 hashCode도 반드시 재정의하자.(아이템11)
- 너무 복잡하게 해결하려 들지 말자.
- Object외의 타입을 매개변수로 받는 equals 메서드는 선언하지 말자.
- 꼭 필요한 경우에만 재정의하자.



