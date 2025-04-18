package org.example.behavioral.strategy;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        // Qo'shish strategiyasi
        calculator.setOperation(new AddOperation());
        System.out.println("10 + 5 = " + calculator.calculate(10, 5));

        // Ayrish strategiyasi
        calculator.setOperation(new SubtractOperation());
        System.out.println("10 - 5 = " + calculator.calculate(10, 5));

        // Ko'paytirish strategiyasi
        calculator.setOperation(new MultiplyOperation());
        System.out.println("10 * 5 = " + calculator.calculate(10, 5));

    }
}
