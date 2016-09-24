package com.dy.drinkpointofsale;

import java.util.EnumSet;

public enum Coin {
    ONE(1), FIVE(5), TEN(10), TWENTY(20), TWENTY_FIVE(25), FIFTY(50);
    static EnumSet<Coin> coins = EnumSet.allOf(Coin.class);

    private int value;
    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Coin getCoin(int i) {
        return coins.stream()
                .filter(coin -> coin.value == i)
                .findFirst()
                .get();
    }
}
