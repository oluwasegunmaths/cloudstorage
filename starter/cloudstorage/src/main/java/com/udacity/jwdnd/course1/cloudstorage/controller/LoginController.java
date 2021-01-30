package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;

    public LoginController (AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @GetMapping()
    public String loginView(
            Model model
    ) {

        if (authenticationService.isAuthFailure()) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Authentication failed check your username and password");
        }
        return "login";
    }}
