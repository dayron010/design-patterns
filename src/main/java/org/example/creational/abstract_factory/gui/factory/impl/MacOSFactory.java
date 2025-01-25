package org.example.creational.abstract_factory.gui.factory.impl;

import org.example.creational.abstract_factory.gui.service.Button;
import org.example.creational.abstract_factory.gui.service.Checkbox;
import org.example.creational.abstract_factory.gui.service.impl.MacOSButton;
import org.example.creational.abstract_factory.gui.service.impl.MacOSCheckbox;
import org.example.creational.abstract_factory.gui.factory.GUIFactory;

/**
 * Concrete factory
 */
public class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
