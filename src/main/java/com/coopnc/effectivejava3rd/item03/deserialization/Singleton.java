package com.coopnc.effectivejava3rd.item03.deserialization;

import java.io.ObjectInput;
import java.io.Serializable;

public class Singleton  implements Serializable {

    private static final Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }

    protected Object readResolve(){
        return instance;
    }

}
