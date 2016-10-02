package com.dy.drinkpointofsale.exception;

public class InsufficientFundsException extends IllegalArgumentException {

    private final int deposit;
    private final int bill;

    public InsufficientFundsException(int deposit, int bill) {
        super("Insufficient Funds. Has " + deposit + " but " + bill + " is required.");
        this.deposit = deposit;
        this.bill = bill;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getBill() {
        return bill;
    }
}
