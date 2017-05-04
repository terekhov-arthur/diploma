package ua.nure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {

    @GetMapping
    public String get(){
        return "add-task";
    }

    @PostMapping
    public String post(@RequestParam("template") MultipartFile file, Model model) throws IOException
    {
        String data = readFile(file.getInputStream());
        model.addAttribute("data", data);

        return "main";
    }

    private String readFile(InputStream inputStream) throws IOException
    {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

}
