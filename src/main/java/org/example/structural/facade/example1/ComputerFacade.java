package org.example.structural.facade.example1;

// Facade class
public class ComputerFacade {

    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    // Run a computer through a single method
    public void start() {
        System.out.println("Computer starting...");
        cpu.start();
        memory.load();
        hardDrive.read();
        System.out.println("The computer has been successfully launched!");
    }
}
