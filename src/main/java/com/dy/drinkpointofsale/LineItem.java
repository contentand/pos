package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.product.Product;

class LineItem {
    Product product;
    int quantity;
    
    public LineItem(String productKey, int quantity) {
        this.product = ProductFactory.getProduct(productKey);
        this.quantity = quantity;
    }
    
    public int getPrice() {
        return product.getPrice() * quantity;
    }
}
