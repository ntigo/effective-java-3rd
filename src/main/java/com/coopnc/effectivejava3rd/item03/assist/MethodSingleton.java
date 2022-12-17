package com.coopnc.effectivejava3rd.item03.assist;

public class MethodSingleton {
    private static final MethodSingleton INSTANCE = new MethodSingleton();
    private static boolean isCreated;

    private MethodSingleton() {
        if ( isCreated )
            throw new UnsupportedOperationException("already exists.");

        isCreated = true;
    }

    public static MethodSingleton getInstance() {
        return INSTANCE;
    }
}
