package com.coopnc.effectivejava3rd.item03.multiThread;

public class Singleton {

    private static Singleton singleton;

    public static synchronized Singleton getInstance(){
        if (singleton != null){
            singleton = new Singleton();
        }

        return  singleton;
    }
}
