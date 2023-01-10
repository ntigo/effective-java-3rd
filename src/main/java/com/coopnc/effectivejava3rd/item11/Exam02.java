package com.coopnc.effectivejava3rd.item11;
import com.coopnc.effectivejava3rd.item10.exam02.Point;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Exam02 {
    // 기본 타입의 박싱 클래스 result hashcode 변환 예시
    private short data1;

    // 참조 타임
    private int val;

    Integer[] list = new Integer[]{1,2,3};
   
    public static void doTest() {

    }

    @Override
    public int hashCode() {
        return  0;
    }

}
