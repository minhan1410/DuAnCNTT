package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/"})
    public String homepage() {
        return "home";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/registration")
    public String Registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String Registration(@ModelAttribute User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        System.out.printf("\n\n%s\n\n", newUser);

        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return "registration";
        }
        userRepository.save(newUser);
        return "home";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
//        CustomUserDetails customUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();
//        System.out.printf("\n\n%s\n\n", customUser.getUser());
//        model.addAttribute("user", customUser.getUser());
//        return "user";
        User customUser = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", customUser);
        model.addAttribute("user", customUser);
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
//        CustomUserDetails customUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();
//        System.out.printf("\n\n%s\n\n", customUser.getUser());
//        model.addAttribute("user", customUser.getUser());
//        return "admin";
        User customUser = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", customUser);
        model.addAttribute("user", customUser);
        return "admin";
    }
}

//https://openplanning.net/11705/tao-ung-dung-login-voi-spring-boot-spring-security-jpa#a13946602
//https://techmaster.vn/posts/36586/spring-security-authorization#3-user---role---post
//https://loda.me/articles/sb9-gii-thch-cch-thymeleaf-vn-hnh-expression-demo-full#79d0540ecdf74fdc90639a81e7e86a49
//https://loda.me/articles/sb13-chi-tit-spring-boot-thymeleaf-mysql-i18n-web-demo
//https://loda.me/articles/ss-huong-dan-spring-security-jpa-hibernate#569f55c47e1c4c029a0e93a6ab5f3632
