package com.coopnc.effectivejava3rd.item03.assist;

public class ReflectionSafeSingleton {
    private static final ReflectionSafeSingleton INSTANCE = new ReflectionSafeSingleton();
    private static boolean isCreated;

    private ReflectionSafeSingleton() {
        if ( isCreated )
            throw new UnsupportedOperationException( "already exists." );

        isCreated = true;
    }

    public static ReflectionSafeSingleton getInstance() {
        return INSTANCE;
    }
}
