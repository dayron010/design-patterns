package org.example.structural.composite.example1;

// 3. Leaf klasi (Boshqa implement)
public class Square implements Graphic {

    @Override
    public void draw() {
        System.out.println("Drawing square");
    }

}
