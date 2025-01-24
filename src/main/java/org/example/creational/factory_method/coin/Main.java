package org.example.creational.factory_method.coin;

import org.example.creational.factory_method.coin.enums.CoinType;
import org.example.creational.factory_method.coin.factory.CoinFactory;

public class Main {

    public static void main(String[] args) {

        Coin goldCoin = CoinFactory.createCoin(CoinType.GOLD);
        Coin silverCoin = CoinFactory.createCoin(CoinType.SILVER);
        Coin ironCoin = CoinFactory.createCoin(CoinType.IRON);

        System.out.println("goldCoin = " + goldCoin);
        System.out.println("silverCoin = " + silverCoin);
        System.out.println("ironCoin = " + ironCoin);

    }

}
