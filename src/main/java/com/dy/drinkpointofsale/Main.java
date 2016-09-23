package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.coin.Coin;
import com.dy.drinkpointofsale.coin.FiftyCent;
import com.dy.drinkpointofsale.coin.FiveCent;
import com.dy.drinkpointofsale.coin.OneCent;
import com.dy.drinkpointofsale.coin.TenCent;
import com.dy.drinkpointofsale.coin.TwentyCent;
import com.dy.drinkpointofsale.coin.TwentyFiveCent;
import com.dy.drinkpointofsale.product.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        
//        SalePoint sp = getSalePoint();
        SalePoint sp = null;
        List<Coin> coinsInMyPocket = new ArrayList<>();
        List<Product> myPurchasedProducts = new ArrayList<>();
        
        System.out.println("Welcome.");
        while(true) {
            System.out.println("Actions:");
            String request = scanner.next();
            switch(request) {
                case "list":
                    sp.getPriceList().forEach((product, price) -> {
                        System.out.println(" - " + product + " : " + price);
                    });
                    break;
                case "state":
                    System.out.println("--- Your current state ---");
                    System.out.println("Coins");
                    break;
                case "insert":
                    while(true) {
                        System.out.println("Enter coint to insert: 1, 5, 10, 25, 50");
                        if (scanner.hasNextInt()) {
                            int coinValue = scanner.nextInt();
                            Coin coin = getCoin(coinValue);
                            if (coin == null) continue;
                            sp.putCoin(coin);
                            System.out.println("Balance : " + sp.getBalance());
                        } else {
                            break;
                        }
                    }
                    break;
                case "balance":
                    System.out.println("You have inserted " + sp.getBalance() + " cents.");
                    break;
                case "quit":
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                case "buy":
                    break;
                default:
                    System.out.println("I'm sorry. I don't understand you. :(");
            }
        }
    }
    
    private static Coin getCoin(int value) {
        switch(value) {
            case 1:
                return new OneCent();
            case 5:
                return new FiveCent();
            case 10:
                return new TenCent();
            case 20:
                return new TwentyCent();
            case 25:
                return new TwentyFiveCent();
            case 50:
                return new FiftyCent();
            default:
                return null;
        }
    }
    
//    private static SalePoint getSalePoint() {
//        return new SalePoint() {
//            
//            List<Coin> inserted = new ArrayList<>();
//            Map<String, Integer> priceList = new HashMap<>();
//            
//            {
//                priceList.put("Tea", 15);
//                priceList.put("Coffee", 25);
//                priceList.put("Juice", 35);
//            }
//            
//            @Override
//            public Map<String, Integer> getPriceList() {
//                return Collections.unmodifiableMap(priceList);
//            }
//
//            @Override
//            public void putCoin(Coin coin) {
//                inserted.add(coin);
//            }
//
//            @Override
//            public List<Coin> getChange() {
//                return inserted;
//            }
//
//            @Override
//            public String getBalance() {
//                return String.valueOf(inserted.stream()
//                        .mapToInt(coin -> coin.getValue())
//                        .reduce(0, (v1, v2) -> v1 + v2));
//            }
//
//            @Override
//            public List<Product> buy(String productKey, int quantity) {
//                return null;
//            }
//
//        };
//    }
}
