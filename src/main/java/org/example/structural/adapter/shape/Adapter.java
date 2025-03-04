package org.example.structural.adapter.shape;

import lombok.ToString;

@ToString
public class Adapter implements RectangleInterface {

    private final TriangleInterface triangleInterface;

    public Adapter(TriangleInterface triangleInterface) {
        this.triangleInterface = triangleInterface;
    }

    @Override
    public void aboutMe() {
        triangleInterface.about();
    }

    @Override
    public double calculateArea() {
        return triangleInterface.calculateArea();
    }
}
