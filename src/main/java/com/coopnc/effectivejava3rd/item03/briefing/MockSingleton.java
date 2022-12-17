package com.coopnc.effectivejava3rd.item03.briefing;

public class MockSingleton implements ISingle {
    private static final MockSingleton INSTANCE = new MockSingleton();

    private MockSingleton() {
    }

    public static MockSingleton getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean send( String message ) {
        System.out.println( "mock : " + message );
        return false;
    }
}
