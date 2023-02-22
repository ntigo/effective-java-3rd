package com.coopnc.effectivejava3rd;

import com.coopnc.effectivejava3rd.item24.exam1.OutsideClass;
import com.coopnc.effectivejava3rd.item24.exam2.AnonymousClass;
import com.coopnc.effectivejava3rd.item24.exam2.LocalClass;
import com.coopnc.effectivejava3rd.item24.exam3.WhatTheLambda;

public class Main {
    public static void main( String[] args ) {
        OutsideClass outsideClass = new OutsideClass();
        OutsideClass.NonStaticClass nonStaticClass = outsideClass.new NonStaticClass();
        nonStaticClass.print();

//        OutsideClass.StaticClass staticClass = new OutsideClass.StaticClass();
//        staticClass.print();
    }
}
