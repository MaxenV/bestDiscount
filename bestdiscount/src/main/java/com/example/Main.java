package com.example;

import com.example.classes.ClientWallet;
import com.example.classes.Orders;

public class Main {
    public static void main(String[] args) {
        Orders clientOrders = new Orders("bestdiscount/src/main/resources/data/orders.json");
        ClientWallet ClientWallet = new ClientWallet("bestdiscount/src/main/resources/data/paymentmethods.json");
    }
}