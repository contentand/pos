package com.dy.drinkpointofsale;

import com.dy.drinkpointofsale.exception.ChangeNotAvailableException;
import com.dy.drinkpointofsale.exception.InsufficientFundsException;
import com.dy.drinkpointofsale.exception.NoSuchProductException;
import com.dy.drinkpointofsale.exception.UnsupportedCoinException;

import java.util.*;

public class Console {

    private Pos pos;
    private Scanner scanner;
    private Map<String, Runnable> commands;

    public Console(Pos pos, Scanner scanner) {
        this.pos = pos;
        this.scanner = scanner;
        this.commands = initializeCommands();
    }

    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            out("Enter command.");
            String command = scanner.next();
            if (commands.containsKey(command)) {
                commands.get(command).run();
            } else {
                out("Wrong command. Only following commands are supported : " + commands.keySet());
            }
        }
    }

    // Private helper members are below.

    private Map<String,Runnable> initializeCommands() {
        Commands functions = new Commands();
        Map<String,Runnable> commands = new HashMap<>();

        commands.put("list", functions::list);
        commands.put("insert", functions::insert);
        commands.put("change", functions::getChange);
        commands.put("select", functions::select);
        commands.put("cancel", functions::cancel);
        commands.put("quit", functions::quit);
        commands.put("buy", functions::buy);

        return commands;
    }

    private class Commands {
        private void list() {
            out("Price list:");
            pos.getPriceList().forEach((name, price) ->
                    out("- " + name + " : " + price + " cents."));
        }

        private void insert() {
            List<Integer> supportedCoins = pos.getSupportedCoins();
            out("You can insert the following coins : " + supportedCoins);

            if (scanner.hasNextInt()) {
                try {
                    int value = scanner.nextInt();
                    pos.putCoin(value);
                    out("Inserted " + value + " cents. Your current balance is : " + pos.getBalance() + " cents.");
                } catch (UnsupportedCoinException e) {
                    out("Coins with value " + e.getValue() + " are not supported!");
                }

            } else {
                out("Please enter numeric values!");
            }
        }

        private void select() {
            out("Enter product name:");
            String productName = scanner.next();
            out("Enter quantity:");
            int quantity;
            if (scanner.hasNextInt() && (quantity = scanner.nextInt()) > 0) {
                try {
                    pos.add(productName, quantity);
                    out("Added to your bill.");
                } catch (NoSuchProductException e) {
                    out("Wrong product name: " + e.getProductName() + ". " +
                            "Enter 'list' command to see product list.");
                }
            } else {
                out("Invalid input. Quantity should be a positive non-zero number.");
            }
        }

        private void getChange() {
            try {
                out("The machine returned : " + pos.getChange());
            } catch (ChangeNotAvailableException e) {
                out("Sorry. We cannot give you change. Can you buy something else?");
                out("You have " + e.getAmount() + " cents left.");
            }
        }

        private void quit() {
            out("Good bye!");
            System.exit(0);
        }

        public void cancel() {
            pos.cancel();
            out("Your shopping cart has been emptied.");
        }

        public void buy() {
            try {
                String receipt = pos.buy();
                out("Purchase successful!");
                out(receipt);
            } catch (InsufficientFundsException e) {
                out("Insufficient funds!");
                out("Your deposit: " + e.getDeposit() + " cents. Your bill: " + e.getBill() + " cents.");
                out("You need " + (e.getBill() - e.getDeposit()) + " cents more.");
            }
        }
    }

    private void out(Object objectToPrint) {
        System.out.println(objectToPrint);
    }


}
