package com.coopnc.effectivejava3rd.item15.exam2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exam {
    // 보안 허점이 있음
    public static final String[] VALUES_A = {"홍길동", "김길동", "박길동"};

    // 첫번째 해결책
    private static final String[] VALUES = {"홍길동", "김길동", "박길동"};
    public static final List<String> VALUES_B = Collections.unmodifiableList(Arrays.asList(VALUES));

    // 두번째 해결책
    private static final String[] VALUES_C = {"홍길동", "김길동", "박길동"};
    public static final String[] getValues() {
        return VALUES_C.clone();
    }

    public static void main(String[] args) {
        VALUES_A[0] = "아무개";
        System.out.println(VALUES_A);
    }
}
