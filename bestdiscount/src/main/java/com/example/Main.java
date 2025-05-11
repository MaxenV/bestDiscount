package com.example;

import com.example.classes.ClientWallet;
import com.example.classes.Order;
import com.example.classes.Orders;
import com.example.classes.PaymentOptimizer;

public class Main {
    public static void main(String[] args) {
        Orders clientOrders = new Orders("src/main/resources/data/orders.json");
        ClientWallet ClientWallet = new ClientWallet("src/main/resources/data/paymentmethods.json");

        for (Order order : clientOrders.getOrders()) {
            PaymentOptimizer optimal = new PaymentOptimizer(ClientWallet, order);
            System.out.print("Best payment method: " + optimal.getBestPaymentMethod().getId());
            System.out.println(" with discount: " + optimal.getMaxDiscount());
        }
    }

}