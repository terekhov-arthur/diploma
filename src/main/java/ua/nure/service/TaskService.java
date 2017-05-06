package ua.nure.service;

import ua.nure.model.Task;

import java.io.InputStream;

public interface TaskService {
    void save(Task task, InputStream source, InputStream test);
}
