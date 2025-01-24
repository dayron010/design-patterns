package org.example.creational.abstract_factory.kingdom.elf;

import org.example.creational.abstract_factory.kingdom.Army;

public class ElfArmy implements Army {

    private static final String description = "Elf Army";

    @Override
    public String getDescription() {
        return description;
    }
}
