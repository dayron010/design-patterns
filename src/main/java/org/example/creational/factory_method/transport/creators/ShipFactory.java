package org.example.creational.factory_method.transport.creators;

import org.example.creational.factory_method.transport.Transport;
import org.example.creational.factory_method.transport.TransportFactory;
import org.example.creational.factory_method.transport.products.Ship;

// 4. Concrete Creators
public class ShipFactory extends TransportFactory {

    @Override
    public Transport createTransport() {
        return new Ship();
    }

}
