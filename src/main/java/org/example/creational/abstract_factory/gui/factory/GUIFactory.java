package org.example.creational.abstract_factory.gui.factory;

import org.example.creational.abstract_factory.gui.service.Button;
import org.example.creational.abstract_factory.gui.service.Checkbox;

/**
 * Abstract factory
 */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();

}
