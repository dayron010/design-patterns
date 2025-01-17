package org.example.creational.factory_method.shape.impl;

import org.example.creational.factory_method.shape.Shape;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}
