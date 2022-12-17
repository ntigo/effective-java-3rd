package com.coopnc.effectivejava3rd.item03.assist;

public class FieldSingleton {
    public static final FieldSingleton INSTANCE = new FieldSingleton();

    private FieldSingleton() {}
}
