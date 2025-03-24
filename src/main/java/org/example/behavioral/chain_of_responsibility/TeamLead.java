package org.example.behavioral.chain_of_responsibility;

// ConcreteHandler 1: Team Lead
public class TeamLead extends Approver {

    @Override
    protected boolean canApprove(double amount) {
        return amount <= 1000;
    }

    @Override
    protected void approve(double amount) {
        System.out.println("Team Lead tasdiqladi: " + amount);
    }
}
