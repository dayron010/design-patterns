package org.example.creational.prototype.hero.factory;

import org.example.creational.prototype.hero.Beast;
import org.example.creational.prototype.hero.Mage;
import org.example.creational.prototype.hero.Warlord;

public interface HeroFactory {

    Beast createBest();

    Mage createMage();

    Warlord createWarlord();

}
