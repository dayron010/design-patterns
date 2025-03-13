package org.example.structural.decorator.eample1;

// Concrete component
public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public Double getCost() {
        return 2D; // Oddiy kofening narxi
    }
}
