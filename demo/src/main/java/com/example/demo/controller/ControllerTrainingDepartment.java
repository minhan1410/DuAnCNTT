package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ControllerTrainingDepartment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SpecializedRepository specializedRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

//    =========================================== quanlisinhvien ================================================

    @GetMapping("/quanlisinhvien")
    public String quanlisinhvien(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);

        if (user.getPermissions().equals("ROLE_Student")) {
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        } else {
            model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        }

//
        model.addAttribute("listStudent", studentRepository.findAll());
        model.addAttribute("userRepository", userRepository);

//        https://stackoverflow.com/questions/53375549/spring-1-form-2-entities

        model.addAttribute("newUser", new User());
        model.addAttribute("listSpecialized", specializedRepository.findAll());

        Map<String,String> mapGVCN = new HashMap<String,String>();
        for(Teacher t: teacherRepository.findByGvChuNhiem(true)){
            mapGVCN.put(t.getId(),userRepository.findById(t.getUserId()).get().getName());
        }
        model.addAttribute("listTeacher", mapGVCN);

        return "sidebar/quanlisinhvien";
    }

//    =========================================== themsinhvien ================================================

    @PostMapping("/dangKiSinhVien")
    public String registrationUser(Model model, Principal principal, @ModelAttribute User newUser, @RequestParam("chuyenNganh") String chuyenNganh, @RequestParam("gvcn") String gvcn) {
        int sizeStudents = studentRepository.findAll().size();

        newUser.setUsername(String.format("A%05d", ++sizeStudents));
        newUser.setPassword(passwordEncoder.encode("111"));
        userRepository.save(newUser);

        Student newStudent = new Student();
        newStudent.setId(newUser.getUsername());
        newStudent.setUserId(userRepository.findByUsername(newUser.getUsername()).getId());
        newStudent.setChuyenNganhId(chuyenNganh);
        newStudent.setGiaoVienId(gvcn);
        studentRepository.save(newStudent);

        return quanlisinhvien(model, principal);
    }
}
