package org.example.creational.builder.house;

public class BuilderPatternExample {

    public static void main(String[] args) {

        // House clasini yaratish uchun Builderdan foydalanamiz
        House house = new House.HouseBuilder()
                .foundation("Concrete Foundation")
                .structure("Wooden Structure")
                .roof("Metal Roof")
                .hasGarden(true)
                .hasSwimmingPool(false)
                .build();

        System.out.println("house = " + house);

    }

}
