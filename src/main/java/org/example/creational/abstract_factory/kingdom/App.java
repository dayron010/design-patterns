package org.example.creational.abstract_factory.kingdom;

public class App implements Runnable {

    private final Kingdom kingdom = new Kingdom();

    public static void main(String[] args) {
        var app = new App();
        app.run();
    }

    @Override
    public void run() {
        System.out.println("\n====Elf kingdom====");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        System.out.println(kingdom.getKing().getDescription());
        System.out.println(kingdom.getArmy().getDescription());
        System.out.println(kingdom.getCastle().getDescription());

        System.out.println("\n====Orc kingdom====");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        System.out.println(kingdom.getKing().getDescription());
        System.out.println(kingdom.getArmy().getDescription());
        System.out.println(kingdom.getCastle().getDescription());
    }

    public void createKingdom(Kingdom.FactoryMaker.KingdomType kingdomType) {
        KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
        kingdom.setKing(kingdomFactory.createKing());
        kingdom.setArmy(kingdomFactory.createArmy());
        kingdom.setCastle(kingdomFactory.createCastle());
    }
}
