package org.example.creational.factory_method.transport;

import org.example.creational.factory_method.transport.creators.AirplaneFactory;
import org.example.creational.factory_method.transport.creators.CarFactory;
import org.example.creational.factory_method.transport.creators.ShipFactory;

// 5. Client code
public class Main {
    public static void main(String[] args) {

        // Creating different types of transport
        TransportFactory carFactory = new CarFactory();
        TransportFactory airplaneFactory = new AirplaneFactory();
        TransportFactory shipFactory = new ShipFactory();

        // Starting the transport
        System.out.println("Creating and starting a car:");
        carFactory.startTransport();

        System.out.println("\nCreating and starting an airplane:");
        airplaneFactory.startTransport();

        System.out.println("\nCreating and starting a ship:");
        shipFactory.startTransport();

    }
}
