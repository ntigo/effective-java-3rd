# 아이템 9. try-finally보다는 try-with-resources를 사용하라.
----

## 1. try-with-resources란?

try 블록이 종료되었을 때 close 메서드를 실행해주어 사용중인 자원을 해제하거나 반환하도록 함  
AutoCloseable 인터페이스를 상속 받고 close 메서드를 구현하면 실행됨
    
```java
// AutoCloseable 상속, close method 구현 필요
public class AutoCloseFile implements AutoCloseable {
	@Override
	public void close() {
            // close code write
	}
}
```

## 2. try-with-recources를 사용하기 좋은 예시

* 파일 처리
* 네트워크 연결 ( socket 등 )
* 데이터 베이스 Connection Pool 사용

## 3. try-with-resources 예제

```java
try ( AutoCloseFile autoCloseFile = new AutoCloseFile() ) {
    // content...
} catch( Exception e ) {
    // exception...
}
```

## 4. try-finally 예제

```java
FileWriter fileWriter = new FileWriter();

try {
    // content...
} catch( Exception e ) {
    // exception...
} finally {
    fileWriter.close();
}
```
    
## 5. try-with-resources가 try-finally 보다 좋은점

* 가독성이 좋음
* 예외 처리가 용이함

## 6. AutoCloseable를 상속받은 자바 Api

* https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html
