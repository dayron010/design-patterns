package org.example.creational.prototype;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Mage extends Prototype<Mage> {

    public Mage(Mage mage) {
    }
}
