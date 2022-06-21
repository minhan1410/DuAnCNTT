package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class ControllerTeacher {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;
    @Autowired
    private StudentPointRepository studentPointRepository;


//    =========================================== quanlilophoc ================================================

    @GetMapping("/quanlilophoc")
    public String quanlilophoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Teacher teacher = teacherRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", teacher);

//        System.out.println("\n\n" + teacher.getId() + "\n");
//        teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId()).forEach(System.out::println);
//        System.out.println("\n");
//        teacherSubjectRepository.findStudentsByTeacherId(teacher.getId()).forEach(System.out::println);

        model.addAttribute("listSubject", teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId()));
        model.addAttribute("listStudent", null);
//        model.addAttribute("listStudent", teacherSubjectRepository.findStudentsByTeacherId(teacher.getId()));
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

        List<Subject> listSubject = teacherSubjectRepository.findSubjectsByTeacherId(teacher.getId());
        List<Student> listStudent = teacherSubjectRepository.findStudentsByTeacherIdAndSubjectId(teacher.getId(), subjectId);

        List<StudentPoint> listStudentPoints = new ArrayList<StudentPoint>();
        for(Subject subject : listSubject){
            if(subject.getId().equals(subjectId)){
                for(Student student : listStudent){
                    StudentPoint sp = studentPointRepository.findStudentPointByStudentIdAndSubjectId(student.getId(),subject.getId());
                    if(sp != null){
                        listStudentPoints.add(sp);
                    }
                }
            }

        }

//        System.out.println("\n\n");
//        listSubject.forEach(System.out::println);
//        System.out.println("\n\n");
//        listStudent.forEach(System.out::println);
//        System.out.println("\n\n");
//        listStudentPoints.forEach(System.out::println);
//        System.out.println("\n\n");

        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listStudent", listStudent);
        model.addAttribute("listStudentPoints", listStudentPoints);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanlilophoc";
    }

    @PostMapping("/danhgiasv")
    public String danhgiasv(Model model, Principal principal, @RequestParam("idstudentPoint") Long idstudentPoint, @RequestParam("tinhTrang") String tinhTrang,
                            @RequestParam("quaTrinh") Double quaTrinh, @RequestParam("cuoiKi") Double cuoiKi, @RequestParam("tongKet") Double tongKet) {

        StudentPoint sp = studentPointRepository.findById(idstudentPoint).get();
        sp.setTinhTrang(tinhTrang);
        sp.setDiemQuaTrinh(quaTrinh);
        sp.setDiemCuoiKy(cuoiKi);
        sp.setDiemTongket(tongKet);

        System.out.println("\n\n"+sp+"\n\n");
        studentPointRepository.save(sp);
        // return chonMonQuanlilophoc(model, principal,sp.getSubjectId());
        return "redirect:/quanlilophoc/"+sp.getSubjectId();
    }

//  =========================================== quanliSV ================================================

    @GetMapping("/quanliSV")
    public String quanliSV(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Teacher teacher = teacherRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", teacher);

        model.addAttribute("listStudent", studentRepository.findStudentByGiaoVienId(teacher.getId()));
//        model.addAttribute("listStudent", teacherSubjectRepository.findStudentsByTeacherId(teacher.getId()));
        model.addAttribute("userRepository", userRepository);


        return "sidebar/dssv";
    }
}
