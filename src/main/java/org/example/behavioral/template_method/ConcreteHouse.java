package org.example.behavioral.template_method;

// Concrete Class 2: Beton uy
public class ConcreteHouse extends HouseTemplate {
    @Override
    void buildWalls() {
        System.out.println("Beton devorlar qurildi.");
    }

    @Override
    void buildRoof() {
        System.out.println("Beton tom qurildi.");
    }
}
