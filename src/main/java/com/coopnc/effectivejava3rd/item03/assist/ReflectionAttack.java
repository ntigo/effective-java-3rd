package com.ntigo.study.effectivejava3rd.item03.assist;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

public class ReflectionAttack {

    @SneakyThrows
    public static <T> T getNewInstance( Class<?> clz ) {
        Constructor<?> declaredConstructor = clz.getDeclaredConstructor();
        declaredConstructor.setAccessible( true );
        T newInstance = (T) declaredConstructor.newInstance();

        return newInstance;
    }

    public static void doTest() {
        MethodSingleton instance = MethodSingleton.getInstance();
        MethodSingleton newInstance = getNewInstance( MethodSingleton.class );

        System.out.println( "reflection unsafety: " + instance + " : " + newInstance );

        ReflectionSafeSingleton rsInstance = ReflectionSafeSingleton.getInstance();
        ReflectionSafeSingleton rsNewInstance = getNewInstance( ReflectionSafeSingleton.class );

        System.out.println( "reflection safety: " + rsInstance + " : " + rsNewInstance );
    }
}
