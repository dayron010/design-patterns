package org.example.behavioral.command;

// 5. Client kodi
public class Main {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();

        Command addCommand = new AddTextCommand(editor, "Salom ");
        Command addCommand1 = new AddTextCommand(editor, "Dunyo!");
        Command deleteCommand = new DeleteTextCommand(editor, 3);

        EditorController controller = new EditorController();

        controller.setCommand(addCommand);
        controller.performAction();

        controller.setCommand(addCommand1);
        controller.performAction();

        controller.setCommand(deleteCommand);
        controller.performAction();

    }
}
