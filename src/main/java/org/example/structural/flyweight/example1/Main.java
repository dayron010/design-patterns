package org.example.structural.flyweight.example1;

import java.util.Random;

public class Main {

    private static final String[] colors = {"Qizil", "Yashil", "Ko'k", "Sariq", "Oq"};

    public static void main(String[] args) {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String color = getRandomColor();

            int x = random.nextInt(100);
            int y = random.nextInt(100);

            Shape circle = ShapeFactory.getCircle(color);
            circle.draw(x, y);
        }
    }

    private static String getRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }

}
