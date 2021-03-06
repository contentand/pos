package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.exception.NoSuchProductException;

import java.util.EnumSet;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.toMap;

public enum Product {

    TEA("Tea", 25), COFFEE("Coffee", 35), JUICE("Juice", 45);
    private static EnumSet<Product> set = EnumSet.allOf(Product.class);

    public static Product getProduct(String productName) {
        try {
            return set.stream()
                    .filter(product -> product.getName().equals(productName))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            throw new NoSuchProductException(productName);
        }
    }

    public static Map<String, Integer> getPriceList() {
        return set.stream()
                .collect(toMap(Product::getName, Product::getPrice));
    }

    String name;
    int price;

    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}
