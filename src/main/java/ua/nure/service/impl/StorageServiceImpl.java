package ua.nure.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.service.StorageService;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String SOURCE_DIR = "data/";

    //todo: add uniqueness check
    @Override
    public void save(String fileName, InputStream inputStream) {
        try (FileWriter writer = new FileWriter(SOURCE_DIR +fileName)) {
            writer.write(readFile(inputStream));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(SOURCE_DIR + fileName)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String readFile(InputStream inputStream) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
}
