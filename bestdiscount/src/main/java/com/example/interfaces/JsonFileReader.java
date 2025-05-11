package com.example.interfaces;

import java.io.IOException;
import java.util.List;

import com.example.classes.Order;

public interface JsonFileReader {
    public List<Order> jsonRead(String path) throws IOException;
}