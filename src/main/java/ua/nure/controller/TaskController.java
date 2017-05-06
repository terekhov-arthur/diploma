package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.service.StorageService;

import java.io.IOException;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final StorageService storageService;

    @Autowired
    public TaskController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String get(){
        return "add-task";
    }

    @PostMapping
    public String post(@RequestParam("taskName") String taskName,
                       @RequestParam("source") MultipartFile source,
                       @RequestParam("test") MultipartFile test,
                       Model model) throws IOException {
        //todo: do some validation;
        //todo: map fileName to start with username;
        storageService.save(taskName + "_source.java", source.getInputStream());
        storageService.save(taskName + "_test.java", test.getInputStream());

        model.addAttribute("result", "success");

        return "main";
    }

    //todo: separate this logic and add tabs converting
    private String convertToHTML(String source) {
        return source.replaceAll("\n", "<br/>");
    }

}
