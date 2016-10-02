package com.dy.drinkpointofsale.exception;

public class NoSuchProductException extends IllegalArgumentException {

    private final String productName;

    public NoSuchProductException(String productName) {
        super("No product under name " + productName + " is found.");
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
}
