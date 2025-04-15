package org.example.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Subject klasi
public class NewsAgency {

    private String news;
    private List<Observer> observers = new ArrayList<>();

    // Observer qo'shish
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Observer o'chirish
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Yangiliklarni o'rnatish va barcha Observerlarga xabar berish
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    // Observerlarni xabardor qilish
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(news));
    }

}
