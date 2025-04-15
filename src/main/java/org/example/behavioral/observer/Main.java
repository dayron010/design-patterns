package org.example.behavioral.observer;

public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        // Observerlar(kanallar) yaratish
        NewsChannel channel1 = new NewsChannel("BBC");
        NewsChannel channel2 = new NewsChannel("FORBES");

        // Observerlarni qo'shish
        agency.addObserver(channel1);
        agency.addObserver(channel2);

        // Yangilik e'lon qilish
        agency.setNews("Bugun yangi qonun qa'bul qilindi!!!");

    }
}
