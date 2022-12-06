package com.coopnc.effectivejava3rd.item01.exam03;

import java.util.EnumSet;

public class EnumSetTest {

    public enum ElementsUnder64 {
        ELEMENTS_1
    }

    public enum ElementsOver64 {
        ELEMENTS_1
        ,ELEMENTS_2
        ,ELEMENTS_3
        ,ELEMENTS_4
        ,ELEMENTS_5
        ,ELEMENTS_6
        ,ELEMENTS_7
        ,ELEMENTS_8
        ,ELEMENTS_9
        ,ELEMENTS_10
        ,ELEMENTS_11
        ,ELEMENTS_12
        ,ELEMENTS_13
        ,ELEMENTS_14
        ,ELEMENTS_15
        ,ELEMENTS_16
        ,ELEMENTS_17
        ,ELEMENTS_18
        ,ELEMENTS_19
        ,ELEMENTS_20
        ,ELEMENTS_21
        ,ELEMENTS_22
        ,ELEMENTS_23
        ,ELEMENTS_24
        ,ELEMENTS_25
        ,ELEMENTS_26
        ,ELEMENTS_27
        ,ELEMENTS_28
        ,ELEMENTS_29
        ,ELEMENTS_30
        ,ELEMENTS_31
        ,ELEMENTS_32
        ,ELEMENTS_33
        ,ELEMENTS_34
        ,ELEMENTS_35
        ,ELEMENTS_36
        ,ELEMENTS_37
        ,ELEMENTS_38
        ,ELEMENTS_39
        ,ELEMENTS_40
        ,ELEMENTS_41
        ,ELEMENTS_42
        ,ELEMENTS_43
        ,ELEMENTS_44
        ,ELEMENTS_45
        ,ELEMENTS_46
        ,ELEMENTS_47
        ,ELEMENTS_48
        ,ELEMENTS_49
        ,ELEMENTS_50
        ,ELEMENTS_51
        ,ELEMENTS_52
        ,ELEMENTS_53
        ,ELEMENTS_54
        ,ELEMENTS_55
        ,ELEMENTS_56
        ,ELEMENTS_57
        ,ELEMENTS_58
        ,ELEMENTS_59
        ,ELEMENTS_60
        ,ELEMENTS_61
        ,ELEMENTS_62
        ,ELEMENTS_63
        ,ELEMENTS_64
        ,ELEMENTS_65
    }

    public static void doTest() {
        Class<?> overClz = EnumSet.noneOf(ElementsOver64.class).getClass();
        Class<?> underClz = EnumSet.noneOf(ElementsUnder64.class).getClass();

        // 엘리먼트 숫자에 따라 생성되는 클래스가 다름을 증명. (RegularEnumSet, JumboEnumSet)
        System.out.println("When the number of elements is less than or equal to 64. " + underClz.getName());
        System.out.println("When the number of elements exceeds 64. " + overClz.getName());
    }
}
