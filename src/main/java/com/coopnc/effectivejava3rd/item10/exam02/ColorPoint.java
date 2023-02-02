package com.coopnc.effectivejava3rd.item10.exam02;

import java.awt.*;

public class ColorPoint extends Point {

    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    // 대칭성 위배
    @Override
    public boolean equals(Object o) {
        // ColorPoint일때만 수행
        if(!(o instanceof ColorPoint))
            return false;

        return super.equals(o) && this.color == ((ColorPoint) o).color;
    }

    // 추이성 위배
//    @Override
//    public boolean equals(Object o) {
//        if(!(o instanceof Point))
//            return false;
//
//        // o가 일반 Point면 색상은 무시, x,y만 비교
//        if(!(o instanceof ColorPoint))
//            return o.equals(this);
//
//        // o가 ColorPoint면 색상까지 비교
//        return super.equals(o) && this.color == ((ColorPoint) o).color;
//    }
}

class ColorPointTest {
    public static void main( String[] args ) {

        // 대칭성 위배
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1, 2, Color.RED);
        System.out.println(p.equals(cp)); //true
        System.out.println(cp.equals(p)); //false


        // 추이성 위배
//        ColorPoint a = new ColorPoint(1, 2, Color.RED);
//        Point b = new Point(1, 2);
//        ColorPoint c = new ColorPoint(1, 2, Color.BLUE);
//        System.out.println(a.equals(b)); //true
//        System.out.println(b.equals(c)); //true
//        System.out.println(a.equals(c)); //false

    }
}