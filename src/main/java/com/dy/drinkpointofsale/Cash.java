package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.exception.ChangeNotAvailableException;
import com.dy.drinkpointofsale.exception.UnsupportedCoinException;

import java.util.*;
import java.util.stream.Collectors;

public class Cash {
    private final NavigableMap<Coin, Integer> coins;

    public Cash(NavigableMap<Coin, Integer> coins) {
        // ensure coins order is descending
        if (coins.firstKey().getValue() < coins.lastKey().getValue()) {
            // reverting order
            coins = coins.descendingMap();
        }

        this.coins = coins;
    }

    public void add(int value) {
        try {
            Coin coin = Coin.getCoin(value);
            coins.merge(coin, 1, (previousCount, one) -> previousCount + one);
        } catch (NoSuchElementException e) {
            throw new UnsupportedCoinException(value);
        }
    }

    public List<Integer> getSupportedCoinValues() {
        return Coin.getSupportedCoinValues();
    }

    public List<Integer> remove(int amount) {
        List<Integer> valuesToReturn = new ArrayList<>();
        if (isAvailable(amount)) {
            for (Coin coin : coins.keySet()) { // SortedMap returns keySet with Iterator giving values in Ascending order
                int demand = amount / coin.getValue();
                int balance = coins.get(coin) - demand;
                int supply = (balance > 0) ? demand : demand + balance;
                amount -= supply * coin.getValue();
                valuesToReturn.addAll(Arrays.stream(new Integer[supply]).map(value -> coin.getValue()).collect(Collectors.toList()));
                coins.merge(coin, supply, (previous, subtract) -> previous - subtract);
                if (amount == 0) break;
            }
            return valuesToReturn;
        } else {
            throw new ChangeNotAvailableException(amount);
        }
    }

    public boolean isAvailable(int amount) {
        for (Coin coin : coins.keySet()) { // SortedMap returns keySet with Iterator giving values in Ascending order
            int demand = amount / coin.getValue();
            int balance = coins.get(coin) - demand;
            int supply = (balance > 0) ? demand : demand + balance;
            amount -= supply * coin.getValue();
            if (amount == 0) break;
        }
        return amount == 0;
    }
}
