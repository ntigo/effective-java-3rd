package com.coopnc.effectivejava3rd.item03.briefing;

public class MockSigleton implements ISingle {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
    
    @Override
    public boolean send( String message ) {
        System.out.println( "mock : " + message );
        return false;
    }
}
