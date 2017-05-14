package ua.nure.service;

import java.io.InputStream;

public interface StorageService {
    String save(String fileName, String data);
    String read(InputStream inputStream);
    void removeTmpFolder();
}
