package org.example.creational.abstract_factory.gui.service.impl;

import org.example.creational.abstract_factory.gui.service.Button;

public class WindowsButton implements Button {

    @Override
    public void render() {
        System.out.println("Windows Button");
    }
}
