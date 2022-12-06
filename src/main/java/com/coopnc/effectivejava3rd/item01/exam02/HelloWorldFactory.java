package com.coopnc.effectivejava3rd.item01.exam02;

import java.util.Locale;

public class HelloWorldFactory {

    private HelloWorldFactory() {
        // Don't create
    }

    public static IHelloWorld getHelloWorld( Locale locale ) {
        IHelloWorld helloWorld;
        if ( locale == Locale.KOREAN || locale == Locale.KOREA ) {
            helloWorld = new HelloWorldKr();
        } else if ( locale == Locale.ENGLISH || locale == Locale.US ) {
            helloWorld = new HelloWorldEn();
        } else {
            throw new UnsupportedOperationException( "locale is not found" );
        }

        return helloWorld;
    }

    public static IHelloWorld getHelloWorld( String name ) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clz = Class.forName( name );
        return (IHelloWorld)  clz.newInstance();
    }
}
