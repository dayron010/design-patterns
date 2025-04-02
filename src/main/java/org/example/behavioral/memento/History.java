package org.example.behavioral.memento;

// Caretaker - Mementolarni boshqaruvchi class
public class History {

    private Memento memento;

    public void saveState(Memento memento) {
        this.memento = memento;
    }

    public Memento getState() {
        return memento;
    }

}
