package ua.nure.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.service.StorageService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    public static final String TEMP_DIR = "temp/";

    @Override
    public String save(String fileName, String data) {
        User user = UserDetailsImpl.getCurrentUser();
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
    public String read(InputStream inputStream) {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void removeTmpFolder() {
        User user = UserDetailsImpl.getCurrentUser();
        String basePath = TEMP_DIR + user.getUsername();
        File dir = new File(basePath);
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }
}
