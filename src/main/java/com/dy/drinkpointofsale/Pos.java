package com.dy.drinkpointofsale;

import java.util.List;
import java.util.Map;

public interface Pos {
    String buy();
    void cancel();
    boolean isSufficientFunds();
    boolean isChangeAvailable();
    void add(String productName, int quantity);
    void putCoin(int value);
    List<Integer> getChange();
    List<Integer> getSupportedCoins();
    Map<String, Integer> getPriceList();
    int getBalance();
    int getTotalPrice();
}
