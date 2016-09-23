package com.dy.drinkpointofsale.product;

public class Coffee implements Product {

    @Override
    public String getName() {
        return "Coffee";
    }
    
    @Override
    public int getPrice() {
        return 25;
    }
    
    
}
