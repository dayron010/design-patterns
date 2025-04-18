package org.example.behavioral.strategy;

// Concrete Strategy 2: Ko'paytirish
public class MultiplyOperation implements Operation {

    @Override
    public int execute(int num1, int num2) {
        return num1 * num2;
    }

}
