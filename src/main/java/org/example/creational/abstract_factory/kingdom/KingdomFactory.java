package org.example.creational.abstract_factory.kingdom;

/**
 * KingdomFactor factory interface
 */
public interface KingdomFactory {

    King createKing();

    Castle createCastle();

    Army createArmy();

}
