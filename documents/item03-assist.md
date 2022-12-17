# 아이템 3. private 생성자나 열거 타입으로 싱글턴임을 보증하라.
--- 
싱글턴 패턴은 인스턴스를 하나만 생성할 수 있는 클래스다.  
이를 이용하여 무상태(stateless)객체나 설계상 유일해야 하는 시스템 컴포넌트 혹은 인스턴스를 하나만 유지해도 좋은 경우 사용한다.  

책에서는 이러한 싱글턴 패턴을 구현 시 ``private 생성자를 사용하여 약간의 조정을 통한 방식이나 열거 타입으로 사용``할 것을 요구한다.

## 잘못된 불완전한 방식의 싱글턴 패턴
---
일부 개발자가 중요한 의미 인식을 하지 않고 무의식 중 사용하면 발생할 수 있는 흔한 케이스다.  
아래 코드는 2가지의 잘못된 방식과 1가지의 불완전함을 포함한다.

```java
public class ImperfectSingleton {
    public static ImperfectSingleton instance;

    public static ImperfectSingleton getInstance() {
        if ( null == instance ) {
            instance = new ImperfectSingleton();
        }

        return instance;
    }
}
```

### 문제점
* 필드는 외부로 공개되면 안된다.
* 기본 생성자는 클래스 접근 제어자를 따라간다.
* Thread safe 하지 않다.

### 문제가 발생하는 이유
1) 필드가 외부로 공개될 경우, 직접 접근하여 변경할 수 있다.
2) 생성자가 공개될 경우 인스턴스화가 가능해진다.
3) Thread safe 하지 않음을 회피하는 기법으로 Synchronized, DCL(Double-checked Locking) 방식이 있다.

```
Synchronized 는 Intrinsic Lock 을 활용하여 임계영역을 Locking 한다.  
getInstance 가 호출되는 매순간 동시 접근을 제어하여 Thread safe를 보장한다.  
DCL 은 최초 생성 시 경합 과정에서만 동시 접근을 제어하는 기법이다.

본 주제가 디자인 패턴에 대한 부분이 아님으로 이 주제를 더 다루지 않겠다.  
```
> **Note.** DCL 방식은 Thread safe 하지 않다는 일부 전문가들의 주장이 있다.
```java
public class ImperfectSingletonTest {

    public static void doTest() {
        // 필드가 public 인 경우 getInstance 를 통하지 않고 필드에 직접 접근하여 변경 가능!!
        ImperfectSingleton.instance = null;

        // 생성자를 명시적으로 지정하지 않으면 직접 생성 가능!!
        // ImperfectSingleton imperfectSingleton = new ImperfectSingleton();

        // Thread safe 하지 않음. (상위 주석 후 수차례 반복 시 확인 가능)
        Runnable runnable = () -> {
            ImperfectSingleton singleton = ImperfectSingleton.getInstance();
            System.out.println( singleton );
        };

        List<Thread> threads = new ArrayList<>();
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );
        threads.add( new Thread( runnable ) );

        for ( int i = 0; i < threads.size(); i++ ) {
            Thread thread = threads.get( i );
            thread.start();
        }
    }
}
```

## 첫 번째 방법: private 생성자 + public static final 필드
---
```java
public class FieldSingleton {
    public static final FieldSingleton INSTANCE = new FieldSingleton();

    private FieldSingleton() {}
}
```

### 장점
* 구현이 간결하고 싱글턴임을 API에 들어낼 수 있다.

### 단점
* 싱글턴을 사용하는 클라이언트가 테스트하기 어려워진다.
* 리플렉션으로 private 생성자를 호출할 수 있다.
* 역직렬화 할 때 새로운 인스턴스가 생길 수 있다.

## 두 번째 방법: private 생성자 + 정적 팩터리 메서드
---
```java
public class MethodSingleton {
    private static final MethodSingleton INSTANCE = new MethodSingleton();

    private MethodSingleton() {}

    public static MethodSingleton getInstance() {
        return INSTANCE;
    }
}
```

