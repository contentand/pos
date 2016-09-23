package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.coin.Coin;
import com.dy.drinkpointofsale.product.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalePointImpl implements SalePoint {
    
    private Sale currentSale;
    private Map<Coin, Integer> coinRepository; // coin type and quantity
    private Map<String, Integer> priceList;    // product key and item price
    private List<Coin> insertedCoins;
    
    public SalePointImpl() {
        this.coinRepository = new HashMap<>();
        this.priceList = new HashMap<>();
        this.insertedCoins = new ArrayList<>();
        createSale();
    }
    

    @Override
    public List<Product> buy() {
        createSale();
        return null;
    }

    @Override
    public void cancel() {
        createSale();
    }

    @Override
    public void putCoin(Coin coin) {
    }

    @Override
    public List<Coin> getChange() {
        return null;
    }

    @Override
    public Map<String, Integer> getPriceList() {
        return priceList;
    }

    @Override
    public void add(String productKey, int quantity) {
        currentSale.add(productKey, quantity);
    }
    
    // Private helper methods are below
    
    private void createSale() {
        this.currentSale = new Sale();
    }

    @Override
    public int getBalance() {
        return insertedCoins.stream()
                .mapToInt(coin -> coin.getValue())
                .reduce(0, (v1, v2) -> v1 + v2);
    }

    @Override
    public int getTotalPrice() {
        return this.currentSale.getPrice();
    }

}
