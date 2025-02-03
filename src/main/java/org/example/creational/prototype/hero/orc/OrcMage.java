package org.example.creational.prototype.hero.orc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Mage;

/**
 * OrcMage
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class OrcMage extends Mage {

    private final String weapon;

    public OrcMage(Mage mage, String weapon) {
        super(mage);
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Orcish mage attacks with " + weapon;
    }
}
