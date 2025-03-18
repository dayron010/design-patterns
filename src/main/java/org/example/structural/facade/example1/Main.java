package org.example.structural.facade.example1;

public class Main {
    public static void main(String[] args) {
        // Use facade
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
