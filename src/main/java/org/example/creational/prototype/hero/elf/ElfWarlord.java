package org.example.creational.prototype.hero.elf;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Warlord;

/**
 * ElfWarlord
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ElfWarlord extends Warlord {

    private final String helpType;

    public ElfWarlord(Warlord warlord, String helpType) {
        super(warlord);
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return "Elven warlord helps in" + helpType;
    }
}
