package com.example.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.interfaces.JsonFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Orders implements JsonFileReader<Order> {
    private List<Order> orders;

    public Orders(String filePath) {
        try {
            this.orders = this.jsonRead(filePath);

        } catch (IOException e) {
            System.out.println("Cannot read json file");
            this.orders = new ArrayList<>();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> jsonRead(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode orderNodes = objectMapper.readTree(new File(path));
        List<Order> orders = new ArrayList<>();

        for (JsonNode order : orderNodes) {
            String id = order.get("id").asText();
            String value = order.get("value").asText();
            JsonNode promotionsNode = order.get("promotions");

            List<String> promotions = new ArrayList<>();
            if (promotionsNode != null && promotionsNode.isArray()) {
                for (JsonNode promotion : promotionsNode) {
                    promotions.add(promotion.asText());
                }
            }

            orders.add(new Order(id, Float.parseFloat(value), promotions));
        }
        return orders;
    }
}
