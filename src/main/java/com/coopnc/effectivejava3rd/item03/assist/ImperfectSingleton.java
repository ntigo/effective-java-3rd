package com.coopnc.effectivejava3rd.item03.assist;

public class ImperfectSingleton {
    public static ImperfectSingleton instance;

    public static ImperfectSingleton getInstance() {
        if ( null == instance ) {
            instance = new ImperfectSingleton();
        }

        return instance;
    }
}
