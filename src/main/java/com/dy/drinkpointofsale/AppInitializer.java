package com.dy.drinkpointofsale;

import java.util.*;

public class AppInitializer {

    public static App initApp() {
        Sale sale = new Sale();
        NavigableMap<Coin, Integer> coins = new TreeMap<>();
        coins.put(Coin.ONE, 5);
        coins.put(Coin.FIVE, 25);
        coins.put(Coin.TEN, 1);
        coins.put(Coin.TWENTY, 45);
        coins.put(Coin.TWENTY_FIVE, 4);
        coins.put(Coin.FIFTY, 30);
        Cash cash = new Cash(coins);
        Pos pos = new PosImpl(cash, sale);
        Console console = new Console(pos, new Scanner(System.in));
        return new App(console);
    }
}
