package com.coopnc.effectivejava3rd.item03.exam;

public class Singleton implements ISingle {
    private static Singleton INSTANCE = new Singleton();
    private static boolean created;

    private Singleton() {
        if( created )
            throw new UnsupportedOperationException("throw");

        created = true;
    }

    // 유일성 (셋팅), 효과적
    public static Singleton getInstance() {
        return INSTANCE;
    }

    @Override
    public void sendSms() {
        System.out.println("sent a sms");
        // 10원 비용
    }
}
