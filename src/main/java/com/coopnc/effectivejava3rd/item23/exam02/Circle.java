package com.coopnc.effectivejava3rd.item23.exam02;

public class Circle extends Figure {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (this.radius * this.radius);
    }
}
