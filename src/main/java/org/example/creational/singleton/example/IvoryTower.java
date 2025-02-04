package org.example.creational.singleton.example;

/**
 * Singleton class.
 */
public final class IvoryTower {

    IvoryTower() {
    }

    private static final IvoryTower INSTANCE = new IvoryTower();

    public static IvoryTower getInstance() {
        return INSTANCE;
    }
}
