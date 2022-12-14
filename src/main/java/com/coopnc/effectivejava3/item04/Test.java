package com.coopnc.effectivejava3rd.item04.exam02;


public class Test {
    int i = 10;
    public static void main(String[] args) {
        Test t; // Test 타입의 t 선언
        t = new Test(); // t 안에 Test Class 넣어주기
        System.out.println(t.i);
    }
}