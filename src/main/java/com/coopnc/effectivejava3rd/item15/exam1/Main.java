package com.coopnc.effectivejava3rd.item15.exam1;

import com.coopnc.effectivejava3rd.item15.exam1.packageA.BClass;
import com.coopnc.effectivejava3rd.item15.exam1.packageA.packageAA.AAClass;

public class Main {
    public static void main(String[] args) {
        AAClass aaClass = new BClass();
        aaClass.get();
    }
}
