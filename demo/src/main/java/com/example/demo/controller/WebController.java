package com.example.demo.controller;

import com.example.demo.model.Specialized;
import com.example.demo.model.User;
import com.example.demo.repository.SpecializedRepository;
import com.example.demo.repository.StudentRepository;
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
    private SpecializedRepository specializedRepository;
    @Autowired
    private StudentRepository studentRepository;
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

    @GetMapping("/dangkihoc")
    public String dangkihoc(Model model, Principal principal) {
        User customUser = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user", customUser);
        model.addAttribute("student", studentRepository.findByUserId(customUser.getId()));
        return "dangkihoc";
    }

    @GetMapping("/registrationSpecialized")
    public String registrationSpecialized(Model model) {
        model.addAttribute("specialized", new Specialized());
        return "registrationSpecialized";
    }

    @PostMapping("/registrationSpecialized")
    public String registrationSpecialized(@ModelAttribute Specialized specialized, Model model) {
        if (specializedRepository.findByName(specialized.getName()) != null) {
            model.addAttribute("mess", "specialized name unique");
            return "registrationSpecialized";
        }

        if (!specializedRepository.findById(specialized.getId()).isEmpty()) {
            model.addAttribute("mess", "specialized id unique");
            return "registrationSpecialized";
        }
        specializedRepository.save(specialized);
        return "home";
    }

    @GetMapping("/registrationUser")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "registrationUser";
    }

    @PostMapping("/registrationUser")
    public String registrationUser(@ModelAttribute User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        System.out.printf("\n\n%s\n\n", newUser);

        if (userRepository.findByUsername(newUser.getUsername()) != null) {
            return "registrationUser";
        }
        userRepository.save(newUser);
        return "home";
    }

    @GetMapping("/registrationTeacher")
    public String registrationTeacher(Model model) {
        model.addAttribute("listSpecialized", specializedRepository.findAll());
        return "registrationTeacher";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        User customUser = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", customUser);
        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(customUser.getId()));
        model.addAttribute("user", customUser);
        model.addAttribute("student", studentRepository.findByUserId(customUser.getId()));
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
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
