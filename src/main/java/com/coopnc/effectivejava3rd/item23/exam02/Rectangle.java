package com.coopnc.effectivejava3rd.item23.exam02;

public class Rectangle extends Figure {
    // 모양이 사각형일 때만 쓰인다.
    private final double length; // todo 추가
    private final double width; // todo 추가

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return this.length * this.width;
    }
}
