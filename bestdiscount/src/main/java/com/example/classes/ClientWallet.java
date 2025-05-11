package com.example.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.interfaces.JsonFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientWallet implements JsonFileReader<PaymentMethod> {
    private List<PaymentMethod> paymentMethods;

    public ClientWallet(String filePath) {
        try {
            this.paymentMethods = this.jsonRead(filePath);
        } catch (Exception e) {
            System.out.println("Cannot read json file");
            this.paymentMethods = new ArrayList<>();
        }

    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    @Override
    public List<PaymentMethod> jsonRead(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode orderNodes = objectMapper.readTree(new File(path));
        List<PaymentMethod> paymentMethod = new ArrayList<>();

        for (JsonNode order : orderNodes) {
            String id = order.get("id").asText();
            Float value = Float.parseFloat(order.get("discount").asText());
            Float limit = Float.parseFloat(order.get("limit").asText());

            paymentMethod.add(new PaymentMethod(id, value, limit));
        }
        return paymentMethod;
    }
}
