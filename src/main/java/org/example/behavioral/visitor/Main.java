package org.example.behavioral.visitor;

public class Main {
    public static void main(String[] args) {

        // Shakllar ro'yxati

        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6)
        };

        // Visitorlar
        ShapeVisitor areaCalculator = new AreaCalculator();
        ShapeVisitor perimeterCalculator = new PerimeterCalculator();

        // Har bir shakl uchun yuzani hisoblash
        System.out.println("\nYuzalarni hisoblash:");
        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        // Har bir shakl uchun perimetrlarni hisoblash
        System.out.println("\nPerimetrlarni hisoblash:");
        for (Shape shape : shapes) {
            shape.accept(perimeterCalculator);
        }

    }
}
