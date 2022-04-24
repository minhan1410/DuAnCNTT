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

    @GetMapping("/dangkihoc")
    public String dangkihoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        System.out.printf("\n\n%s\n\n", studentRepository.findByUserId(user.getId()));

        model.addAttribute("user", user);

        if (user.getPermissions().equals("ROLE_Student")) {
            model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        } else {
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
    public String updateUser(Model model, Principal principal, @ModelAttribute User updateUser) {
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
        model.addAttribute("listSubject", subjectRepository.findAll());
        model.addAttribute("teacherRepository", teacherRepository);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/thoikhoabieutoantruong";
    }

    @GetMapping("/dangkimonhoc")
    public String dangkimonhoc(Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", student);
//
        List<Subject> subjects = subjectRepository.findAll(); //TKB toan truong
        Set<Subject> subjectDk = studentPointRepository.findSubjectsByStudentId(student.getId()); // ds mon da dk

        subjects.remove(subjectDk);

        for(Subject sb: subjects) {
            sb.setSoLuongSvDaDk(studentPointRepository.findStudentsBySubjectId(sb.getId()).size());
            subjectRepository.save(sb);
        }

        model.addAttribute("listSubject", subjects);
        model.addAttribute("subjectDk", subjectDk.size() == 0 ? null : subjectDk);


        return "sidebar/dangkimonhoc";
    }

    @GetMapping("/dkh/{id}")
    public String dangkimonhoc(@PathVariable("id") String id, Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", student);
//
        List<Subject> subjects = subjectRepository.findAll();
        Set<Subject> subjectDk = studentPointRepository.findSubjectsByStudentId(student.getId());

        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            StudentPoint studentPoint = new StudentPoint();
            studentPoint.setStudentId(student.getId());
            studentPoint.setSubjectId(subject.get().getId());

            if (subjectDk.add(subject.get())) {
                subjects.remove(subjectDk);
                studentPointRepository.save(studentPoint);
            } else {
                model.addAttribute("mess", "Đã đăng ký môn trước đó");
            }

            System.out.println("\n\n" + "\n\n");
        }

        for(Subject sb: subjects) {
            sb.setSoLuongSvDaDk(studentPointRepository.findStudentsBySubjectId(sb.getId()).size());
            subjectRepository.save(sb);
        }

        model.addAttribute("listSubject", subjects);
        model.addAttribute("subjectDk", subjectDk);

        return "sidebar/dangkimonhoc";
    }

    @GetMapping("/huydkh/{id}")
    public String huydangkimonhoc(@PathVariable("id") String id, Model model, Principal principal) {
//        header
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", student);
//
        List<Subject> subjects = subjectRepository.findAll();
        Set<Subject> subjectDk = studentPointRepository.findSubjectsByStudentId(student.getId());

        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            for (StudentPoint sp : studentPointRepository.findAll()) {
                if (id.equals(sp.getSubjectId())) {
                    studentPointRepository.deleteById(sp.getId());
                }
            }

            subjectDk.remove(subject.get());
        }

        for(Subject sb: subjects) {
            sb.setSoLuongSvDaDk(studentPointRepository.findStudentsBySubjectId(sb.getId()).size());
            subjectRepository.save(sb);
        }

        model.addAttribute("listSubject", subjects);
        model.addAttribute("subjectDk", subjectDk);

        return "sidebar/dangkimonhoc";
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
