package ua.nure.service;

import java.io.InputStream;

public interface StorageService {
    String save(String fileName, InputStream inputStream);
    String saveTemp(String fileName, String data);
    String load(String fileName);
    String readFile(InputStream inputStream);
}
