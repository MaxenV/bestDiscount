package com.example.interfaces;

import java.io.IOException;
import java.util.List;

public interface JsonFileReader<T> {
    public List<T> jsonRead(String path) throws IOException;
}