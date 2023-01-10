package com.coopnc.effectivejava3rd.item11.exam01;

import java.util.Objects;

public class exam03 {
    // 전형 적인 hashcode

    private int  areaCode;
    private int prefix;
    private int lineNumber;

    @Override
    public int hashCode() {
        int result = Integer.hashCode(areaCode);
        result = 31 * result + Integer.hashCode(prefix);
        result = 31 * result + Integer.hashCode(lineNumber);
        return result;

//        return Objects.hash(lineNumber,prefix,areaCode);
    }

}
