package com.coopnc.effectivejava3rd.item11.exam01;

import java.util.Objects;

public class PhoneNumber {
    private int  areaCode;
    private int prefix;
    private int lineNumber;

    PhoneNumber(int areaCode, int prefix, int lineNumber) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PhoneNumber))
            return false;
        PhoneNumber pn = (PhoneNumber)obj;
        return pn.lineNumber == lineNumber && pn.prefix == prefix
                && pn.areaCode == areaCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber,prefix,areaCode);
    }
}
