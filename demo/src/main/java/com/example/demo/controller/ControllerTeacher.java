package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Teacher;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Set;

@Controller
public class ControllerTeacher {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;


//    =========================================== quanlilophoc ================================================

    @GetMapping("/quanlilophoc")
    public String quanlilophoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Teacher teacher = teacherRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", teacher);

//
//        System.out.println("\n\n" + teacher.getId() + "\n");
//        teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId()).forEach(System.out::println);
//        System.out.println("\n");
//        teacherSubjectRepository.findStudentsByTeacherId(teacher.getId()).forEach(System.out::println);

        model.addAttribute("listSubject", teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId()));
        model.addAttribute("listStudent", teacherSubjectRepository.findStudentsByTeacherId(teacher.getId()));
        model.addAttribute("userRepository", userRepository);


        return "sidebar/quanlilophoc";
    }

    @GetMapping("/quanlilophoc/{id}")
    public String chonMonQuanlilophoc(Model model, Principal principal, @PathVariable("id") String subjectId) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Teacher teacher = teacherRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", teacher);
//

        Set<Subject> listSubject = teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId());
        Set<Student> listStudent = teacherSubjectRepository.findStudentsByTeacherIdAndSubjectId(teacher.getId(), subjectId);

//        System.out.println("\n\n");
//        listSubject.forEach(System.out::println);
        System.out.println("\n\n");
        listStudent.forEach(System.out::println);
        System.out.println("\n\n");

        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listStudent", listStudent);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanlilophoc";
    }

}
