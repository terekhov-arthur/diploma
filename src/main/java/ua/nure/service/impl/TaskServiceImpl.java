package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.model.Task;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.repository.TaskRepository;
import ua.nure.repository.UserRepository;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;

import java.io.InputStream;

import static ua.nure.util.StringUtils.removePackage;

@Service
public class TaskServiceImpl implements TaskService {

    private final StorageService storageService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(StorageService storageService, TaskRepository taskRepository, UserRepository userRepository) {
        this.storageService = storageService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Task task, InputStream source, InputStream test) {
        User user = UserDetailsImpl.getCurrentUser();
        task.setOwner(userRepository.findByUsername(user.getUsername()));

        String sourceData = removePackage(storageService.readFile(source));
        String testData = removePackage(storageService.readFile(test));

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

    public Iterable<Task> findAll(Iterable<Long> iterable) {
        return taskRepository.findAll(iterable);
    }

    public long count() {
        return taskRepository.count();
    }
}
