package org.example.structural.bridge.example1;

public class Main {

    public static void main(String[] args) {

        Device tv = new TV();
        RemoteControl remoteTv = new BasicRemote(tv);
        remoteTv.turnOn();
        remoteTv.turnOff();

        System.out.println("--------------------");

        Device radio = new Radio();
        RemoteControl remoteRadio = new BasicRemote(radio);
        remoteRadio.turnOn();
        remoteRadio.turnOff();

    }
}
