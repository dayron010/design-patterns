package org.example.creational.factory_method.shape.factory;

import org.example.creational.factory_method.shape.impl.Circle;
import org.example.creational.factory_method.shape.impl.Rectangle;
import org.example.creational.factory_method.shape.Shape;
import org.example.creational.factory_method.shape.enums.ShapeType;

public abstract class ShapeFactory {

    // this is a factory method
    public static Shape getShape(ShapeType shapeType) {
        return switch (shapeType) {
            case CIRCLE -> new Circle();
            case RECTANGLE -> new Rectangle();
        };
    }

}
