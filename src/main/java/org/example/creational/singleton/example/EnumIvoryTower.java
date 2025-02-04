package org.example.creational.singleton.example;

/**
 * Enum based singleton implementation
 *
 */
public enum EnumIvoryTower {

    INSTANCE;

    @Override
    public String toString() {
        return getDeclaringClass().getCanonicalName() + "@" + hashCode();
    }
}
