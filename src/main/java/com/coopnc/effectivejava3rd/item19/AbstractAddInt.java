package com.coopnc.effectivejava3rd.item19;

// @implSpec 태그 설명을 위해 생성한 클래스
// Locale : ko_KR
// Command line arguments : -encoding UTF-8 -charset UTF-8 -docencoding UTF-8 -tag "implSpec:a:Implementation Requirements:"
/**
 * 정수의 합산 기능을 제공한다.
 */
public abstract class AbstractAddInt {
    /**
     * 두 정수의 합을 구하여 반환한다.
     *
     * @param       num1 1번 정수
     * @param       num2 2번 정수
     *
     * @return      두 정수의 합
     *
     * @implSpec    항상 두 정수의 합을 반환하도록 구현해야 한다.
     *              addAll 내부 구현에 사용되며, 재정의될 경우 addAll의 동작에 영향을 미친다.
     *
     * @since       2023. 02. 08
     */
    public int add( int num1, int num2 ) {
        return num1 + num2;
    }

    /**
     * 입력받은 모든 정수의 합을 반환한다.
     *
     * @param       numList 입력받은 정수 목록
     *
     * @return      입력받은 모든 정수의 합
     *
     * @implSpec    입력받은 정수 목록을 차례대로 add 메서드를 사용하여 합산한다.
     *              add 메서드가 재정의될 경우, addAll의 동작에 영향을 미친다.
     *              add 메서드는 항상 두 정수의 합을 반환하도록 구현해야 한다.
     *
     * @see         AbstractAddInt#add(int, int)
     *
     * @since       2023. 02. 08
     */
    public int addAll( int ...numList ) {
        int result = 0;
        for ( int num : numList ) {
            result = add( result, num );
        }
        return result;
    }
}
