package ua.nure.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.service.StorageService;

import java.io.BufferedReader;
import java.io.File;
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
    private static final String TEMP_DIR = SOURCE_DIR + "temp/";

    //todo: add uniqueness check
    @Override
    public String save(String fileName, InputStream inputStream) {
        try (FileWriter writer = new FileWriter(SOURCE_DIR + fileName)) {
            String data = readFile(inputStream).replaceAll("package [\\w\\d]+;", "");
            writer.write(data);
            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String saveTemp(String fileName, String data) {
        String basePath = TEMP_DIR + "admin/";
        new File(basePath).mkdirs();

        String path = basePath + fileName + ".java";
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(data);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Override
    public String load(String fileName) {
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
