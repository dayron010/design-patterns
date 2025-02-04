package org.example.creational.singleton.example;

public class App {

    public static void main(String[] args) {

        IvoryTower instance = IvoryTower.getInstance();
        IvoryTower instance2 = IvoryTower.getInstance();
        System.out.println("\ninstance = " + instance);
        System.out.println("instance2 = " + instance2);

        EnumIvoryTower enumIvoryTower = EnumIvoryTower.INSTANCE;
        EnumIvoryTower enumIvoryTower1 = EnumIvoryTower.INSTANCE;
        System.out.println("\nenumIvoryTower = " + enumIvoryTower);
        System.out.println("enumIvoryTower1 = " + enumIvoryTower1);

    }

}
