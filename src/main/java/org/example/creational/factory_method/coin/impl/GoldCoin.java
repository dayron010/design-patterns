package org.example.creational.factory_method.coin.impl;

import lombok.ToString;
import org.example.creational.factory_method.coin.Coin;

@ToString
public class GoldCoin implements Coin {

    private static final String name = "GoldCoin";

    @Override
    public String getName() {
        return name;
    }
}
