package ua.nure.service.impl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.model.Label;
import ua.nure.model.Level;
import ua.nure.model.Task;
import ua.nure.model.TaskStatistic;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.repository.LabelRepository;
import ua.nure.repository.LevelRepository;
import ua.nure.repository.TaskRepository;
import ua.nure.repository.TaskStatisticRepository;
import ua.nure.repository.UserRepository;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.util.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.nure.util.StringUtils.removePackage;

@Service
public class TaskServiceImpl implements TaskService {

    private final StorageService storageService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskStatisticRepository taskStatisticRepository;
    private final LabelRepository labelRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public TaskServiceImpl(StorageService storageService, TaskRepository taskRepository, UserRepository userRepository,
            TaskStatisticRepository taskStatisticRepository, LabelRepository labelRepository, LevelRepository levelRepository) {
        this.storageService = storageService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskStatisticRepository = taskStatisticRepository;
        this.labelRepository = labelRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public void save(Task task, InputStream source, InputStream test, Set<String> labels) {
        task.setName(task.getName().trim());
        task.setDescription(task.getDescription().trim());

        User user = UserDetailsImpl.getCurrentUser();
        task.setOwner(userRepository.findByUsername(user.getUsername()));

        String sourceData = removePackage(storageService.read(source));
        String testData = removePackage(storageService.read(test));

        task.setSource(sourceData);
        task.setTest(testData);

        Set<Label> alreadyExistingLabels = labelRepository.findAllByValueIgnoreCaseIn(labels);
        if(alreadyExistingLabels.size() != labels.size()) {
            labels.removeAll(alreadyExistingLabels.stream().map(Label::getValue).collect(Collectors.toSet()));
            labelRepository.save(mapToLabelSet(labels)).forEach(alreadyExistingLabels::add);
        }

        task.setLabels(new ArrayList<>(alreadyExistingLabels));
        if (task.getLevel() == null) {
            task.setLevel(levelRepository.save(new Level()));
        }
        taskRepository.save(task);
    }

    private List<Label> mapToLabelSet(Set<String> labels) {
        return labels.stream().map(Label::new).distinct().collect(Collectors.toList());
    }

    public Task findOne(Long aLong) {
        return taskRepository.findOne(aLong);
    }

    public boolean exists(Long aLong) {
        return taskRepository.exists(aLong);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Level> findLevels() {
        return levelRepository.findAll();
    }

    @Override
    public TaskStatistic findOrCreateStatistic(User user, Task task) {
        return Optional.ofNullable(taskStatisticRepository.findByUserAndTask(user, task))
                       .orElse(TaskStatistic.create(user, task));
    }

    @Override
    public List<TaskStatistic> findByUser(User user) {
        return taskStatisticRepository.findByUser(user);
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

    @Override
    public int getLevelStatistic(long levelId) {
        Level level = levelRepository.findOne(levelId);
        int result = 0;
        if (level != null && !level.getTasks().isEmpty()) {
            User user = UserDetailsImpl.getCurrentUser();
            long completedCount = findByUser(user).stream().filter(st -> st.getTask().getLevel().getId() == levelId).filter(TaskStatistic::isCompleted).count();
            result = (int) (completedCount / (double) level.getTasks().size() * 100);
        }
        return result;
    }

    @Override
    public boolean tryLevelUp() {
        User user = UserDetailsImpl.getCurrentUser();
        if (getLevelStatistic(user.getLevel().getId()) == 100) {
            if (levelRepository.exists(user.getLevel().getId() + 1)) {
                user.setLevel(levelRepository.findOne(user.getLevel().getId() + 1));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
