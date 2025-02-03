package org.example.creational.prototype.hero.factory;

import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Beast;
import org.example.creational.prototype.hero.Mage;
import org.example.creational.prototype.hero.Warlord;

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
