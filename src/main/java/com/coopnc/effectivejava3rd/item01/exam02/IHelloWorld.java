package com.coopnc.effectivejava3rd.item01.exam02;

import java.util.Locale;

public interface IHelloWorld {
    void display();

    // JDK 8부터 인터페이스에도 정적 메소드를 선언할 수 있음. 한정자를 명세하지 않을 경우 기본 public 으로 제공
    static IHelloWorld getHelloWorldFromInterface(Locale locale) {
        IHelloWorld helloWorld;
        if ( locale == Locale.KOREAN || locale == Locale.KOREA ) {
            helloWorld = new HelloWorldKr();
        }
        else if ( locale == Locale.ENGLISH || locale == Locale.US ) {
            helloWorld = new HelloWorldEn();
        }
        else {
            throw new UnsupportedOperationException("locale is not found");
        }

        return helloWorld;
    }
}
