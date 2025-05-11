package com.example;

import java.util.List;

import com.example.classes.ClientWallet;
import com.example.classes.Order;
import com.example.classes.Orders;
import com.example.classes.PaymentMethod;

public class Main {
    public static void main(String[] args) {
        Orders clientOrders = new Orders("bestdiscount/src/main/resources/data/orders.json");
        ClientWallet ClientWallet = new ClientWallet("bestdiscount/src/main/resources/data/paymentmethods.json");
    }

    public static void optimizePayment(ClientWallet wallet, Order order) {
        float orderValue = order.getValue();
        List<String> promotions = order.getPromotions();
        List<PaymentMethod> paymentMethods = wallet.getPaymentMethods();

        PaymentMethod bestPaymentMethod = null;
        float maxDiscount = 0;

        for (PaymentMethod paymentMethod : paymentMethods) {
            if (promotions.contains(paymentMethod.getId()) && paymentMethod.getLimit() >= orderValue) {
                float discount = orderValue * (paymentMethod.getDiscount() / 100);
                if (discount > paymentMethod.getLimit()) {
                    discount = paymentMethod.getLimit();
                }
                if (discount > maxDiscount) {
                    maxDiscount = discount;
                    bestPaymentMethod = paymentMethod;
                }
            }
        }

    }
}