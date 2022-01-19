package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;

@Controller
@RequestMapping("/user")
//@RestController
public class UserController {
    private final UserServiceImpl userServiceInpl;
@Autowired
    public UserController(UserServiceImpl userServiceInpl) {
        this.userServiceInpl = userServiceInpl;
    }

    @GetMapping("")
    public String show(Principal principal, Model model) {
  //      User people = userServiceInpl.findByUsername(principal.getName());
        model.addAttribute("people",userServiceInpl.findByUsername(principal.getName()));
        return "user";
    }
//    @GetMapping("/")
//    public String homePage () {
//        return "HOME";
//    }
//    @GetMapping("/authentificated")
//    public String pageAuthentificated(Principal principal){
//        return "защищенная область: " + principal.getName();
//    }

//@GetMapping("/user")
//    public String pageAdmin(Principal principal){
//        User user = userServiceInpl.findByUsername(principal.getName());
//        return  user.getUsername() + " with roles; " + user.getRoles();
//    }

}
