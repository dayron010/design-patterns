package org.example.creational.factory_method.shape;

import org.example.creational.factory_method.shape.enums.ShapeType;
import org.example.creational.factory_method.shape.factory.ShapeFactory;

public class Main {

    public static void main(String[] args) {

        Shape circle = ShapeFactory.getShape(ShapeType.CIRCLE);
        circle.draw();

        Shape rectangle = ShapeFactory.getShape(ShapeType.RECTANGLE);
        rectangle.draw();

    }

}
