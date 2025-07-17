package org.example.behavioral.command.example1;

// Invoker klasi
public class EditorController {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void performAction() {
        command.execute();
    }
}
