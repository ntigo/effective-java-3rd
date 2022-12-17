package com.coopnc.effectivejava3rd.item03.assist;

public class MockSingleton implements ISingleton {
    private static final MockSingleton INSTANCE = new MockSingleton();

    private MockSingleton() {
    }

    public static MockSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        // 가짜 구현체를 이용하여 비용 발생하지 않음.
        System.out.println( "가짜 Mock 사용 루틴 - 비용 미발생" );
        return false;
    }
}
