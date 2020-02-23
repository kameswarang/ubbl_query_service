package ubbl.query_service.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import ubbl.query_service.model.User;
import ubbl.query_service.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String register(Model model) {
        model.addAttribute(new User());
        return "register";
    }
    
    @PostMapping
    public String processRegister(@Valid User newUser, Errors error) {
        if(error.hasErrors()) {
            return "register";
        }
        userService.saveUser(newUser);
        return "redirect:/login?registrationSuccess";
    }
}