package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.model.Task;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.service.impl.CompileService;
import ua.nure.util.Context;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;
    private final StorageService storageService;

    private final Context context;
    private final CompileService compileService;

    @Autowired
    public TaskController(TaskService taskService, StorageService storageService, Context context, CompileService compileService) {
        this.taskService = taskService;
        this.storageService = storageService;
        this.context = context;
        this.compileService = compileService;
    }

    @GetMapping
    public String get(){
        return "task/add";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") long id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);

        String source = taskService.loadTask(task);
        model.addAttribute("source", source);

        return "task/view";
    }

    @PostMapping
    public String post(@RequestParam("source") MultipartFile source,
                       @RequestParam("test") MultipartFile test,
                       @ModelAttribute Task task,
                       Model model) throws IOException {
        //todo: do some validation;

        taskService.save(task, source.getInputStream(), test.getInputStream());
        model.addAttribute("result", "success");

        return "main";
    }

    //todo: refactor this shit
    @PostMapping("/{id}/check")
    public String check(@PathVariable("id") long id, @RequestParam("solution") String solution, Model model)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException, MalformedURLException
    {
        Task task = taskService.findOne(id);

        String solutionClass = task.getSourceClassName();
        String testClass = task.getTestClassName();

        String solutionPath = storageService.saveTemp(solutionClass, solution);
        String testPath = storageService.saveTemp(testClass, taskService.loadTest(id));

        List<Diagnostic<? extends JavaFileObject>> diagnostics = compileService.compile(solutionPath, testPath);

        if(!diagnostics.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (Diagnostic diagnostic : diagnostics) {
                //todo: reformat the message
                builder.append(diagnostic.toString());
            }
            model.addAttribute("error", builder.toString());
            return "main";
        }

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("data/temp/admin").toURI().toURL() });
        Class.forName(solutionClass, false, classLoader);

        Object taskTest = Class.forName(testClass, false, classLoader).newInstance();
        Method method = taskTest.getClass().getMethod("test");
        boolean result = (boolean) method.invoke(taskTest);

        model.addAttribute("result", result);
        return "main";
    }
}
