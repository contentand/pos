package com.dy.drinkpointofsale.product;

public class Juice implements Product {

    @Override
    public String getName() {
        return "Juice";
    }
    
    @Override
    public int getPrice() {
        return 35;
    }
    
    
}
