package org.example.structural.bridge.example1;

// Concrete Implementor 1
public class TV implements Device {

    @Override
    public void turnOn() {
        System.out.println("TV ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV OFF");
    }
}
