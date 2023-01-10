package com.coopnc.effectivejava3rd.item11;

import java.util.Objects;

public class Exam03 {
    // 전형적인 hashcode

    private int areaCode;
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
