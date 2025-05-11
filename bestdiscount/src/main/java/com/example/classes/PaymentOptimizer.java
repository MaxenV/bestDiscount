package com.example.classes;

import java.util.List;

public class PaymentOptimizer {

    private float orderValue;
    private List<String> promotions;
    private List<PaymentMethod> paymentMethods;

    private PaymentMethod bestPaymentMethod = null;
    private float maxDiscount = 0;

    public PaymentMethod getBestPaymentMethod() {
        return bestPaymentMethod;
    }

    public float getMaxDiscount() {
        return maxDiscount;
    }

    public PaymentOptimizer(ClientWallet wallet, Order order) {
        this.orderValue = order.getValue();
        this.promotions = order.getPromotions();
        this.paymentMethods = wallet.getPaymentMethods();

        bankDiscount();
        loyaltyDiscount();
    }

    private void bankDiscount() {

        for (PaymentMethod paymentMethod : this.paymentMethods) {
            if (this.promotions.contains(paymentMethod.getId()) && paymentMethod.getLimit() >= this.orderValue) {
                float discount = this.orderValue * (paymentMethod.getDiscount() / 100);
                if (discount > paymentMethod.getLimit()) {
                    discount = paymentMethod.getLimit();
                }
                if (discount > this.maxDiscount) {
                    this.updateBestPaymentMethod(paymentMethod, discount);
                }
            }
        }
    }

    private void loyaltyDiscount() {
        PaymentMethod loyalPaymentMethod = this.paymentMethods.stream()
                .filter(pm -> pm.getId().equals("PUNKTY"))
                .findFirst()
                .orElse(null);

        if (loyalPaymentMethod != null) {
            float loyaltyDiscount = this.orderValue * (loyalPaymentMethod.getDiscount() / 100);
            if (loyalPaymentMethod.getDiscount() > 10) {
                loyaltyDiscount += this.calculateAdditionalLoyaltyBonus(loyaltyDiscount);
            }
            if (loyalPaymentMethod.getLimit() < loyaltyDiscount)
                loyaltyDiscount = loyalPaymentMethod.getLimit();

            if (loyaltyDiscount > maxDiscount) {
                this.updateBestPaymentMethod(loyalPaymentMethod, loyaltyDiscount);
            }
        }
    }

    private void updateBestPaymentMethod(PaymentMethod paymentMethod, float discount) {
        this.maxDiscount = discount;
        this.bestPaymentMethod = paymentMethod;
    }

    private float calculateAdditionalLoyaltyBonus(float loyaltyDiscount) {
        return (orderValue - loyaltyDiscount) * 0.1f;
    }
}
