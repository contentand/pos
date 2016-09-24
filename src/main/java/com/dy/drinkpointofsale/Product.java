package com.dy.drinkpointofsale;

import java.util.EnumSet;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

public enum Product {

    TEA("Tea", 15), COFFEE("Coffee", 25), JUICE("Juice", 35);
    private static EnumSet<Product> set = EnumSet.allOf(Product.class);

    public static Product getProduct(String productName) {
        return set.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .get();
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
