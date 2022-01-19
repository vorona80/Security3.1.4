package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RegistrationUser;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.User;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userServiceImpl;
    private RegistrationUser registrationUser;
    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RegistrationUser registrationUser){
        this.userServiceImpl = userServiceImpl;
        this.registrationUser = registrationUser;
    }
    @GetMapping("")
    public String returnUser (Principal principal, Model model) {
        model.addAttribute("user", userServiceImpl.getUser());
        model.addAttribute("username", userServiceImpl.findByUsername(principal.getName()));
        return "admin";
    }

    //    @GetMapping("/{id}")
//    public String show(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("people",userService.show(id));
//        return "show";
//    }
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping
    public String create(@ModelAttribute("user")@Validated User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        registrationUser.creat(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") @Validated User user, BindingResult bindingResult, @PathVariable("id")Long id) {
        if(bindingResult.hasErrors())
            return "edit";
        userServiceImpl.update(id,user);
        return  "redirect:/admin";
    }
    //   @DeleteMapping("/{id}")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id) {
        userServiceImpl.delete(id);
        return  "redirect:/admin";
    }
}


