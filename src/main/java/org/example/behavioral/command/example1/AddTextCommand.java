package org.example.behavioral.command.example1;

// 3. Concrete Command klass
public class AddTextCommand implements Command {

    private TextEditor textEditor;
    private String textToAdd;

    public AddTextCommand(TextEditor textEditor, String textToAdd) {
        this.textEditor = textEditor;
        this.textToAdd = textToAdd;
    }

    @Override
    public void execute() {
        textEditor.addText(textToAdd);
    }
}
