package org.example.creational.prototype.factory;

import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.Beast;
import org.example.creational.prototype.Mage;
import org.example.creational.prototype.Warlord;

/**
 * Concrete factory class
 */
@RequiredArgsConstructor
public class HeroFactoryImpl implements HeroFactory {

    private final Beast beast;
    private final Mage mage;
    private final Warlord warlord;

    /**
     * Create beast
     *
     * @return Beast
     */
    @Override
    public Beast createBest() {
        return beast.copy();
    }

    /**
     * Create mage
     *
     * @return Mage
     */
    @Override
    public Mage createMage() {
        return mage.copy();
    }

    /**
     * Create warlord
     *
     * @return Warlord
     */
    @Override
    public Warlord createWarlord() {
        return warlord.copy();
    }
}
