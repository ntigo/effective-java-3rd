package com.coopnc.effectivejava3rd.item01.briefing;

public class IVE implements Singable {

    Utility utility;
    @Override
    public void sing() {
        System.out.println( "What's after like" );
        Utility.convertStrToInt();
    }
}
