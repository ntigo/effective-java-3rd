# # 아이템 15 클래스와 멤버의 접근 권한을 최소화하라 
잘 설계된 컴포넌트는 모든 내부 구현을 완벽히 숨겨, 구현과 API를 깔끔히 분리합니다.  
정보 은닉, 혹은 캡슐화라고 하는 이 개념은 소프트웨어의 근간이 되는 원리입니다.

## 정보 은닉의 장점  
> - 시스템 개발 속도를 높인다. 여러 컴포넌트를 병렬로 개발할 수 있다.    
> - 시스템 관리 비용을 낮춘다. 각 컴포넌트를 더 빨리 파악하여 디버깅할 수 있고, 다른 컴포넌트로 교체하는 부담도 적다.
> - 정보 은닉 자체가 성능을 높여주지는 않지만, 성능 최적화에 도움을 준다.  
>   완성된 시스템을 프로파일링해 최적화할 컴포넌트를 정한 다음 다른 컴포넌트에 영향을 주지 않고 해당 컴포넌트만 최적화할 수 있기 때문이다.  
> - 소프트웨어 재사용성을 높인다. 외부에 거의 의존하지 않고 독자적으로 동작할 수 있는 컴포넌트라면 그 컴포넌트와 함께 개발되지 않은 낯선 환경에서도 유용하게 쓰일 가능성이 크기 때문이다.  
> - 큰 시스템을 제작하는 난이도를 낮춰준다. 시스템 전체가 아직 완성되지 않은 상태에서도 개별 컴포넌트의 동작을 검증할 수 있기 때문이다.  

자바는 정보 은닉을 위해 다양한 장치를 제공한다.  
그 중 접근 제어 메커니즘은 클래스, 인터페이스, 멤버의 접근성을 명시한다.  
각 요소의 접근성은 그 요소가 선언된 위치와 접근 제한자로 정해진다.  
이 접근 제한자를 제대로 활용하는 것이 정보 은닉의 핵심이다.

## 접근 제어자
> - private: 멤버를 선언한 톱레벨 클래스에서만 접근할 수 있다.
> - package-private: 멤버가 소속된 패키지 안의 모든 클래스에서 접근할 수 있다.  
>접근 제한자를 명시하지 않았을 때 적용되는 패키지 접근 수준이다. (단, 인터페이스의 멤버는 기본적으로 public이 적용된다.)
> - protected: package-private의 접근 범위를 포함하며, 이 멤버를 선언한 클래스의 하위 클래스에서도 접근할 수 있다.
> - public: 모든 곳에서 접근할 수 있다. 

**접근 제어의 기본 원칙은, 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.**
소프트웨어가 올바로 동작하는 한 항상 가장 낮은 접근 수준을 부여해야 한다는 뜻이다.  
클래스나 인터페이스를 public으로 공개 API로 정의하면, 클라이언트에 공개되기 때문에 하위 호환을 위해 영원히 관리해줘야만 한다.

클래스의 공개 API를 설계한 후, 그 외 모든 멤버는 private으로 만들자.  
같은 패키지의 다른 클래스가 접근해야하는 멤버에 한해 package-private으로 풀어주자.  
private과 package-private 멤버는 모두 해당 클래스의 구현에 해당하므로 보통은 공개 API에 영향을 주지 않는다.  
접근 수준을 protected, public 으로 선언하는 순간 공개API가 되므로 영원히 지원해야 한다.  
또한 내부 동작 방식을 API 문서에 적어 사용자에게 공개해야 할 수도 있다.  

**멤버 접근성을 좁히지 못하게 방해하는 제약이 하나 있다.**  
상위 클래스의 메서드를 재정의할 때는 그 접근 수준을 상위 클래스에서보다 좁게 설정할 수 없다는 것이다.  
이 제약은 상위 클래스의 인스턴스는 하위 클래스의 인스턴스로 대체해 사용할 수 있어야 한다는 규칙(리스코프 치환 원칙)을 지키기 위해 필요하다.  

**public 클래스의 인스턴스 필드는 되도록 public이 아니어야한다.**  
그 필드에 담을 수 있는 값을 제한할 힘을 잃게 된다.(불변식을 보장할 수 없음)  
일반적으로 thead safe하지 않다.  
final 이면서 불변 객체를 참조하더라도 문제가 여전히 남는다.  
내부 구현을 바꾸고 싶어도 그 public 필드를 없애는 방식으로 리팩터링할 수 없게 된다.  

단, 상수는 Public static final 필드로 공개해도 좋다.  
관례상 상수의 이름은 대문자 알파벳으로 쓰며, 각 단어 사이에 밑줄을 넣는다.  
이런 필드는 반드시 기본 타입 값이나 불변 객체를 참조해야 한다.  
가변 객체를 참조한다면 다른 객체를 참조하지 못하지만, 참조된 객체 자체는 수정될 수 있으니 끔찍한 결과를 초래할 수 있다.  

**코드를 테스트하려는 목적으로 접근제한을 풀어주면 안된다.**

**클래스에서 public static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공해서는 안된다.**  

**자바 9에서는 모듈 시스템이라는 개념이 도입되면서 두 가지 암묵적 접근 수준이 추가되었다.**  


  