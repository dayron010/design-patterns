package org.example.behavioral.visitor;

// Concrete visitor:AreaCalculator
public class AreaCalculator implements ShapeVisitor {

    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Doira yuzi: " + area);
    }

    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("To'rtburchak yuzi: " + area);
    }
}
