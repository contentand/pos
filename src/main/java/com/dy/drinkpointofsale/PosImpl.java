package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.exception.InsufficientFundsException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PosImpl implements Pos {
    private final List<Integer> SUPPORTED_COINS;
    private final Cash cash;
    private final Sale sale;
    private int deposit;

    public PosImpl(Cash cash, Sale sale) {
        this.cash = cash;
        this.sale = sale;
        this.SUPPORTED_COINS = cash.getSupportedCoinValues();
    }

    @Override // throws InsufficientFundsException
    public String buy() {
        int total = sale.getTotalPrice();
        if (isSufficientFunds()) {
            deposit -= total;
            String receipt = sale.getReceipt();
            sale.clear();
            return receipt;
        } else {
            throw new InsufficientFundsException(deposit, total);
        }
    }

    @Override
    public boolean isSufficientFunds() {
        return sale.getTotalPrice() <= deposit;
    }

    @Override
    public void cancel() {
        sale.clear();
    }

    @Override // throws UnsupportedCoinException
    public void putCoin(int value) {
        cash.add(value);
        deposit += value;
    }

    @Override
    public boolean isChangeAvailable() {
        return deposit == 0 || cash.isAvailable(deposit);
    }

    @Override // throws ChangeNotAvailableException
    public List<Integer> getChange() {
        if (deposit == 0) return Collections.emptyList();
        List<Integer> change = cash.remove(deposit);
        deposit = 0;
        return change;
    }

    @Override
    public List<Integer> getSupportedCoins() {
        return SUPPORTED_COINS;
    }

    @Override
    public Map<String, Integer> getPriceList() {
        return sale.getPriceList();
    }

    @Override // throws NoSuchProductException
    public void add(String productName, int quantity) {
        sale.add(productName, quantity);
    }

    @Override
    public int getBalance() {
        return deposit;
    }

    @Override
    public int getTotalPrice() {
        return sale.getTotalPrice();
    }
}
