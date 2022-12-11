package com.coopnc.effectivejava3rd.item03.eagerInitialization;

public class App {

    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }

}
