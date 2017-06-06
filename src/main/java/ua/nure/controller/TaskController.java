package ua.nure.controller;

import org.hibernate.Hibernate;
import org.junit.runner.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.model.Level;
import ua.nure.model.Task;
import ua.nure.model.TaskStatistic;
import ua.nure.model.User;
import ua.nure.model.security.UserDetailsImpl;
import ua.nure.repository.LevelRepository;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.service.impl.CompileService;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final CompileService compileService;
    private final LevelRepository levelRepository;

    @Autowired
    public TaskController(TaskService taskService, CompileService compileService, LevelRepository levelRepository) {
        this.taskService = taskService;
        this.compileService = compileService;
        this.levelRepository = levelRepository;
    }

    @GetMapping
    public String get(Model model){
        List<Level> levels = levelRepository.findAll();
        model.addAttribute("levels",levels);
        return "task/add";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") final long id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);
        User user = UserDetailsImpl.getCurrentUser();
        model.addAttribute("statistic", taskService.findOrCreateStatistic(user, task));

        return "task/view";
    }

    @GetMapping("/list")
    public String tasks(Model model) {
        List<Task> tasks = taskService.findAll()
                                      .stream()
                                      .sorted(Comparator.comparingLong(t -> t.getLevel().getId()))
                                      .collect(Collectors.toList());
        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @PostMapping
    public String post(@RequestParam("sourceData[]") MultipartFile[] data,
                       @ModelAttribute Task task,
                       Model model) throws IOException {
        //todo: do some validation;

        MultipartFile source = data[0].getName().toLowerCase().endsWith("test") ? data[1] : data[0];
        MultipartFile test = data[0].getName().toLowerCase().endsWith("test") ? data[0] : data[1];

        taskService.save(task, source.getInputStream(), test.getInputStream());

        return "redirect:/task/list";
    }

    //todo: refactor it
    @PostMapping("/{id}/check")
    public String check(@PathVariable("id") long id, @RequestParam("solution") String solution, Model model)
            throws Exception {

        Task task = taskService.findOne(id);
        TaskStatistic statistic = taskService.findOrCreateStatistic(UserDetailsImpl.getCurrentUser(), task);
        List<Diagnostic<? extends JavaFileObject>> diagnostics = compileService.compile(solution, task);

        if (!diagnostics.isEmpty()) {
            statistic.failed();
            handleErrors(model, diagnostics);
            return "redirect:/task/"+id;
        }

        Result result = taskService.verify(solution, task);
        updateStatistic(statistic, result);
        taskService.saveStatistic(statistic);

        if(result.getFailureCount() > 0) {
            return "redirect:/task/"+id;
        }

        return "redirect:/task/list";
    }

    private void updateStatistic(TaskStatistic statistic, Result result) {
        if(result.getFailureCount() == 0) {
            statistic.setCompleted(true);
        } else {
            statistic.failed();
        }
    }

    private void handleErrors(Model model, List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        if(!diagnostics.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Diagnostic diagnostic : diagnostics) {
                //todo: reformat the message
                builder.append(diagnostic.toString());
            }
            model.addAttribute("error", builder.toString());
        }
    }
}
