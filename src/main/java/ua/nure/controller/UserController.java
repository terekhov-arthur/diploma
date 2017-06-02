package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.model.Level;
import ua.nure.model.User;
import ua.nure.repository.LevelRepository;
import ua.nure.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public UserController(UserRepository userRepository, LevelRepository levelRepository) {
        this.userRepository = userRepository;
        this.levelRepository = levelRepository;
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user, @RequestParam("re-password") String rePassword, Model model){
        if (!user.getPassword().equals(rePassword)) {
            model.addAttribute("error", "passwords do not match");
            return "sign-up";
        }

        Level firstLevel = levelRepository.findOne(1L);
        user.setLevel(firstLevel);
        userRepository.save(user);

        model.addAttribute("sign-up-successful", true);
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUpPage(){
        return "sign-up";
    }
}
