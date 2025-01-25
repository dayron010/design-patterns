package org.example.creational.abstract_factory.gui;

import org.example.creational.abstract_factory.gui.factory.GUIFactory;
import org.example.creational.abstract_factory.gui.factory.impl.MacOSFactory;
import org.example.creational.abstract_factory.gui.factory.impl.WindowsFactory;
import org.example.creational.abstract_factory.gui.service.Button;
import org.example.creational.abstract_factory.gui.service.Checkbox;

/**
 * Client code
 */
public class App {

    private static void clientCode(GUIFactory factory) {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }

    public static void main(String[] args) {

        // Use Windows factory
        GUIFactory windowsFactory = new WindowsFactory();
        System.out.println("\nUsing Windows Factory: ");
        clientCode(windowsFactory);

        // Use macOS Factory
        GUIFactory macOSFactory = new MacOSFactory();
        System.out.println("\nUsing MacOS Factory: ");
        clientCode(macOSFactory);

    }

}
