package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.SpecializedRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class SidebarController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecializedRepository specializedRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/dangkihoc")
    public String dangkihoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(user.getId()));

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "dangkihoc";
    }

    @GetMapping("/thongtincanhan")
    public String thongtincanhan(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "sidebar/thongtincanhan";
    }

    @PostMapping("/updateUser")
    public String updateUser(Model model, Principal principal, @ModelAttribute User updateUser){
        User user = (User) ((Authentication) principal).getPrincipal();
        user.setName(updateUser.getName());
        user.setAddress(updateUser.getAddress());
        user.setPhoneNumber(updateUser.getPhoneNumber());
        user.setEmail(updateUser.getEmail());
        user.setBirthday(updateUser.getBirthday());
        user.setSex(updateUser.getSex());

        userRepository.save(user);
        return thongtincanhan(model, principal);
    }


    @GetMapping("/bangdiem")
    public String bangdiem(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(user.getId()));

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "sidebar/bangdiem";
    }
}
