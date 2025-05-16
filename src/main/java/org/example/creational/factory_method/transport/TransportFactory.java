package org.example.creational.factory_method.transport;

// 3. Creator - abstract class
public abstract class TransportFactory {

    // Factory method
    public abstract Transport createTransport();

    // Creator class can include operations with the product
    public void startTransport() {
        // Call the factory method
        Transport transport = createTransport();

        // Work with the created product
        transport.move();
    }

}
