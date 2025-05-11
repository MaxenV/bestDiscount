package com.example.classes;

import java.util.List;

public class Order {
    private String id;
    private float value;
    private List<String> promotions;

    public Order(String id, float value, List<String> promotions) {
        this.id = id;
        this.value = value;
        this.promotions = promotions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public List<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<String> promotions) {
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", value=" + value + ", promotions=" + promotions + "]";
    }

}
