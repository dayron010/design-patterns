package org.example.creational.abstract_factory.gui.service.impl;

import org.example.creational.abstract_factory.gui.service.Button;

public class MacOSButton implements Button {

    @Override
    public void render() {
        System.out.println("MacOS Button");
    }
}
