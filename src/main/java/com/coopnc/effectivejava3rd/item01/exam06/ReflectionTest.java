package com.coopnc.effectivejava3rd.item01.exam06;

public class ReflectionTest {
    private static volatile ReflectionTest reflectionTest;

    public String data = "data";
    private ReflectionTest() {
    }

    public static ReflectionTest getInstance() {
        if ( null == reflectionTest ) {
            synchronized (ReflectionTest.class) {
                if ( null == reflectionTest ) {
                    reflectionTest = new ReflectionTest();
                }
            }
        }

        return reflectionTest;
    }
}
