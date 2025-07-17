package org.example.behavioral.chain_of_responsibility.example1;

// ConcreteHandler 3: Director
public class Director extends Approver {

    @Override
    protected boolean canApprove(double amount) {
        return amount <= 20000;
    }

    @Override
    protected void approve(double amount) {
        System.out.println("Director tasdiqladi " + amount);
    }
}
