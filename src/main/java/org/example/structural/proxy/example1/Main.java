package org.example.structural.proxy.example1;

public class Main {

    public static void main(String[] args) {

        ProxyImage image = new ProxyImage("qandaydir.jpg");

        // Birinchi marta chaqirilganda fayl yuklanadi va ko‘rsatiladi
        image.display();

        // Ikkinchi marta chaqirilganda faqat ko‘rsatiladi (yuklashsiz)
        image.display();

    }

}
