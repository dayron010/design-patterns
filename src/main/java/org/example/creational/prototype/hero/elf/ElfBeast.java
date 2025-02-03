package org.example.creational.prototype.hero.elf;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Beast;

/**
 * ElfBeast
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ElfBeast extends Beast {

    private final String helpType;

    public ElfBeast(ElfBeast elfBeast) {
        super(elfBeast);
        this.helpType = elfBeast.helpType;
    }

    @Override
    public String toString() {
        return "Elven eagle helps in" + helpType;
    }
}
