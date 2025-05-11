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

        for (Order order : clientOrders.getOrders()) {
            optimizePayment(ClientWallet, order);
        }
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

        // Loyalty discount
        PaymentMethod loyalPaymentMethod = paymentMethods.stream()
                .filter(pm -> pm.getId().equals("PUNKTY"))
                .findFirst()
                .orElse(null);

        if (loyalPaymentMethod != null) {
            float loyaltyDiscount = orderValue * (loyalPaymentMethod.getDiscount() / 100);
            if (loyalPaymentMethod.getDiscount() > 10) {
                loyaltyDiscount += (orderValue - loyaltyDiscount) * 0.1; // add 10 percentage
            }
            if (loyalPaymentMethod.getLimit() < loyaltyDiscount)
                loyaltyDiscount = loyalPaymentMethod.getLimit();

            if (loyaltyDiscount > maxDiscount) {
                maxDiscount = loyaltyDiscount;
                bestPaymentMethod = loyalPaymentMethod;
            }
        }

        if (bestPaymentMethod != null) {
            System.out.println("Best payment method: " + bestPaymentMethod.getId() + " with discount: " + maxDiscount);
        } else {
            System.out.println("No suitable payment method found.");
        }
    }
}