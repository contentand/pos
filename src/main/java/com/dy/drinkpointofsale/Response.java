package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.coin.Coin;
import com.dy.drinkpointofsale.product.Product;
import java.util.List;

public interface Response {
    List<Product> getProducts();
    List<Coin> getChange();
    Receipt getReceipt();
    boolean isSuccessful();
    String getErrorMessage();
}
