package org.example.creational.factory_method.coin.factory;

import org.example.creational.factory_method.coin.Coin;
import org.example.creational.factory_method.coin.enums.CoinType;

public abstract class CoinFactory {

    public static Coin createCoin(CoinType coinType) {
        return coinType.getCoinSupplier().get();
    }

}
