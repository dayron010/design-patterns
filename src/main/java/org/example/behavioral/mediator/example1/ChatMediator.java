package org.example.behavioral.mediator.example1;

// Mediator interface
public interface ChatMediator {

    void sendMessage(String message, User user);

    void addUser(User user);

}
