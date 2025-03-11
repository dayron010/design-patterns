package org.example.structural.composite.example1;

// 2. Leaf klasi (Yagona implement)
public class Circle implements Graphic {

    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }

}
