package com.coopnc.effectivejava3rd.item03.reflection;

public enum Singleton {

    INSTANCE;

    private static String name = "";

    public static String get(){
        return name;
    }


}
