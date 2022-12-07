package com.coopnc.effectivejava3rd;

import com.coopnc.effectivejava3rd.item01.exam02.HelloWorldFactory;
import com.coopnc.effectivejava3rd.item01.exam04.ServiceLoaderTest;

import java.util.Locale;

public class Main {
    public static void main( String[] args ) {

        HelloWorldFactory.getHelloWorld( Locale.KOREAN );
        ServiceLoaderTest.doTest();
    }
}