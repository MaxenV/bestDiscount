package com.example.classes;

public class PaymentMethod {
    private String id;
    private float discount;
    private float limit;

    public PaymentMethod(String id, float discount, float limit) {
        this.id = id;
        this.discount = discount;
        this.limit = limit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PaymentMethod [id=" + id + ", discount=" + discount + ", limit=" + limit + "]";
    }

}
