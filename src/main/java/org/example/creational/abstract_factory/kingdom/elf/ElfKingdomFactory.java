package org.example.creational.abstract_factory.kingdom.elf;

import org.example.creational.abstract_factory.kingdom.Army;
import org.example.creational.abstract_factory.kingdom.Castle;
import org.example.creational.abstract_factory.kingdom.King;
import org.example.creational.abstract_factory.kingdom.KingdomFactory;

/**
 * ElfKingdomFactory concrete factory
 */
public class ElfKingdomFactory implements KingdomFactory {

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}
