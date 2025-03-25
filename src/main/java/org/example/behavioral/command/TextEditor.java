package org.example.behavioral.command;

// 2. Reciever klasi (matn bilan ishlash uchun)
public class TextEditor {

    private StringBuilder text = new StringBuilder();

    public void addText(String newText) {
        this.text.append(newText);
        System.out.println("Matn qo'shildi: " + this.text);
    }

    public void deleteText(int length) {
        if (!text.isEmpty()) {
            text.setLength(Math.max(0, text.length() - length));
            System.out.println("Matn o'chirildi: " + text);
        }
    }

    public String getText() {
        return text.toString();
    }

}
