package com.coopnc.effectivejava3rd.item03.assist;

public class HolderSingleton {
    private HolderSingleton() {}

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }
}
