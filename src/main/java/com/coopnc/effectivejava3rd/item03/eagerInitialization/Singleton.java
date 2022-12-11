package com.coopnc.effectivejava3rd.item03.eagerInitialization;

public class Singleton {
    private static final Singleton instance = new Singleton();
    
    private  Singleton(){};

    public static Singleton getInstance(){
        return instance;
    }


}
