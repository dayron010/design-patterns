package org.example.structural.composite.example1;

import java.util.ArrayList;
import java.util.List;

// 4. Composite klasi
public class CompositeGraphic implements Graphic {

    private final List<Graphic> children = new ArrayList<Graphic>();

    public void add(Graphic graphic) {
        children.add(graphic);
    }

    public void remove(Graphic graphic) {
        children.remove(graphic);
    }

    @Override
    public void draw() {
        children.forEach(Graphic::draw);
    }
}