### 장점
* API를 변경하지 않고 싱글턴이 아니게 변경할 수 있다.
* 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
* 정적 팩터리의 메서드 참조를 공급자(Supplier)로 사용할 수 있다.

### 단점
* *첫 번째 방법과 동일*

### 장점 1) API를 변경하지 않고 싱글턴이 아니게 변경할 수 있다.
클라이언트 코드를 변경시키지 않고 생성 방식을 변형시킬 수 있다.
```java
public class MethodSingleton {
    private MethodSingleton() {}

    public static MethodSingleton getInstance() {
        return new MethodSingleton();
    }
}
```
### 장점 2) 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.
Generic 을 활용하여 같은 인스턴스를 반환하지만 타입을 변환하여 사용할 수 있다.

```java
public class GenericSingleton<T> {
    public static final GenericSingleton<?> INSTANCE = new GenericSingleton<>();

    private GenericSingleton() {
    }

    public static <T> GenericSingleton<T> getInstance() {
        return (GenericSingleton<T>) INSTANCE;
    }

    public boolean send( T message ) {
        // blah~ blah~
        return true;
    }
}
```
### 장점 3) 정적 팩터리의 메서드 참조를 공급자(Supplier)로 사용할 수 있다.
Supplier 를 활용하여, lambda expression or method reference 를 사용할 수 있다.

```java
public class SingletonSupplier {

    public void start( Supplier<ISingleton> supplier ) {
        ISingleton instance = supplier.get();
        instance.send( "test" );
    }

    public static void main( String[] args ) {
        SingletonSupplier singletonSupplier = new SingletonSupplier();
        singletonSupplier.start( MockSingleton::getInstance );
    }
}
```

## 두 가지(첫, 두 번째) 방식의 문제점과 해결 방안
---
1) 싱글턴을 사용하는 클라이언트가 테스트하기 어려워진다.  
싱글턴을 사용하는 인스턴스가 비용(Money)이 발생하는 루틴이라고 가정하면   
하나의 인스턴스만 생성되는 싱글턴의 특성상 대체가 곤란하다는 내용이다.  
이는 언급된 내용처럼 ``인터페이스를 구현해서 만든 Mock으로 대체할 수 있다.``

```java
// 실제 객체
public class RealSingleton implements ISingleton {
    private static final RealSingleton INSTANCE = new RealSingleton();

    private RealSingleton() {
    }

    public static RealSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        // 비용 발생
        System.out.println( "실제 비용 발생 루틴" );
        return false;
    }
}

// 가짜 객체 구현
public class MockSingleton implements ISingleton {
    private static final MockSingleton INSTANCE = new MockSingleton();

    private MockSingleton() {
    }

    public static MockSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        // 가짜 구현체를 이용하여 비용 발생하지 않음.
        return false;
    }
}

public static main( String[] args ) {
    ISingleton singleton = RealSingleton.getInstrance(); // 실 비즈니스 동작 시 사용
    singleton = MockSingleton.getInstrance(); // 테스트 시 구현 대체
    singleton.send( "fire~!" );
}
```

2) 리플렉션에 안전하지 않다.
리플렉션을 이용하여, 생성자에 접근 제어자를 수정하여 새로운 인스턴스를 생성할 수 있다.  
공격을 방어하려면 생성자에서 ``두 번째 객체가 생성될 때 예외를 던진다.``

