package org.example.structural.flyweight.example1;

import java.util.HashMap;

// Flyweight factory class
public class ShapeFactory {

    private static final HashMap<String, Shape> circleMap = new HashMap<String, Shape>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("\nAylana yaratildi: Rang = " + color);
        }

        return circle;
    }

}
