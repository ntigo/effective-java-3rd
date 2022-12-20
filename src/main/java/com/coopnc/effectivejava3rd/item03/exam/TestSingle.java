package com.coopnc.effectivejava3rd.item03.briefing;

public class TestSingle implements ISingle {
    private static TestSingle INSTANCE = new TestSingle();


    // 유일성 (셋팅), 효과적
    public static TestSingle getInstance() {
        return INSTANCE;
    }

    public void sendSms() {
        System.out.println( "fake sent a sms");
        // 10원 비용
    }
}
