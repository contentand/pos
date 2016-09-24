package com.dy.drinkpointofsale;

public final class LineItem {
    private final Product product;
    private final int quantity;

    public LineItem(Product product, int quantity) {
        if (product == null) throw new NullPointerException();
        this.product = product;
        this.quantity = quantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return product.getPrice();
    }

    public int getSubTotal() {
        return product.getPrice() * quantity;
    }
}
