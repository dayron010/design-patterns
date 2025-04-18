package org.example.behavioral.strategy;

// Concrete Strategy 1: Qo'shish
public class AddOperation implements Operation {

    @Override
    public int execute(int num1, int num2) {
        return num1 + num2;
    }

}
