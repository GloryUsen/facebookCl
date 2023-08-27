package com.facebook_clone.controller;

import com.facebook_clone.model.UsersModel;
import com.facebook_clone.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getEmail(), usersModel.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/api/register")
    public String apiRegister(){
        System.out.println("register request: ");
        return "homepage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            return "homepage";
        } else {
            return "error_page";
        }
    }
}
