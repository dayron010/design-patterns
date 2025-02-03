package org.example.creational.prototype.hero.orc;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Warlord;

/**
 * OrcWarlord
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class OrcWarlord extends Warlord {

    private final String weapon;

    public OrcWarlord(Warlord warlord, String weapon) {
        super(warlord);
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "Orcish warlord attacks with " + weapon;
    }
}
