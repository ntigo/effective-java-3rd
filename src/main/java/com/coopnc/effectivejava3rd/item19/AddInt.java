package com.coopnc.effectivejava3rd.item19;

public class AddInt extends AbstractSafeAddInt {

    // add 메서드 재정의로 인한 addAll 메서드 비정상 동작
    @Override
    public int add( int num1, int num2 ) {
        return num1;
    }

}
