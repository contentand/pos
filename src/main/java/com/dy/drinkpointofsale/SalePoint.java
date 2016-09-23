package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.coin.Coin;
import com.dy.drinkpointofsale.product.Product;
import java.util.List;
import java.util.Map;

public interface SalePoint {
    List<Product> buy();
    void cancel();
    
    void putCoin(Coin coin);
    List<Coin> getChange();
    
    Map<String, Integer> getPriceList();
    
    void add(String productKey, int quantity);
    
    int getBalance();
    int getTotalPrice();
}
