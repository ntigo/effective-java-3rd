package com.coopnc.effectivejava3rd.item19;

/**
 * 정수의 합산 기능을 제공한다.
 * 재정의 가능 메서드를 내부에서 호출하지 않아 안전하다.
 */
public abstract class AbstractSafeAddInt {
    /**
     * 두 정수의 합을 구하여 반환한다.
     *
     * @param       num1 1번 정수
     * @param       num2 2번 정수
     *
     * @return      두 정수의 합
     *
     * @since       2023. 02. 08
     */
    public int add(int num1, int num2 ) {
        return _add( num1, num2 );
    }

    /**
     * 입력받은 모든 정수의 합을 반환한다.
     *
     * @param       numList 입력받은 정수 목록
     *
     * @return      입력받은 모든 정수의 합
     *
     * @see         AbstractAddInt#add(int, int)
     *
     * @since       2023. 02. 08
     */
    public int addAll( int ...numList ) {
        int result = 0;
        for ( int num : numList ) {
            result = _add( result, num );
        }
        return result;
    }

    /**
     * 두 정수의 합을 구하는 도우미 메서드
     *
     * @param       num1 1번 정수
     * @param       num2 2번 정수
     *
     * @return      두 정수의 합
     *
     * @since       2023. 02. 08
     */
    private int _add(int num1, int num2 ) {
        return num1 + num2;
    }
}
