package org.example.creational.prototype;

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
