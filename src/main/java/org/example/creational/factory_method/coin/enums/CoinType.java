package org.example.creational.factory_method.coin.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.creational.factory_method.coin.Coin;
import org.example.creational.factory_method.coin.impl.GoldCoin;
import org.example.creational.factory_method.coin.impl.IronCoin;
import org.example.creational.factory_method.coin.impl.SilverCoin;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public enum CoinType {

    GOLD(GoldCoin::new),
    SILVER(SilverCoin::new),
    IRON(IronCoin::new);

    private final Supplier<Coin> coinSupplier;

}