```java
public class ReflectionAttack {

    @SneakyThrows
    public static <T> T getNewInstance( Class<?> clz ) {
        Constructor<?> declaredConstructor = clz.getDeclaredConstructor();
        declaredConstructor.setAccessible( true );
        T newInstance = (T) declaredConstructor.newInstance();

        return newInstance;
    }

    public static void doTest() {
        MethodSingleton instance = MethodSingleton.getInstance();
        MethodSingleton newInstance = getNewInstance( MethodSingleton.class );

        System.out.println( instance + " : " + newInstance );
    }
}
```
```java
public class ReflectionSafeSingleton {
    private static final ReflectionSafeSingleton INSTANCE = new ReflectionSafeSingleton();
    private static boolean isCreated;

    // 생성자에서 두 번 생성하지 못하게 함으로 안전해진다.
    private ReflectionSafeSingleton() {
        if ( isCreated )
            throw new UnsupportedOperationException( "already exists." );

        isCreated = true;
    }

    public static ReflectionSafeSingleton getInstance() {
        return INSTANCE;
    }
}
```

3) 역직렬화 시 새로운 인스턴스가 생길 수 있다.  
역직렬화 시 필드 변수의 값을 복사한 새로운 인스턴스를 생성할 수 있는 문제가 있다.  
이를 방지하기 위해 필드 변수에는 ``transient``로 선언하여 다른 인스턴스로 복제됨을 방지한다.  
또한 동일한 인스턴스를 생성 및 반환하도록 ``readResolve()``를 구현한다.
```java
public static void serialize( Object obj, String fileName ) {
    try ( ObjectOutput out = new ObjectOutputStream( new FileOutputStream( fileName ) ) ) {
        out.writeObject( obj );
    } catch ( FileNotFoundException e ) {
        throw new RuntimeException( e );
    } catch ( IOException e ) {
        throw new RuntimeException( e );
    }
}

public static Object deserialize( String fileName ) {
    try ( ObjectInput in = new ObjectInputStream( new FileInputStream( fileName ) ) ) {
        return in.readObject();
    } catch ( IOException e ) {
        throw new RuntimeException( e );
    } catch ( ClassNotFoundException e ) {
        throw new RuntimeException( e );
    }
}

public static void doTest() {
    String fileName = "serial.obj";

    SerializationSingleton serialIns = SerializationSingleton.getInstance();
    serialize( serialIns, fileName );
    System.out.println( serialIns );

    SerializationSingleton deserializeIns = (SerializationSingleton) deserialize( fileName );
    System.out.println( deserializeIns );
}

// com.ntigo.study.effectivejava3rd.item03.assist.SerializationSingleton@34cd072c
// com.ntigo.study.effectivejava3rd.item03.assist.SerializationSingleton@34c4973
```
```java
public class SerializationSingleton implements Serializable {

    private static final SerializationSingleton INSTANCE = new SerializationSingleton();
    private transient String name = "ntigo";

    private SerializationSingleton() {
    }

    public static SerializationSingleton getInstance() {
        return INSTANCE;
    }

    public void display() {
        System.out.println( this.name );
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
// com.ntigo.study.effectivejava3rd.item03.assist.SerializationSingleton@34cd072c
// ntigo
// com.ntigo.study.effectivejava3rd.item03.assist.SerializationSingleton@34cd072c
// ntigo
```


## 세 번째 방법: 열거 타입
---
``대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만들기 최적의 방법!``
```java
public enum EnumSingleton {
    INSTANCE
}
```

### 장점
* 가장 간결한 방법이며 직렬화와 리플렉션에도 안전하다.
* 대부분의 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이다.

### +@ Initialization-on-demand holder idiom (Bill Pugh Solution)
---
싱글턴을 안전하게 구현할 수 있도록 William Pugh가 고안한 이디엄이다.

```java
public class HolderSingleton {
    private HolderSingleton() {}

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }
}
```

### 장점
* *두 번째 생성 방법의 장점과 동일*
* Lazy loading이 가능하다.

``static inner class는 해당 객체 사용 시 초기화 된다.``


## Reference
---
https://wesome.org/bill-pugh-singleton-solution-or-holder-singleton-pattern  
http://www.cs.umd.edu/~pugh/java/memoryModel/  
http://www.cs.umd.edu/~pugh/java/memoryModel/jsr-133-faq.html  
http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html