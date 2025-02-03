package org.example.creational.prototype.hero.elf;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.example.creational.prototype.hero.Mage;

/**
 * ElfMage
 */
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ElfMage extends Mage {

    private final String helpType;

    public ElfMage(Mage mage, String helpType) {
        super(mage);
        this.helpType = helpType;
    }

    @Override
    public String toString() {
        return "Elven mage helps in" + helpType;
    }
}
