package org.example.behavioral.chain_of_responsibility;

// Handler klasi
public abstract class Approver {

    protected Approver nextApprover;

    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    abstract protected boolean canApprove(double amount);

    abstract protected void approve(double amount);

    public void processRequest(double amount) {
        if (canApprove(amount)) {
            approve(amount);
        } else if (nextApprover != null) {
            nextApprover.processRequest(amount);
        } else {
            System.out.println("Hech kim bu miqdorni tasdiqlay olmadi: " + amount);
        }
    }

}
