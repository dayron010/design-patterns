package org.example.behavioral.strategy;

// Context klasi
public class Calculator {

    private Operation operation;

    // Strategiyani o'rnatish
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    // Strategiyani ishlatish
    public int calculate(int num1, int num2) {
        if (operation == null) {
            throw new IllegalStateException("Strategiya o'rnatilmagan!");
        }
        return operation.execute(num1, num2);
    }

}
