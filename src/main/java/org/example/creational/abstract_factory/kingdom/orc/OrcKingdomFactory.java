package org.example.creational.abstract_factory.kingdom.orc;

import org.example.creational.abstract_factory.kingdom.Army;
import org.example.creational.abstract_factory.kingdom.Castle;
import org.example.creational.abstract_factory.kingdom.King;
import org.example.creational.abstract_factory.kingdom.KingdomFactory;

/**
 * OrcKingdomFactory concrete factory
 */
public class OrcKingdomFactory implements KingdomFactory {

    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}
