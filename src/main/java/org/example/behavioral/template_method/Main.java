package org.example.behavioral.template_method;

public class Main {
    public static void main(String[] args) {

        System.out.println("Yog'och uy qurilmoqda ...");
        HouseTemplate woodenHouse = new WoodenHouse();
        woodenHouse.buildHouse();

        System.out.println("\nBeton uy qurilmoqda ...");
        ConcreteHouse concreteHouse = new ConcreteHouse();
        concreteHouse.buildHouse();

    }
}
