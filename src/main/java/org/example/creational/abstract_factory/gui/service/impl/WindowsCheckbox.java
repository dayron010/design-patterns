package org.example.creational.abstract_factory.gui.service.impl;

import org.example.creational.abstract_factory.gui.service.Checkbox;

public class WindowsCheckbox implements Checkbox {

    @Override
    public void render() {
        System.out.println("Windows Checkbox");
    }
}
