package com.coopnc.effectivejava3rd.item13.exam01;

/**
 * 재대로 구현된 clone 은 절대 실패하지 않음으로 비 검사 예외로 처리해 주는것이 좋다.
 */
public class CloneableNotThrowException implements Cloneable {
    private int checkValue;

    public CloneableNotThrowException(final int checkValue ) {
        this.checkValue = checkValue;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "checkValue : " ).append( checkValue );

        return stringBuilder.toString();
    }

    /**
     * ExamRun1301 복사
     *  - 검사 예외인 경우 사용하기 불편하다.
     * @return 복사된 ExamRun1301 인스턴스
     * @throws CloneNotSupportedException clone 을 제공하는 class 가 Cloneable 인터페이스를 상속받지 않는 경우 발생
     */
//    @Override
//    public CloneableNotThrowException clone() throws CloneNotSupportedException {
//        return (CloneableNotThrowException) super.clone();
//    }

    /**
     * CloneableNotThrowException 복사
     *  - 비 검사 예외인 경우 사용하는데 편리하다.
     * @return 복사된 ExamRun1301 인스턴스
     * @throws RuntimeException clone 을 제공하는 class 가 Cloneable 인터페이스를 상속받지 않는 경우 발생
     */
    @Override
    public CloneableNotThrowException clone() throws RuntimeException {
        try {
            return (CloneableNotThrowException) super.clone();
        } catch( CloneNotSupportedException e ) {
            System.out.println( "[UnChecked Exception]CloneNotSupportedException -> " + e );
            throw new RuntimeException();
        }
    }
}
