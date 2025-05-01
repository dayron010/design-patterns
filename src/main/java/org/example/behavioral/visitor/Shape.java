package org.example.behavioral.visitor;

// Element interfeysi
public interface Shape {

    void accept(ShapeVisitor visitor);

}
