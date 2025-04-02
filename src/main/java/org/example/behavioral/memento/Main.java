package org.example.behavioral.memento;

public class Main {
    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setText("Salom Dunyo");
        System.out.println("Text: " + editor.getText());
        history.saveState(editor.save());

        // Matnni o'zgartirish
        editor.setText("Xayr Dunyo");
        System.out.println("Text: " + editor.getText());

        // Qayta tiklash
        editor.restore(history.getState());
        System.out.println("Text: " + editor.getText());

    }
}
