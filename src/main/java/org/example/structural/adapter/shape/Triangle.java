package org.example.structural.adapter.shape;

import lombok.ToString;

@ToString
public class Triangle implements TriangleInterface {

    private final double baseLength;
    private final double height;

    public Triangle(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }

    @Override
    public void about() {
        System.out.println("Shape about the triangle");
    }

    @Override
    public double calculateArea() {
        return 0.5 * baseLength * height;
    }
}
