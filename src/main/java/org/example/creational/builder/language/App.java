package org.example.creational.builder.language;

public class App {

    public static void main(String[] args) {

        User user = new User.Builder()
                .username("nimadir")
                .password("0000")
                .age(20)
                .build();

        System.out.println(user);

    }

}
