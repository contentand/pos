package com.dy.drinkpointofsale;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pos {

    private final List<Integer> SUPPORTED_COINS;
    private final Cash cash;
    private final Sale sale;
    private int deposit;

    public Pos(Cash cash, Sale sale) {
        this.cash = cash;
        this.sale = sale;
        this.SUPPORTED_COINS = cash.getSupportedCoinValues();
    }

    public String buy() { // throws ISE if funds are insufficient for purchase
        int total = sale.getTotalPrice();
        if (isSufficientFunds()) {
            deposit -= total;
            String receipt = sale.getReceipt();
            sale.clear();
            return receipt;
        } else {
            throw new IllegalStateException();
        }

    }

    public boolean isSufficientFunds() {
        return sale.getTotalPrice() <= deposit;
    }

    public void cancel() {
        sale.clear();
    }

    public void putCoin(int value) { // throws NSEE if value does not match any coin
        cash.add(value);
        deposit += value;
    }

    public boolean isChangeAvailable() {
        return deposit == 0 || cash.isAvailable(deposit);
    }

    public List<Integer> getChange() { // throws ISE if there is not enough coins to give the change
        if (deposit == 0) return Collections.emptyList();
        try {
            List<Integer> change = cash.remove(deposit);
            deposit = 0;
            return change;
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    public List<Integer> getSupportedCoins() {
        return SUPPORTED_COINS;
    }

    public Map<String, Integer> getPriceList() {
        return sale.getPriceList();
    }

    public void add(String productName, int quantity) { // throws NSEE if product name does not match any product
        sale.add(productName, quantity);
    }

    int getBalance() {
        return deposit;
    }

    int getTotalPrice() {
        return sale.getTotalPrice();
    }
}
