package org.example.behavioral.mediator.example1;

public class Main {
    public static void main(String[] args) {

        ChatMediator chatMediator = new ChatMediatorImpl();

        User ali = new ChatUser(chatMediator, "Ali");
        User vali = new ChatUser(chatMediator, "Vali");
        User soli = new ChatUser(chatMediator, "Soli");

        chatMediator.addUser(ali);
        chatMediator.addUser(vali);
        chatMediator.addUser(soli);

        ali.send("Hammaga salom !!!");

    }
}
