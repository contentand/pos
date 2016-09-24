package com.dy.drinkpointofsale;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class Sale {
    private List<LineItem> lineItems;

    public Sale() {
        this.lineItems = new ArrayList<>();
    }

    public void add(String productName, int quantity) { // throws NSEE
        LineItem lineItem = new LineItem(Product.getProduct(productName), quantity);
        lineItems.add(lineItem);
    }

    public int getTotalPrice() {
        return lineItems.stream()
                .mapToInt(LineItem::getSubTotal)
                .sum();
    }

    public Map<String, Integer> getPriceList() {
        return Product.getPriceList();
    }

    public String getReceipt() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n--------------------------");
        builder.append("\n--       RECEIPT        --");
        builder.append("\n--------------------------");
        Formatter f = new Formatter();
        for (LineItem lineItem : lineItems) {
            f.format("\n-- %-20s --",
                    lineItem.getProductName());
            f.format("\n-- %4d * %-4d = %6d --",
                    lineItem.getQuantity(),
                    lineItem.getUnitPrice(),
                    lineItem.getSubTotal());
        }
        f.format("\n-- %20s --", " ");
        f.format("\n-- Total = %12d --", getTotalPrice());
        builder.append(f.toString());
        f.close();
        builder.append("\n--------------------------\n");
        return builder.toString();
    }

    public void clear() {
        lineItems.clear();
    }

}
