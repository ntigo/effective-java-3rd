package com.coopnc.effectivejava3rd.item03.assist;

public enum EnumSingleton implements ISingleton {
    INSTANCE;

    @Override
    public boolean send( String message ) {
        System.out.println("EnumSingleton");
        return false;
    }
}
