package ua.nure.service;

import java.io.InputStream;

public interface StorageService {
    void save(String fileName, InputStream inputStream);
    String load(String fileName);
}
