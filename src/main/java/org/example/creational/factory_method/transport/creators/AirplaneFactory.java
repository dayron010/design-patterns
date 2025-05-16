package org.example.creational.factory_method.transport.creators;

import org.example.creational.factory_method.transport.Transport;
import org.example.creational.factory_method.transport.TransportFactory;
import org.example.creational.factory_method.transport.products.AirPlane;

// 4. Concrete Creators
public class AirplaneFactory extends TransportFactory {

    @Override
    public Transport createTransport() {
        return new AirPlane();
    }

}
