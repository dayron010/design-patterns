package org.example.creational.abstract_factory.kingdom.orc;

import org.example.creational.abstract_factory.kingdom.Castle;

public class OrcCastle implements Castle {

    private static final String description = "Orc Castle";

    @Override
    public String getDescription() {
        return description;
    }
}
