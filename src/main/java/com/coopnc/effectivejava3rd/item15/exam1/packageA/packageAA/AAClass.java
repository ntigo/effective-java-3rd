package com.coopnc.effectivejava3rd.item15.exam1.packageA.packageAA;

public class AAClass {
    private int privateInt = 0;
    int privatePackageInt = 1;
    public int publicInt = 2;
    protected int protectedInt = 3;

    public void get() {
        System.out.println("call get method in AAClass");
    }
}
