package org.example.behavioral.template_method;

// Concrete Class 1: yog'och uy
public class WoodenHouse extends HouseTemplate {
    @Override
    void buildWalls() {
        System.out.println("Yog'och devorlar qurildi.");
    }

    @Override
    void buildRoof() {
        System.out.println("Yog'och tom qurildi.");
    }

    @Override
    void buildWindows() {
        System.out.println("Yog'och ramkali oynalar qo'yildi.");
    }
}
