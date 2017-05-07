package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.model.Task;
import ua.nure.repository.TaskRepository;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.util.Context;
import ua.nure.util.StringUtil;

import java.io.InputStream;

@Service
public class TaskServiceImpl implements TaskService {

    private final StorageService storageService;
    private final Context context;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(StorageService storageService, Context context, TaskRepository taskRepository) {
        this.storageService = storageService;
        this.context = context;
        this.taskRepository = taskRepository;
    }

    @Override
    public void save(Task task, InputStream source, InputStream test) {
        task.setOwner(context.getUser());

        String sourceData = storageService.save(StringUtil.getSourceFileName(task), source);
        String testData = storageService.save(StringUtil.getTestFileName(task), test);

        task.setSourceClassName(StringUtil.getClassName(sourceData));
        task.setTestClassName(StringUtil.getClassName(testData));

        taskRepository.save(task);
    }

    @Override
    public String loadTask(Task task) {
        return storageService.load(StringUtil.getSourceFileName(task));
    }

    @Override
    public String loadTest(Long id) {
        return storageService.load(StringUtil.getTestFileName(findOne(id)));
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
