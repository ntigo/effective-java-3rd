package com.coopnc.effectivejava3rd.item24.exam1;

import java.util.Set;

public class CalculatorExample {
    /**
     * 바깥 클래스와 함께 쓰일 때 유용한 public 도우미 클래스
     */
    public void calculatorTest() {
        Calculator calculator = new Calculator();
        int result = calculator.execute( Calculator.Operation.PLUS, 1, 3 );
    }
}




