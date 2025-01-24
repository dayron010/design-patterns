package org.example.creational.abstract_factory.kingdom.orc;

import org.example.creational.abstract_factory.kingdom.King;

public class OrcKing implements King {

    private static final String description = "Orc king";

    @Override
    public String getDescription() {
        return description;
    }
}
