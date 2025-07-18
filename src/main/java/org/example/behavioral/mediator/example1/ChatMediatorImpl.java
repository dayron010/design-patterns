package org.example.behavioral.mediator.example1;

import java.util.ArrayList;
import java.util.List;

public class ChatMediatorImpl implements ChatMediator {

    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // O'ziga o'zi xabar jo'natishini oldini olamiz
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
