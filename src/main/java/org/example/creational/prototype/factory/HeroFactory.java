package org.example.creational.prototype.factory;

import org.example.creational.prototype.Beast;
import org.example.creational.prototype.Mage;
import org.example.creational.prototype.Warlord;

public interface HeroFactory {

    Beast createBest();

    Mage createMage();

    Warlord createWarlord();

}
