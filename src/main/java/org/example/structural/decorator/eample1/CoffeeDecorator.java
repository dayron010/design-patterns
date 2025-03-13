package org.example.structural.decorator.eample1;

// Decorator (Abstract decorator class)
public abstract class CoffeeDecorator implements Coffee {

    protected Coffee decoraterCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoraterCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoraterCoffee.getDescription();
    }

    @Override
    public Double getCost() {
        return decoraterCoffee.getCost();
    }
}
