package com.StudentsManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.StudentsManagementSystem.entity.User;
import com.StudentsManagementSystem.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/process-register")
    public String saveUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword
    ) {

        if(!password.equals(confirmPassword)) {
            return "redirect:/register?error=password_mismatch";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        userRepo.save(user);

        return "redirect:/login?registerSuccess";
    }
}
