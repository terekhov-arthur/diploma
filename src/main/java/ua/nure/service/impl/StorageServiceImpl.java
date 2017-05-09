package ua.nure.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    public static final String SOURCE_DIR = "data/";
    public static final String TEMP_DIR = SOURCE_DIR + "temp/";

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String basePath = TEMP_DIR + user.getUsername() +"/";
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

    @Override
    public String readFile(InputStream inputStream) {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
