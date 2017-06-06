package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.model.Label;
import ua.nure.repository.LabelRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/label")
public class LabelController {

    private final LabelRepository repository;

    @Autowired
    public LabelController(LabelRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<String> labels(@RequestParam("term") String label){
        return repository.findAllByValueIgnoreCaseContains(label)
                         .stream()
                         .map(Label::getValue)
                         .collect(Collectors.toList());
    }
}
