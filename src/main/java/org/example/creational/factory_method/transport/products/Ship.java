package org.example.creational.factory_method.transport.products;

import org.example.creational.factory_method.transport.Transport;

public class Ship implements Transport {

    @Override
    public void move() {
        System.out.println("Ship is sailing on water ...");
    }

}
