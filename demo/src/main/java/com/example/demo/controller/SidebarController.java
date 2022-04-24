package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.StudentPoint;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

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

    @GetMapping("/dangkihoc")
    public String dangkihoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(user.getId()));

        model.addAttribute("user", user);

        if(user.getPermissions().equals("ROLE_Student")){
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        }else{
            model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        }

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

    @GetMapping("/thoikhoabieutoantruong")
    public String thoikhoabieutoantruong(Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));

//
        model.addAttribute("listSubject",subjectRepository.findAll());
        model.addAttribute("teacherRepository",teacherRepository);
        model.addAttribute("userRepository",userRepository);

        return "sidebar/thoikhoabieutoantruong";
    }

    @GetMapping("/dangkimonhoc")
    public String dangkimonhoc(Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
//
        model.addAttribute("listSubject",subjectRepository.findAll());
        return "sidebar/dangkimonhoc";
    }

    @GetMapping("/checkdkh/{id}")
    public String dangkimonhoc(@PathVariable("id") String id, Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", student);
//
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()){
            StudentPoint studentPoint = new StudentPoint();
            studentPoint.setStudentId(student.getId());
            studentPoint.setSubjectId(subject.get().getId());

            studentPointRepository.save(studentPoint);
        }

        return "null";
    }

    @GetMapping("/lichthichinhthuc")
    public String lichthichinhthuc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "sidebar/lichthichinhthuc";
    }

    @GetMapping("/quanlilophoc")
    public String quanlilophoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "sidebar/quanlilophoc";
    }
}
