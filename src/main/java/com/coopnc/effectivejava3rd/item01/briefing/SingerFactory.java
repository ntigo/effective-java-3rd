package com.coopnc.effectivejava3rd.item01.briefing;

public class SingerFactory {

    public static Singable createSinger(String name) {
        try {
            Class<?> clz = Class.forName( name );
            try {
                return (Singable) clz.newInstance();
            } catch ( InstantiationException e ) {
                throw new RuntimeException( e );
            } catch ( IllegalAccessException e ) {
                throw new RuntimeException( e );
            }
        } catch ( ClassNotFoundException e ) {
            throw new RuntimeException( e );
        }
    }
}
