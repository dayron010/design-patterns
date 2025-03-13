package org.example.structural.decorator.eample1;

// Concrete decorator (Shakar uchun)
public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoraterCoffee.getDescription() + ", Sugar";
    }

    @Override
    public Double getCost() {
        return decoraterCoffee.getCost() + 2; // Shakar uchun qo'shimcha narx
    }
}
