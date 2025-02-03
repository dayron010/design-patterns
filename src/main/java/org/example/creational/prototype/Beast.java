package org.example.creational.prototype;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class Beast extends Prototype<Beast> {

    public Beast(Beast beast) {
    }
}
