package com.coopnc.effectivejava3rd.item03.briefing;

public class Singleton implements ISingle {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public boolean send( String message ) {
        // cost sms send
        System.out.println( "cost: " +  message );
        return true;
    }
}
