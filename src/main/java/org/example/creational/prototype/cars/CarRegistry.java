package org.example.creational.prototype.cars;

import java.util.HashMap;
import java.util.Map;

// 3. Prototype Registry (obyektlarni saqlash va qayta foydalanish)
class CarRegistry {
    private Map<String, Prototype> carMap = new HashMap<>();

    public void addCar(String key, Prototype car) {
        carMap.put(key, car);
    }

    public Prototype getCar(String key) {
        return carMap.get(key).clone();
    }
}