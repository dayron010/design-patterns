package org.example.creational.abstract_factory.gui.factory.impl;

import org.example.creational.abstract_factory.gui.service.Button;
import org.example.creational.abstract_factory.gui.service.Checkbox;
import org.example.creational.abstract_factory.gui.service.impl.WindowsButton;
import org.example.creational.abstract_factory.gui.service.impl.WindowsCheckbox;
import org.example.creational.abstract_factory.gui.factory.GUIFactory;

/**
 * Concrete factory
 */
public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
