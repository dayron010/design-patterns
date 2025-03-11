package org.example.structural.bridge.example1;

// Concrete Implementor 2
public class Radio implements Device {

    @Override
    public void turnOn() {
        System.out.println("Radio ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio OFF");
    }
}
