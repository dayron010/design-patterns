package org.example.structural.adapter.shape;

import lombok.ToString;

@ToString
public class Rectangle implements RectangleInterface {

    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void aboutMe() {
        System.out.println("Shape about the rectangle");
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}
