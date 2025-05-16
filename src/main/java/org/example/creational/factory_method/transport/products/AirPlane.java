package org.example.creational.factory_method.transport.products;

import org.example.creational.factory_method.transport.Transport;

public class AirPlane implements Transport {

    @Override
    public void move() {
        System.out.println("Airplane is flying in the sky ...");
    }

}
