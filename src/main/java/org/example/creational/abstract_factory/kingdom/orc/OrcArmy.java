package org.example.creational.abstract_factory.kingdom.orc;

import org.example.creational.abstract_factory.kingdom.Army;

public class OrcArmy implements Army {

    private static final String description = "Orc army";

    @Override
    public String getDescription() {
        return description;
    }
}
