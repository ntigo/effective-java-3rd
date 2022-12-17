package com.ntigo.study.effectivejava3rd.item03.assist;

public class GenericSingleton<T> implements ISingleton {
    public static final GenericSingleton<?> INSTANCE = new GenericSingleton<>();

    private GenericSingleton() {
    }

    public static <T> GenericSingleton<T> getInstance() {
        return (GenericSingleton<T>) INSTANCE;
//        return (GenericSingleton<T>) Holder.INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        return false;
    }

    private static class Holder {
        static final GenericSingleton<?> INSTANCE = new GenericSingleton<>();
    }
}
