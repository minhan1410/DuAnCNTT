package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class SidebarController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecializedRepository specializedRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentPointRepository studentPointRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;

//    =========================================== dangkihoc ================================================

    @GetMapping("/dangkihoc")
    public String dangkihoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
//        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(user.getId()));

        model.addAttribute("user", user);

        if (user.getPermissions().equals("ROLE_Student")) {
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        } else {
            model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        }

        return "dangkihoc";
    }

//    =========================================== thongtincanhan ================================================


    @GetMapping("/thongtincanhan")
    public String thongtincanhan(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);

        if (user.getPermissions().equals("ROLE_Student")) {
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        } else {
            model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        }
        return "sidebar/thongtincanhan";
    }

    @PostMapping("/updateUser")
    public String updateUser(Principal principal, @ModelAttribute User updateUser) {
        User user = (User) ((Authentication) principal).getPrincipal();
        user.setName(updateUser.getName());
        user.setAddress(updateUser.getAddress());
        user.setPhoneNumber(updateUser.getPhoneNumber());
        user.setEmail(updateUser.getEmail());
        user.setBirthday(updateUser.getBirthday());
        user.setSex(updateUser.getSex());
        user.setAvatar(updateUser.getAvatar());

        userRepository.save(user);
//        return thongtincanhan(model, principal);
        return "redirect:/thongtincanhan";
    }

//    =========================================== thoikhoabieutoantruong ================================================

    @GetMapping("/thoikhoabieutoantruong")
    public String thoikhoabieutoantruong(Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);

        if (user.getPermissions().equals("ROLE_Student")) {
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        } else {
            model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        }

//
        model.addAttribute("listSubject", subjectRepository.findAll());
        model.addAttribute("teacherRepository", teacherRepository);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/thoikhoabieutoantruong";
    }

}
