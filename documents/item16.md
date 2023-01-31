# 아이템 16.public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

## 캡슐화의 이점을 제공하지 못하는 클래스
데이터 필드에 직접 접근하여 수정이 가능하다. 따라서 `캡슐화의 이점`을 제공하지 못한다.

```java 

class Point {
    public double x;
    public double y;
}

```

## 객체 지향 방식으로 캡슐화 한 클래스
필드들을 모두 `private`으로 변경<br/>
public `접근자(getter)`, `변경자(setter)` 추가

```java

class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getx(){
        return this.x;
    }

    public double gety(){
        return this.y;
    }

    public void setx(double x){
        this.x = x;
    }

    public void sety(double y){
        this.y = y;
    }
}

```
이렇게 패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써 클래스 내부 변경을 `유연하게` 할 수 있다.<br/>
`package-priavte` 클래스 혹은 `private` 중첩클래스라면 데이터 필드를 노출한다 해도 문제가 되지 않는다<br/>

## package-private 클래스

```java

class Point {
    public double x;
    public double y;

}

```
해당 클래스가 package-private 접근자로 생성되었기에 같은 패키지 안에서만 값 접근 및 변경이 가능하다.<br/>

## private 중첩 클래스

```java

class Point {
    private double x;
    private double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void changeXY (double x, double y){
        this.x = x;
        this.y = y;
    }
}

```
`private` 중첩 클래스임으로 같은 패키지 내에서만 해당 클래스 접근이 가능하면 값 변경 시 생성한 객체를 통해서만 접근 및 변경이 가능하다.<br/>
그래서 `package-private` 클래스보다 `private` 중첩 클래스가 더 제한적이다.

## 자바 플랫폼 라이브러리에서 필드를 노출시킨 사례
자바플랫폼 라이브러리에도 public 클래스의 필드를 직접 노출하지 말라는 규칙을 어긴 사례가 종종 있다.<br/>
대표적인 예로 java.awt 의 Point, Dimension 클래스이다.

```java

public class Point extends Point2D implements java.io.Serializable {
    /**
     * The X coordinate of this <code>Point</code>.
     * If no X coordinate is set it will default to 0.
     *
     * @serial
     * @see #getLocation()
     * @see #move(int, int)
     * @since 1.0
     */
    public int x;

    /**
     * The Y coordinate of this <code>Point</code>.
     * If no Y coordinate is set it will default to 0.
     *
     * @serial
     * @see #getLocation()
     * @see #move(int, int)
     * @since 1.0
     */
    public int y;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -5276940640259749850L;
}
```
이렇게 public으로 클래스 필드를 선언하면 성능이슈를 이르킨다. 

## 불변 필드를 public으로 선언

```java

public final class Time {
    private static final int HOUR_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute){
        if (hour < 0 || hour >= HOURS_PER_DAY)
            throw new IllegalArqumentException("시간 : "+hour);
        if (minute < 0 || minute >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("분 : "+minute);

        this.hour = hour;
        this.minute = minute;

    }
}

```

## 불변 필드를 노출한 public 클래스
유효한 시간 표현을 보장하는 클래스
```java

public class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour > HOURS_PER_DAY) {
            throw new IllegalArgumentException("시간: " + hour);
        }
        if (minute < 0 || minute > MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("분: " + minute);
        }
        this.hour = hour;
        this.minute = minute;
    }
}


```

## 정리
public 클래스는 절대 `가변 필드`를 직접 노출해서는 안 된다.<br/>
불변 필드라면 노출해도 덜 위험하지만 안심할 수는 없다 하지만 `package-private` 클래스나 `private` 중첩클래스에서는 종종 (`불변이든 가변이든`)필드를 노출하는 편이 나을 때도 있다.
