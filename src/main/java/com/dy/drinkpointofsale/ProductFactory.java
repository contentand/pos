package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.product.Coffee;
import com.dy.drinkpointofsale.product.Juice;
import com.dy.drinkpointofsale.product.Product;
import com.dy.drinkpointofsale.product.Tea;
import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
    
    private static Map<String, Product> products;
    private static Map<String, Integer> priceList;
    
    static { // initializing products
        products = new HashMap<>();
        products.put("Tea", new Tea());
        products.put("Coffee", new Coffee());
        products.put("Juice", new Juice());
    }
    
    static { // initializing price list
        priceList = new HashMap<>();
        products.forEach((key, product) -> {
            priceList.put(key, product.getPrice());
        });
    }
    
    public static Product getProduct(String key) {
        return products.getOrDefault(key, new Product() {
            @Override
            public String getName() {
                return key;
            }

            @Override
            public int getPrice() {
                return 0;
            }
            
        });
    }
    
    public static Map<String, Integer> getPriceList() {
        return priceList;
    }
}
