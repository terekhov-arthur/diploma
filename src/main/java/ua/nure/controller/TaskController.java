package ua.nure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.InputParameter;
import ua.nure.model.Task;
import ua.nure.model.TaskValidator;

import java.util.Arrays;

@Controller
@RequestMapping("/task")
public class TaskController {

    @GetMapping("/{id}")
    public String get(@PathVariable("id") String id, Model model){
        model.addAttribute("task", create());
        return "task";
    }

    @PostMapping("/{id}/check")
    public String post(@PathVariable("id") String id, @RequestParam("solution") String solution, Model model){
        model.addAttribute("result", "test".equals(solution));
        return "main";
    }

    private Task create(){
        Task<String> stringTask = new Task<>();

        stringTask.setTitle("Converter");
        stringTask.setDescription("method(int n)\nYou have a number 'n' to be converted to a string.");
        stringTask.setParams(Arrays.asList(new InputParameter("number", InputParameter.Type.INT)));
        stringTask.setReturnValueType(InputParameter.Type.STRING);

        return  stringTask;
    }

}
