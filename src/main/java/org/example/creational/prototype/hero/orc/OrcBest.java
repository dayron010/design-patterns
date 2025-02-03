package org.example.creational.prototype.hero.orc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Beast;

/**
 * OrcBest
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class OrcBest extends Beast {

    private final String weapon;

    public OrcBest(Beast beast, String weapon) {
        super(beast);
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Orcish wolf attacks with " + weapon;
    }
}
