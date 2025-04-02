package org.example.behavioral.memento;

// Originator - Holatni yaratuvchi va tiklovchi class
public class TextEditor {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    // Memento obyektini yaratish
    public Memento save() {
        return new Memento(text);
    }

    // Mementodan holatni qayta tiklash
    public void restore(Memento memento) {
        this.text = memento.getState();
    }

}
