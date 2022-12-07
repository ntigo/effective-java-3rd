package com.coopnc.effectivejava3rd.item01.exam04;


import com.coopnc.effectivejava3rd.item01.exam02.IHelloWorld;

import java.util.Optional;
import java.util.ServiceLoader;

public class ServiceLoaderTest {

    public static void doTest() {
        ServiceLoader<IHelloWorld> loader = ServiceLoader.load(IHelloWorld.class);
        Optional<IHelloWorld> first = loader.findFirst();
        first.ifPresent( h -> h.display() );

//        for ( IHelloWorld iHelloWorld : loader ) {
//            iHelloWorld.display();
//        }
    }
}
