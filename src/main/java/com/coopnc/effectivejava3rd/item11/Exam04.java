package com.coopnc.effectivejava3rd.item11;

public class Exam04 {
    // 지연 초기화 전략

    private int  areaCode;
    private int prefix;
    private int lineNumber;

    @Override
    public int hashCode() {
        int result = Integer.hashCode(areaCode);
        result = 31 * result + Integer.hashCode(prefix);
        result = 31 * result + Integer.hashCode(lineNumber);
        return result;
    }

}
