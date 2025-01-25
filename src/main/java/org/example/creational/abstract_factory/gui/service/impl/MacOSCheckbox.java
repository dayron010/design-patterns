package org.example.creational.abstract_factory.gui.service.impl;

import org.example.creational.abstract_factory.gui.service.Checkbox;

public class MacOSCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Mac OS Checkbox");
    }
}
