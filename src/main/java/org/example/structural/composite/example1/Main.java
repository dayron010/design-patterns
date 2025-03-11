package org.example.structural.composite.example1;

// 5. Asosi klass (Client klass)
public class Main {
    public static void main(String[] args) {

        // Leaf obyektlar yaratamiz
        Graphic circle1 = new Circle();
        Graphic circle2 = new Circle();
        Graphic square = new Square();

        // Composite obyekt yaratamiz va unga elementlar qo‘shamiz
        CompositeGraphic composite = new CompositeGraphic();
        composite.add(circle1);
        composite.add(circle2);
        composite.add(square);

        // Barcha elementlarni chizamiz
        System.out.println("Composite Drawing");
        composite.draw();

    }
}
