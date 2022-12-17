package com.coopnc.effectivejava3rd.item03.assist;

import java.io.Serializable;

public class SerializationSingleton implements Serializable {

    private static final SerializationSingleton INSTANCE = new SerializationSingleton();
//    private String name = "ntigo";
    private transient String name = "ntigo";

    private SerializationSingleton() {
    }

    public static SerializationSingleton getInstance() {
        return INSTANCE;
    }

    public void display() {
        System.out.println( this.name );
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
