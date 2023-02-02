package com.coopnc.effectivejava3rd.item10.exam02;

import java.awt.*;

public class SmellPoint extends Point {
    private final Smell smell;

    public SmellPoint(int x, int y, Smell smell) {
        super(x, y);
        this.smell = smell;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point))
            return false;

        //o가 일반 Point면 x,y만 비교 - 무한재귀
        if(!(o instanceof SmellPoint))
            return o.equals(this);

        //o가 SmellPoint면 냄새까지 비교
        return super.equals(o) && this.smell == ((SmellPoint) o).smell;
    }
}

class SmellPointTest {
    public static void main( String[] args ) {
        ColorPoint colorPoint = new ColorPoint(1, 2, Color.RED);
        SmellPoint smellPoint = new SmellPoint(1, 2, Smell.FRUIT);

        System.out.println(colorPoint.equals(smellPoint));  //무한재귀
    }
}
