package org.example.structural.decorator.eample1;

// Concrete decorator (Sut uchun)
public class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoraterCoffee.getDescription() + ", Milk";
    }

    @Override
    public Double getCost() {
        return decoraterCoffee.getCost() + 0.5; // Sut uchun qo'shimcha narx
    }
}
