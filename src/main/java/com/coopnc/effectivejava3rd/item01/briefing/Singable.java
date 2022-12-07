package com.coopnc.effectivejava3rd.item01.briefing;

public interface Singable {
    void sing();

    static Singable createSinger(String name) {
        if( "ADELE".equalsIgnoreCase( name ))
            return new Adele();
        else if( "IU".equalsIgnoreCase( name ))
            return new IU();

        return null;
    }
}
