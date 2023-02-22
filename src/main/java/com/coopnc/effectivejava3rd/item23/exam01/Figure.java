package com.coopnc.effectivejava3rd.item23.exam01;

public class Figure {
    enum Shape {
        RECTANGLE,
    }

    // 모양을 나타냄
    private final Shape shape;

    // 모양이 사각형일 때만 쓰인다.
    private double length;
    private double width;

    // 사각형용 생성자
    public Figure(double length, double width) {
        this.shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    public double area() {
        switch (this.shape) {
            case RECTANGLE:
                return this.length * this.width;
            default:
                throw new AssertionError(shape);
        }
    }
}
