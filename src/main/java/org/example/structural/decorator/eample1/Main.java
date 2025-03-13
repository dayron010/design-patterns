package org.example.structural.decorator.eample1;

public class Main {
    public static void main(String[] args) {

        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + "\tcost: " + simpleCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(milkCoffee.getDescription() + "\tcost: " + milkCoffee.getCost());

        Coffee sugarCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(sugarCoffee.getDescription() + "\tcost: " + sugarCoffee.getCost());

        Coffee milkCoffee2 = new MilkDecorator(new SugarDecorator(new SimpleCoffee()));
        System.out.println(milkCoffee2.getDescription() + "\tcost: " + milkCoffee2.getCost());

    }

}
