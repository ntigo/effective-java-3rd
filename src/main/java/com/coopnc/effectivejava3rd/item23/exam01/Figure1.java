package com.coopnc.effectivejava3rd.item23.exam01;

public class Figure1 {
    enum Shape {
        RECTANGLE,
        CIRCLE, // todo 추가
        TRIANGLE
    }

    // 모양을 나타냄
    private final Shape shape;

    // 모양이 사각형일 때만 쓰인다.
    private final double length; // todo 추가
    private final double width; // todo 추가

    // 모양이 원일 때만 쓰인다.
    private double radius;

    // 사각형용 생성자
    public Figure1(double length, double width) {
        this.shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
        this.radius = 0;
    }

    // 원용 생성자
    public Figure1(double radius) {  // todo 추가
        this.shape = Shape.CIRCLE;
//        this.radius = radius;
        this.length = 0;
        this.width = 0;
    }

    public double area() {
        switch (this.shape) {
            case RECTANGLE:
                return this.length * this.width;
            case CIRCLE: // todo 추가
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Figure figure = new Figure(10, 10);
        // 어떤 타입인지 인스턴스로는 구분하기 어려움
    }
}
