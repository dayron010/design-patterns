package org.example.behavioral.visitor;

// Concrete visitor: PerimeterCalculator
public class PerimeterCalculator implements ShapeVisitor {

    @Override
    public void visit(Circle circle) {
        double perimeter = 2 * Math.PI * circle.getRadius();
        System.out.println("Aylana perimetri: " + perimeter);
    }

    @Override
    public void visit(Rectangle rectangle) {
        double perimeter = 2 * (rectangle.getWidth() + rectangle.getHeight());
        System.out.println("To'rtburchak perimetri: " + perimeter);
    }
}
