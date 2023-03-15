package com.coopnc.effectivejava3rd.item23.exam02;

public class Triangle extends Figure {
    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double area() {
        return 0;
    }
}
