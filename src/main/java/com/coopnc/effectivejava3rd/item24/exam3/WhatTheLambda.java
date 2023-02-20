package com.coopnc.effectivejava3rd.item24.exam3;

public class WhatTheLambda {
    public void Test() {
        RodongInterface rodongInterface = () -> System.out.println( "아니 이게 무엇이죠...?" );
        rodongInterface.justPrint();
    }

    public void anonymousMethod() {
        // it's Runnable
        new Thread( () -> {
            System.out.println( "우와우우우웅" );
        } ).start();
    }
}
