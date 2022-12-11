package com.coopnc.effectivejava3rd.item03;

import com.coopnc.effectivejava3rd.item03.staticInner.Singleton;

public class Item3 {

    public static void main(){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}


