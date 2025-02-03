package org.example.creational.prototype.cars;

// 2. Konkret prototip sinfi
class Car implements Prototype {
    private String model;
    private String color;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    // Clone metodi orqali obyektni nusxalash
    @Override
    public Prototype clone() {
        return new Car(this.model, this.color);
    }

    public void showDetails() {
        System.out.println("Model: " + model + ", Color: " + color);
    }
}
