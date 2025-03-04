package org.example.structural.adapter.shape;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        RectangleInterface rectangle = new Rectangle(20, 10);
        TriangleInterface triangle = new Triangle(20, 10);
        RectangleInterface adapter = new Adapter(triangle);

        List<RectangleInterface> rectangles = List.of(rectangle, adapter);
        rectangles.forEach(System.out::println);

    }

    static double getDetails(RectangleInterface rectangle) {
        rectangle.aboutMe();
        return rectangle.calculateArea();
    }

}
