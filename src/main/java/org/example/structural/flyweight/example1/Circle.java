package org.example.structural.flyweight.example1;

public class Circle implements Shape {

    private String color;
    private int radius = 10;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println("Aylana chizildi: Reng: " + color + ", Koordinata (" + x + "," + y + "), Radius: " + radius);
    }

}
