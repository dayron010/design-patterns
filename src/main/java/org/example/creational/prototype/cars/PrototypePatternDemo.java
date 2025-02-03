package org.example.creational.prototype.cars;

// 4. Test qilish
public class PrototypePatternDemo {
    public static void main(String[] args) {
        CarRegistry registry = new CarRegistry();

        // Asl obyektlarni qo'shamiz
        registry.addCar("sport", new Car("Ferrari", "Red"));
        registry.addCar("suv", new Car("Range Rover", "Black"));

        // Yangi obyektlarni nusxalash
        Car car1 = (Car) registry.getCar("sport");
        Car car2 = (Car) registry.getCar("suv");

        // Detallarni ko‘rsatish
        car1.showDetails();
        car2.showDetails();
    }
}