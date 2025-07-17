package org.example.behavioral.chain_of_responsibility.example1;

// Client code
public class Main {

    private static Approver getApprovalChain() {
        Approver teamLead = new TeamLead();
        Approver manager = new Manager();
        Approver director = new Director();

        teamLead.setNextApprover(manager);
        manager.setNextApprover(director);

        return teamLead;
    }

    public static void main(String[] args) {
        Approver approvalChain = getApprovalChain();

        // Turli summalar bilan tekshirish
        approvalChain.processRequest(500); //Team Lead tasdiqlaydi
        approvalChain.processRequest(2500); //Manager tasdiqlaydi
        approvalChain.processRequest(15000); //Director tasdiqlaydi
        approvalChain.processRequest(25000); //Hech kim tasdiqlay olmaydi

    }

}
