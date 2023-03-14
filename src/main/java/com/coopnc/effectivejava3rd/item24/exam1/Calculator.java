package com.coopnc.effectivejava3rd.item24.exam1;

public class Calculator {
    public enum Operation {
        PLUS, MINUS, MULTIPLY, DIVIDE
    }

    public int execute( Operation operation, int first, int second ) {
        switch ( operation ) {
            case PLUS:
                return first + second;
            case MINUS:
                return first - second;
            case MULTIPLY:
                return first * second;
            case DIVIDE:
                return first / second;
            default:
                return 0;
        }
    }
}
