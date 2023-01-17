# # 아이템 14 Comparable을 구현할지 고려하라 

* compareTo는 Object의 메서드가 아니다. 하지만, 성격은 두 가지만 빼면 Object의 equals와 같다.
* compareTo는 단순 동치성 비교에 더해 순서까지 비교할 수 있으며, 제네릭하다.
* Comparable을 구현했다는 것은 그 클래스의 인스턴스들에는 자연적인 순서(natural order)가 있음을 뜻한다. 
 따라서 Comparable을 구현한 객체들의 배열은 다음처럼 손쉽게 정렬할 수 있다.
> Arrays.sort(a);


```
public class WordList {
    public static void main(String[] args){

        Set<String> s = new TreeSet<>();

        s.add("c");
        s.add("b");
        s.add("a");
        Collections.addAll(s, args);
        System.out.println(s);
    }
}
```
----
```
실행 결과 :  [a, b, c]
```
## CompareTo 메서드의 일반 규약
> 이 객체와 주어진 객체의 순서를 비교한다. 이 객체가 주어진 객체보다 작으면 음의 정수를, 같으면 0을, 크면 양의 정수로 반환한다. 이 객체와 비교할 수 없는 타입의 객체가 주어지면 ClassCastException을 던진다.
> 
> 다음 설명에서 sgn(표현식) 표기는 수학에서 말하는 부호 함수(signum function)를 뜻하며, 표현식의 값이 음수, 0, 양수일 때 -1, 0, 1을 반환하도록 정의했다.
> ## compareTo 반환값의 기준 
> * 기준 객체 < 주어진 객체 → -1 반환
> * 기준 객체 == 주어진 객체 → 0 반환
> * 기준 객체 > 주어진 객체 → 1 반환
> 
> * Comparable을 구현한 클래스는 모든 x, y에 대해 sgn(x.compartTo(y)) == -sgn.(y.compareTo(x))여야 한다(따라서 x.compareTo(y)는 y.compareTo(x)가 예외를 던질때에 한해 예외를 던져야 한다).
>
> * Comparable을 구현한 클래스는 추이성을 보장해야 한다. 즉, (x.compareTo(y) > 0 && y.compareTo(z) > 0)이면 x.compareTo(z) > 0이다.
> * Comparable을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z))다.
> * 이번 권고가 필수는 아니지만 꼭 지키는 게 좋다. (x.compareTo(y) == 0) == (x.equals(y))여야 한다. Comparable을 구현하고 이 권고를 지키지 않는 모든 클래스는 그 사실을 명시해야 한다. 다음과 같이 명시하면 적당할 것이다.
> * “주의 : 이 클래스의 순서는 equals 메서드와 일관되지 않다.”
## compareTo와 equals의 일관성이 지켜지지 않은 BigDecimal 클래스를 예
```
public class test2 {
    public static void main(String[] args) {
        final BigDecimal bigDecimal1 = new BigDecimal("1.0");
        final BigDecimal bigDecimal2 = new BigDecimal("1.00");

        final HashSet<BigDecimal> hashSet = new HashSet<>();
        hashSet.add(bigDecimal1);
        hashSet.add(bigDecimal2);

        System.out.println(hashSet.size());

        final TreeSet<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(bigDecimal1);
        treeSet.add(bigDecimal2);

        System.out.println(treeSet.size());


    }
}

```

```
실행 결과
hashSet : 2
treeSet : 1
```
> hashSet 과 TreeSet은서로 다른 동치성을 비교 한다 
> HashSet은 equals를 기반으로 비교하기 때문에 다른 값으로 인식하여 크기가 2가 되며
> TreeSet은 compareTo를 기반으로 객체에 대한 동치성을 비교하기 때문에 같은 값으로 인식되어 compareTo가 0을 반환하기 때문에 크기가 1이 된다
----
## Comparable로 구성
```
class Student implements Comparable<Student>{
    String studentName; // 이름
    int studentAge;      // 나이
    double score;       // 점수

    public Student(String studentName, int studentAge, double score) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.score = score;
    }

    @Override
    public String toString() {
        return "이름: " + studentName + ", 나이: " + studentAge + ", 점수: " + score;
    }
    @Override
    public int compareTo(Student o){
        return Integer.compare(studentAge, o.studentAge);
    }
}
```
> Student Class를 생성
```
public class ComparableExample {
    public static void main(String[] args) {
        Student[] student = getStudents();
        print(student);

        Arrays.sort(student);

        System.out.println("======================== 정렬 전");
        System.out.println("======================== 정렬 후");

        print(student);
    }

    private static Student[] getStudents() {
        Student[] student = new Student[5];

        student[0] = new Student("철수", 10, 4.2);
        student[1] = new Student("영희", 12, 4.5);
        student[2] = new Student("짱구", 11, 3.5);
        student[3] = new Student("맹구", 13, 2.8);
        student[4] = new Student("유리", 14, 4.2);

        return student;
    }

    private static void print(final Student[] student) {
        for (Student s : student) {
            System.out.println(s.toString());
        }
    }
}

```
> compare로 구성하여 기준을 나이로 정렬을 지정하지 않을 경우 오름차순 으로 정렬된 결과를 얻을 수 있습니다
> 기준이 나이가 아닌 나이, 이름, 점수 여럿일 경우 => 기본타입 필드가 여럿일 때의 비교자
```
  public int compareTo(Stuent s) {
    int result = Short.Compare(studentName, s.studentName) //가장 중요한 필드
    if (result == 0) {
        result = Short.Compare(studentAge, s.studentAge) // 두번째
        if (result == 0) {
        result = Short.Compare(score, s.score) // 세번째
```
> 클래스의 핵심필드가 여러 개일 경우 중요한 필드 부터 비교하도록 합니다 
```
실행 결과
이름: 철수, 나이: 10, 점수: 4.2
이름: 영희, 나이: 12, 점수: 4.5
이름: 짱구, 나이: 11, 점수: 3.5
이름: 맹구, 나이: 13, 점수: 2.8
이름: 유리, 나이: 14, 점수: 4.2
======================== 정렬 전
======================== 정렬 후
이름: 철수, 나이: 10, 점수: 4.2
이름: 짱구, 나이: 11, 점수: 3.5
이름: 영희, 나이: 12, 점수: 4.5
이름: 맹구, 나이: 13, 점수: 2.8
이름: 유리, 나이: 14, 점수: 4.2
```
## Comparable을 사용해야하는 이유
> 순서를 고려해야하는 값 클래스를 작성한다면 Comparable 인터페이스를 구현하여 인스턴스들을 (정렬,비교,검색) 기능을 제공하는
> 컬렉션과 어우러 지도록 해야한다고 합니다
> 또한 compareTo 메서드에서 필드값을 비교할때 <와 > 연산자는 쓰지말아야 한다고 나와있습니다
> 이유는 코드가 거추장 스럽고 오류를 유발하기때문에 더 이상 추천하지 않는다고 나와있습니다
> 그대신 박싱된 기본 타칩 클래스가 제공하는 정적 compare 메서드나 comparator 인터페이스가 제공하는 메서드를 사용하라

## 정리
> 순서를 고려 해야 할 경우 comparable 인터페이스를 활용하여 구현 할 경우 
> 더욱 쉽게 인스턴스들을 비교 하고 정렬 할 수 있습니다
> 그렇기에 활용이 필요할때 사용하며 앞써 언급된 규약을 지키며 사용 해야 합니다

