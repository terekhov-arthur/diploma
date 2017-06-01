package ua.nure.service.impl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.nure.model.Task;
import ua.nure.model.TaskStatistic;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.repository.TaskRepository;
import ua.nure.repository.TaskStatisticRepository;
import ua.nure.repository.UserRepository;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.util.StringUtils;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Optional;

import static ua.nure.util.StringUtils.removePackage;

@Service
public class TaskServiceImpl implements TaskService {

    private final StorageService storageService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStatisticRepository taskStatisticRepository;

    @Autowired
    public TaskServiceImpl(StorageService storageService, TaskRepository taskRepository, UserRepository userRepository,
            TaskStatisticRepository taskStatisticRepository) {
        this.storageService = storageService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskStatisticRepository = taskStatisticRepository;
    }

    @Override
    public void save(Task task, InputStream source, InputStream test) {
        User user = UserDetailsImpl.getCurrentUser();
        task.setOwner(userRepository.findByUsername(user.getUsername()));

        String sourceData = removePackage(storageService.read(source));
        String testData = removePackage(storageService.read(test));

        task.setSource(sourceData);
        task.setTest(testData);

        taskRepository.save(task);
    }

    public Task findOne(Long aLong) {
        return taskRepository.findOne(aLong);
    }

    public boolean exists(Long aLong) {
        return taskRepository.exists(aLong);
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public TaskStatistic findOrCreateStatistic(User user, Task task) {
        return Optional.ofNullable(taskStatisticRepository.findByUserAndTask(user, task))
                       .orElse(TaskStatistic.create(user, task));
    }

    @Override
    public Result verify(String solution, Task task) throws Exception {
        String solutionClass = StringUtils.getClassName(solution);
        String testClass = StringUtils.getClassName(task.getTest());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { getClasspath() });
        Class.forName(solutionClass, false, classLoader);

        Result result = JUnitCore.runClasses(Class.forName(testClass, false, classLoader));
        storageService.removeTmpFolder();

        return result;
    }

    @Override
    public void saveStatistic(TaskStatistic statistic) {
        taskStatisticRepository.save(statistic);
    }

    private URL getClasspath() throws MalformedURLException {
        return new File(StorageServiceImpl.TEMP_DIR + UserDetailsImpl.getCurrentUser().getUsername())
                .toURI().toURL();
    }

    public Iterable<Task> findAll(Iterable<Long> iterable) {
        return taskRepository.findAll(iterable);
    }

    public long count() {
        return taskRepository.count();
    }
}
