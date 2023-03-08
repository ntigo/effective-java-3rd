# item27. 비검사 경고를 제거하라

## 1. 제네릭을 사용하기 시작하면 보게될 컴파일러 경고

> * 비검사 형변환 경고
> * 비검사 메서드 호출 경고
> * 비검사 매개변수화 가변인수 타입 경고
> * 비검사 변환 경고

```java
// 책속에 잘못된 예시코드 (매개변수 경고)
public class Main {
    public static void main(String[] arg) {
        Set<String> some = new HashSet();
    }
}
```
* 요약 : 할 수있는 한 모든 비검사 경고를 제거하자

* 정리 : 비검사 경고 제거로 코드의 타입 안정성을 보장할 수 있습니다

 ## 2. 비검사 경고 제거를할 수 없다면 하지만 타입이 안전하다고 확신할 수 있다면 `@SuppreWarings("unchecked")` 에너테이션을 달아 경고를 숨기자!
#### 주의: 타입의 안전함을 검증은 필수입니다 
#### 이유: 경고를 제거하여 경고는 없어지지만 컴파일은 가능하기 때문에런타입에는 여전히 `ClassCastException`을 던질 수 있기 때문입니다. 

## 2-1 @SuppreWarings 에너테이션 사용
* 사용범위 : 개별 지역변수 선언부터 클래스 전체까지 어떤 선언에도 달수 있다
* 제시한 사용범위: 가능한 좁은 범위에 적용하자 
>  * 여기서! 가능한 좁은 범위 : 변수선언 또는 아주 잛은 메서드 혹은 생성자에 적용하는 범위
>  * 주의: 클래스 전체에 적용 절대 ❌ 자칫 심각할 경고를 놓칠수 있기 때문입니다
>  
```java 
//ArrayList toArray 메소드 예제 코드
 public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
```

-----

```java
// 지역변수를 추가해 @SuppressWarnings의 범위를 좁힌 예제코드
public class TestToArray {
    private int size;
    private Object[] elements;
    public <T> T[] toArray(T[] a){
        if(a.length < size){
            //생성한 배열과 매개변수로 받은 배열의 타입이 모두 T[] 로 같으므로
            // 올바른 형변환이다
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements,0,a,0,size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
}
```
> * 수정한 결과 : 비검사 경고를 숨기는 범위를 좁혀 경고가 발생할 수 있는 줄이고, 주석으로 근거를 표시하여 코드를 이해도를 고려하여 타입안정성 확보 

## 📋 정리 
비검사 경고는 중요합니다 그렇기 때문에 경고를 무시하지말고 확인하여 제거해야합니다<br>
하지만 경고를 제거할 방법을 찾지 못하였다면 `@SuppreWarings` 에너테이션을 사용하여 경고를 숨깁니다 <br>
위와같은 처리를 할수있는 전제는 숨긴 경고 코드의 타입 안전성을 확실하게 증명하고,그 근거를 주석을 달아 남겨 코드안정성을 확보해야합니다.

