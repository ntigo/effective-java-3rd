# 아이템 33. 타입 안전 이종 컨테이너를 고려하라

## Part 1. 타입 안전 이종 컨테이너란?

### 1. 타입 안전 이종 컨테이너
`타입 안전성`이 보장된 `서로 다른 타입`의 원소를 가지는 컨테이너

### 2. 타입 안전 이종 컨테이너 예시
데이터베이스  

| rowNum | name |      birthDay       |
|:------:|:----:|:-------------------:|
|   1    | 홍길동  | 2000-01-21 14:20:00 |
|   2    | 김철수  | 2011-03-01 04:01:00 |
|   3    | 이영희  | 2007-12-18 19:11:00 |

### 3. Java의 타입 안전 이종 컨테이너 패턴
애너테이션 API
> [1] 컨테이너 대신 키를 매개변수화  
> [2] 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공  

## Part 2. 타입 안전 이종 컨테이너 구현 방법

### 1. Favorites 클래스 구현
> [1] `타입 토큰`(Class 객체)을 키로 가지는 맵 인스턴스 생성  
> : Key를 `와일드카드 타입의 Class`로 설정하여, 다양한 타입을 지원할 수 있게 함  
> [2] 값을 추가할 수 있는 putFavorite 메서드 구현  
> : Class.cast 메서드의 `동적 형변환`을 통해 `런타임 타입 안정성` 확보  
> => checkedSet, checkedList, checkedMap  
> [3] 값을 조회할 수 있는 getFavorite 메서드 구현  
> : `Class.cast` 메서드 활용

### 2. 한정적 타입 토큰 적용
한정적 타입 토큰을 사용한 API
```java
// 애너테이션 API를 활용하는 Field 클래스
public final class Field extends AccessibleObject implements Member {
    //...

    /**
     * 애너테이션을 읽어 오는 기능의 메서드
     * Class, Method 등 애너테이션의 대상이 되는 타입들에서 구현
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        Objects.requireNonNull(annotationClass);
        return annotationClass.cast(declaredAnnotations().get(annotationClass));
    }
}
```
Favorites 클래스에 한정적 타입 토큰 적용하기
> [1] `한정적 와일드카드 타입`을 키로 가지도록 맵 인스턴스 변경  
> [2] putFavorite 메서드에 `한정적 제네릭 타입` 적용  
> [3] getFavorite 메서드에 `한정적 제네릭 타입` 적용  

## Part 3. 타입 안전 이종 컨테이너의 한계점

### 1. 실체화 불가 타입 사용 불가
실체화 불가 타입을 사용하지 못하는 이유  
> * `제네릭 타입`은 동일한 `로타입의 Class 객체`를 공유한다  
> : List&lt;String&gt;용 Class 객체를 얻을 수 없음  
> => List의 Class 객체는 매개 변수 타입과 상관없이 동일하게 List.class이다  

우회 방법 : **슈퍼 타입 토큰** 
> * 제네릭이 소거되지 않는 특이한 케이스를 활용하여 구현  
> : `제네릭 클래스를 상속`하여 사용하는 케이스  
> * 한계점  
> [1] 제네릭 타입이 아닌 경우 사용하지 못함  
> [2] ~~제네릭 메서드의 구조적 특징으로 인해 힙 오염(타입 불일치) 발생 가능~~  

## 결론
> 타입 안전 이종 컨테이너의 개념과 구현 방식을 이해하고 필요한 곳에 활용하라