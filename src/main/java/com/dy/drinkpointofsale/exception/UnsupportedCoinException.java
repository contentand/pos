package com.dy.drinkpointofsale.exception;

public class UnsupportedCoinException extends IllegalArgumentException {

    private final int value;

    public UnsupportedCoinException(int value) {
        super("There is no coin with value " + value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
