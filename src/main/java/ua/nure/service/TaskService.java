package ua.nure.service;

import ua.nure.model.Task;

import java.io.InputStream;

public interface TaskService
{
    void save(Task task, InputStream source, InputStream test);
    String loadTaskSource(Task task);

    Task findOne(Long id);
    boolean exists(Long id);
    Iterable<Task> findAll();
}
