package org.example.creational.prototype.hero;

import lombok.SneakyThrows;

/**
 * Prototype
 */
public abstract class Prototype<T> implements Cloneable {

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public T copy() {
        return (T) super.clone();
    }

}
