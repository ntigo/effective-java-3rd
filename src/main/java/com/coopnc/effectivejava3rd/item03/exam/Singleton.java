package com.coopnc.effectivejava3rd.item03.briefing;

public class Singletion implements ISingle {
    private static Singleton INSTANCE = new Singleton();
    private static boolean created;

    private Singleton() {
        if( created )
            throw new UnsupportedOperationException("throw");

        created = true;
    }

    // 유일성 (셋팅), 효과적
    public static Singletion getInstance() {
        return INSTANCE;
    }

    public void sendSms() {
        System.out.println( "sent a sms");
        // 10원 비용
    }
}
