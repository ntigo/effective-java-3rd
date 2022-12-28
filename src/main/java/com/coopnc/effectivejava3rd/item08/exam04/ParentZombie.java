package com.coopnc.effectivejava3rd.item08.exam04;

public class ParentZombie {
    protected static String A = "STATIC MEMBER A";
    ParentZombie(){
        System.out.println("try create parent zombie");
        throw new IllegalArgumentException("no create");
    }
}
