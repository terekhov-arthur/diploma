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
import ua.nure.service.TaskService;

import java.io.IOException;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String get(){
        return "task/add";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") long id, Model model) {
        Task task = taskService.findOne(id);
        model.addAttribute("task", task);

        String source = taskService.loadTaskSource(task);
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
}
