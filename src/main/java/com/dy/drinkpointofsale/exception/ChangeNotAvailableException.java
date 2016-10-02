package com.dy.drinkpointofsale.exception;

public class ChangeNotAvailableException extends IllegalArgumentException {
    private final int amount;

    public ChangeNotAvailableException(int amount) {
        super("Cannot return " + amount + " as there are not enough coins.");
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
