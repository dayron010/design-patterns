package org.example.creational.abstract_factory.kingdom.elf;

import org.example.creational.abstract_factory.kingdom.King;

public class ElfKing implements King {

    private static final String description = "Elf king";

    @Override
    public String getDescription() {
        return description;
    }
}
