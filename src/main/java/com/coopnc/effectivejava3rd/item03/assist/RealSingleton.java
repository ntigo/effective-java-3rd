package com.coopnc.effectivejava3rd.item03.assist;

public class RealSingleton implements ISingleton {
    private static final RealSingleton INSTANCE = new RealSingleton();

    private RealSingleton() {
    }

    public static RealSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        // 비용 발생
        System.out.println( "실제 객체 사용 루틴 - 비용 발생" );
        return false;
    }
}
