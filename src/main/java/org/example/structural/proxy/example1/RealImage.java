package org.example.structural.proxy.example1;

public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading file from disk:" + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying file: " + fileName);
    }
}
