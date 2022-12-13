# 아이템 3. private 생성자나 열거 타입으로 싱글턴임을 보증하라

싱글턴이란 인스턴스를 오직 하나만 생성할 수 있는 클래스


### 기본적인 방식의 싱글턴
getInstance 함수에서 인스턴스를 체크하여 생성하지 않았을 경우에만 인스턴스를 생성한다
   
멀티쓰레드 환경에서는 안전하지 않다.

```java 
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if (instance != null){
            instance = new Singleton();
        }

        return  instance;
    }
}
```

### synchronized 방식의 싱글턴
synchronized를 사용하여 멀티쓰레드에서 안전하게 사용할수 있다. 
하지만 getInstance 호출마다 락을 걸고 푸는 과정이 있음으로 불필요한 자원이 소비되는 단점이 있다.


```java 
public class Singleton {

    private static Singleton singleton;
    
    private Singleton(){}

    public static synchronized Singleton getInstance(){
        if (singleton != null){
            singleton = new Singleton();
        }

        return  singleton;
    }
}
```

### doubleCheckedLocking 방식의 싱글턴
멀티쓰레드에서 안전하게 사용할 수 있으며 synchronized를 필요한 상황에만 사용할수 있도록 더블 체크를 하는 방식이다.

```java
public class Singleton {

    private static volatile Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
            if(instance == null){
                synchronized (Singleton.class){
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }

            return instance;
    }

}

```

### public static final 방식의 싱글턴
public static final 필드인 Elvis.INSTANCE를 초기화 할때 딱한번 실행된다

리플레션 공격 상황이 발생할수 있다 이러한 공격을 방어하려면 생성자를 수정하여 두번째 객체가 생성되려 할때 예외를 던지면 된다.

역직렬화를 막을려면 readResolve 메소드를 제공하여야한다


```java 
public class Elvis {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() { }

    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }


    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}

```

### 정적 팩터리 방식의 싱글턴
getInstance는 항상 같은 객체의 참조를 반환한다

리플레션 공격 상황이 발생할수 있다 이러한 공격을 방어하려면 생성자를 수정하여 두번째 객체가 생성되려 할때 예외를 던지면 된다.

역직렬화를 막을려면 readResolve 메소드를 제공하여야한다 

### 장점
* API를 바꾸지 않고도 싱글턴이 아니게 변경할수 있는 점
* 정적 팩터리를 제네릭 싱턴 팩터리로 만들수 있다는 점 
* 정적 팩터린의 메서드 참조를 공급자로 사용할수 있다는 점

```java 
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }

    public void leaveTheBuilding() {
        System.out.println("leaveTheBuilding");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}

```

### staticInnerClass 방식의 싱글턴
역직렬화와 리플레션 공격 상황에 안전하다.
멀티 쓰레드 환경에 안전하게 사용할수 있다.

```java
public class Singleton {

    private Singleton(){

    }

    private static class SingletonHolder {
        private static  final  Singleton INSTANCE = new Singleton();
    }

    public  static Singleton getInstance(){
        return  SingletonHolder.INSTANCE;
    }
}
```

### 열거 타입 방식의 싱글턴
멀티 쓰레드 환경에 안전하며 직렬화상황이나 리플렉션 공격을 막을수 있다.
단점으로는 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 한다면 이방법은 사용할 수 없다.

```java 
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
         System.out.println("leaveTheBuilding");
    }

    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
```

### 리플렉션 공격

```java
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


Constructor<Singleton> constructor = null;
        try {
            constructor = Singleton.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        constructor.setAccessible(true);

        Singleton singleton2 = null;
        try {
            singleton2 = constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
```


