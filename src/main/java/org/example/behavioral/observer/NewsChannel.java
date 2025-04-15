package org.example.behavioral.observer;

// Concrete Observer
public class NewsChannel implements Observer {

    private String channelName;

    public NewsChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void update(String news) {
        System.out.println(channelName + " yangiliklar oldi: " + news);
    }
}
