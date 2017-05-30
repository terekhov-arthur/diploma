package ua.nure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.nure.model.User;

@Controller
public class UserController {
    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute User user, @RequestParam("re-password") String rePassword, Model model){
        if (!user.getPassword().equals(rePassword)) {
            model.addAttribute("error", "passwords do not match");
            return "sign-up";
        }
        model.addAttribute("sign-up-successful", true);
        return "login";
    }
}
