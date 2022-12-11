package com.coopnc.effectivejava3rd.item03.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {

    public  static void main() {
        Singleton singleton1 = Singleton.INSTANCE;
        Constructor<Singleton> constructor = null;
        try {
            constructor = Singleton.class.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        constructor.setAccessible(true);

        Singleton singleton2 = null;
        try {
            singleton2 = constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        System.out.println(singleton2 == singleton1);

        Singleton.INSTANCE.name();
    }

}
