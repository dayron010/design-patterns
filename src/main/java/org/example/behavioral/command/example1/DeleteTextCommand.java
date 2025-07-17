package org.example.behavioral.command.example1;

// 3. Concrete Command klass
public class DeleteTextCommand implements Command {

    private TextEditor textEditor;
    private int lengthToDelete;

    public DeleteTextCommand(TextEditor textEditor, int lengthToDelete) {
        this.textEditor = textEditor;
        this.lengthToDelete = lengthToDelete;
    }

    @Override
    public void execute() {
        textEditor.deleteText(lengthToDelete);
    }
}
