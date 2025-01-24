package org.example.creational.abstract_factory.kingdom.elf;

import org.example.creational.abstract_factory.kingdom.Castle;

public class ElfCastle implements Castle {

    private static final String description = "Elf Castle";

    @Override
    public String getDescription() {
        return description;
    }
}
