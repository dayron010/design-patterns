package org.example.behavioral.visitor;

// Visitor Interfeysi
public interface ShapeVisitor {

    void visit(Circle circle);

    void visit(Rectangle rectangle);

}
