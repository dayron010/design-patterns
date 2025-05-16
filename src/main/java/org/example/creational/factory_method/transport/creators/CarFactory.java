package org.example.creational.factory_method.transport.creators;

import org.example.creational.factory_method.transport.Transport;
import org.example.creational.factory_method.transport.TransportFactory;
import org.example.creational.factory_method.transport.products.Car;

// 4. Concrete Creators
public class CarFactory extends TransportFactory {

    @Override
    public Transport createTransport() {
        return new Car();
    }

}
