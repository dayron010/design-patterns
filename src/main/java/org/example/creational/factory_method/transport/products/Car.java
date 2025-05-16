package org.example.creational.factory_method.transport.products;

import org.example.creational.factory_method.transport.Transport;

// 2. Concrete Products
public class Car implements Transport {

    @Override
    public void move() {
        System.out.println("Car is moving on the road ...");
    }

}
