package com.example.demo.controller;

import com.example.demo.model.*;
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
    public String updateUser(Model model, Principal principal, @ModelAttribute User updateUser) {
        User user = (User) ((Authentication) principal).getPrincipal();
        user.setName(updateUser.getName());
        user.setAddress(updateUser.getAddress());
        user.setPhoneNumber(updateUser.getPhoneNumber());
        user.setEmail(updateUser.getEmail());
        user.setBirthday(updateUser.getBirthday());
        user.setSex(updateUser.getSex());
        user.setAvatar(updateUser.getAvatar());

        userRepository.save(user);
        return thongtincanhan(model, principal);
    }

//    =========================================== bangdiem ================================================

    @GetMapping("/bangdiem")
    public String bangdiem(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("student", student);

//
        int tcttl = 0;
        double tbctl = 0;

        List<StudentPoint> studentPoints = new ArrayList<StudentPoint>();
        for (StudentPoint studentPoint : studentPointRepository.findAll()) {
            if (studentPoint.getStudentId().equals(student.getId())) {
                studentPoints.add(studentPoint);
                if (studentPoint.getDiemTongket() >= 4.0) {
                    Subject subject = subjectRepository.findById(studentPoint.getSubjectId()).get();
                    tcttl += subject.getSoTinChi();
                }
            }
        }
        model.addAttribute("studentPoint", studentPoints.size() > 0 ? studentPoints : null);

        Set<Subject> subjectDk = studentPointRepository.findSubjectsByStudentId(student.getId());
        model.addAttribute("subjectDk", subjectDk);

        model.addAttribute("tcttl", tcttl);
        model.addAttribute("tbctl", tbctl);

        return "sidebar/bangdiem";
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

//    =========================================== dangkimonhoc ================================================

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

        for (Subject sb : subjects) {
            sb.setSoLuongSvDaDk(studentPointRepository.findStudentsBySubjectId(sb.getId()).size());
            subjectRepository.save(sb);
        }

        model.addAttribute("mess", null);
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

            if (subject.get().getSoLuongSvDaDk() >= subject.get().getSoLuongSv()) {
                model.addAttribute("mess", "Lớp đầy");
            } else if (!subjectDk.add(subject.get())) {
                model.addAttribute("mess", "Đã đăng ký môn trước đó");
            } else {
                subjects.remove(subjectDk);
                studentPointRepository.save(studentPoint);
                model.addAttribute("mess", "Đã đăng ký thành công");
            }
        }

        for (Subject sb : subjects) {
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

        if(studentPointRepository.findStudentPointByStudentIdAndSubjectId(student.getId(),subject.get().getId()).getDiemQuaTrinh() > 0.0){
            model.addAttribute("mess", "Đã có điểm, không được hủy môn");
        }
        else if (subject.isPresent()) {
            for (StudentPoint sp : studentPointRepository.findAll()) {
                if (id.equals(sp.getSubjectId())) {
                    studentPointRepository.deleteById(sp.getId());
                }
            }
            subjectDk.remove(subject.get());

            for (Subject sb : subjects) {
                sb.setSoLuongSvDaDk(studentPointRepository.findStudentsBySubjectId(sb.getId()).size());
                subjectRepository.save(sb);
                model.addAttribute("mess", "Đã hủy thành công");
            }
        }



        model.addAttribute("listSubject", subjects);
        model.addAttribute("subjectDk", subjectDk);

        return "sidebar/dangkimonhoc";
    }

//    =========================================== lichthichinhthuc ================================================

    @GetMapping("/lichthichinhthuc")
    public String lichthichinhthuc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Student student = studentRepository.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("student", student);

//
        Set<Subject> subjects = studentPointRepository.findSubjectsByStudentId(student.getId());
        model.addAttribute("listSubject", subjects);
        model.addAttribute("dateFormat", new SimpleDateFormat("yyyy-MM-dd"));

        List<StudentPoint> studentPoints = studentPointRepository.findStudentPointByStudentId(student.getId());

//        System.out.println("\n");
//        studentPoints.stream().forEach(System.out::println);
//        System.out.println("\n");
        model.addAttribute("studentPoint", studentPoints);

        return "sidebar/lichthichinhthuc";
    }

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

//    =========================================== quanligiaovien ================================================

    @GetMapping("/quanligiaovien")
    public String quanligiaovien(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", studentRepository.findByUserId(user.getId()));
        return "sidebar/quanligiaovien";
    }

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

        return "sidebar/quanlisinhvien";
    }
}
