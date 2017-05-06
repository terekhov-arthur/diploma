package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.model.Task;
import ua.nure.service.StorageService;
import ua.nure.service.TaskService;
import ua.nure.service.impl.TaskServiceImpl;

import java.io.IOException;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final StorageService storageService;
    private final TaskService taskService;

    @Autowired
    public TaskController(StorageService storageService, TaskService taskService) {
        this.storageService = storageService;
        this.taskService = taskService;
    }

    @GetMapping
    public String get(){
        return "add-task";
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

    //todo: separate this logic and add tabs converting
    private String convertToHTML(String source) {
        return source.replaceAll("\n", "<br/>");
    }

}
