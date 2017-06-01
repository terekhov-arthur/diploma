package ua.nure.service;

import org.junit.runner.Result;
import ua.nure.model.Task;
import ua.nure.model.TaskStatistic;
import ua.nure.model.User;
import ua.nure.repository.TaskStatisticRepository;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

public interface TaskService {
    void save(Task task, InputStream source, InputStream test);
    Task findOne(Long id);
    boolean exists(Long id);
    Iterable<Task> findAll();
    TaskStatistic findOrCreateStatistic(User user, Task task);
    Result verify(String solution, Task task) throws Exception;
    void saveStatistic(TaskStatistic statistic);
}
