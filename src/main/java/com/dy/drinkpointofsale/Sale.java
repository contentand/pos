package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sale {
    
    private List<LineItem> lineItems;
    private Map<Product, Integer> store;
    
    public Sale() {
        this.lineItems = new ArrayList<>();
    }
    
    public void add(String productKey, int quantity) {
        LineItem lineItem = new LineItem(productKey, quantity);
        this.lineItems.add(lineItem);
    }
    
    public int getPrice() {
        return lineItems.stream()
                .mapToInt(lineItem -> lineItem.getPrice())
                .reduce(0, (v1, v2) -> v1 + v2);
    }
    
    public boolean canDemandBeSatisfiedByStore() {
        return true;
    }
    
}
