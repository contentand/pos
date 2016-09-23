package com.dy.drinkpointofsale.product;

public class Tea implements Product {

    @Override
    public String getName() {
        return "Tea";
    }
    
    @Override
    public int getPrice() {
        return 15;
    }
    
    
}
