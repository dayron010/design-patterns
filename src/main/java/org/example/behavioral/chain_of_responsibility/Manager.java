package org.example.behavioral.chain_of_responsibility;

// ConcreteHandler 2: Manager
public class Manager extends Approver {

    @Override
    protected boolean canApprove(double amount) {
        return amount <= 5000;
    }

    @Override
    protected void approve(double amount) {
        System.out.println("Manager tasdiqladi: " + amount);
    }
}
