package com.ntigo.study.effectivejava3rd.item03.assist;

public class MethodSingleton {
    private static final MethodSingleton INSTANCE = new MethodSingleton();

    private MethodSingleton() {}

    public static MethodSingleton getInstance() {
        return INSTANCE;
    }
}
