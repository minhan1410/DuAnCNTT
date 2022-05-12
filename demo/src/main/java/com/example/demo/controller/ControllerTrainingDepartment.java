package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private SubjectRepository subjectRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

//    =========================================== quanlisinhvien ================================================

    @GetMapping("/quanlisinhvien")
    public String quanlisinhvien(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));

//

//        https://stackoverflow.com/questions/53375549/spring-1-form-2-entities

        model.addAttribute("newUser", new User());
        model.addAttribute("listSpecialized", specializedRepository.findAll());

        Map<String, String> mapGVCN = new HashMap<String, String>();
        for (Teacher t : teacherRepository.findByGvChuNhiem(true)) {
            mapGVCN.put(t.getId(), userRepository.findById(t.getUserId()).get().getName());
        }
        model.addAttribute("listTeacher", mapGVCN);

        model.addAttribute("listStudent", studentRepository.findAll());
        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanlisinhvien";
    }

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

    @PostMapping("/capNhatTrangThai/{index}")
    public String capNhatTrangThai(Model model, Principal principal, @PathVariable("index") int index, @ModelAttribute Student student) {
        Student s = studentRepository.findAll().get(index);
        s.setTrangThai(student.getTrangThai());
        studentRepository.save(s);

        return quanlisinhvien(model, principal);
    }

//    =========================================== quanligiaovien ================================================

    @GetMapping("/quanligiaovien")
    public String quanligiaovien(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
//
        model.addAttribute("newUser", new User());
        model.addAttribute("listSpecialized", specializedRepository.findAll());

        model.addAttribute("listTeacher", teacherRepository.findAll());
        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanligiaovien";
    }

    @PostMapping("/dangKiGiaoVien")
    public String dangKiGiaoVien(Model model, Principal principal, @ModelAttribute User newUser, @RequestParam("chuyenNganh") String chuyenNganh, @RequestParam("gvcn") Boolean gvcn) {
        int sizeTeacher = teacherRepository.findAll().size();

        newUser.setUsername(String.format("B%05d", ++sizeTeacher));
        newUser.setPassword(passwordEncoder.encode("111"));
        userRepository.save(newUser);

        Teacher newTeacher = new Teacher();
        newTeacher.setId(newUser.getUsername());
        newTeacher.setUserId(userRepository.findByUsername(newUser.getUsername()).getId());
        newTeacher.setChuyenNganhId(chuyenNganh);
        newTeacher.setGvChuNhiem(gvcn);
        teacherRepository.save(newTeacher);

        return quanligiaovien(model, principal);
    }

    @PostMapping("capNhatTrangThaiGV/{index}")
    public String capNhatTrangThaiGV(Model model, Principal principal, @PathVariable("index") int index, @RequestParam("trangThai") String trangThai) {
        Teacher s = teacherRepository.findAll().get(index);
        s.setTrangThai(trangThai);

        teacherRepository.save(s);
        return quanligiaovien(model, principal);
    }
//    =========================================== quanlichuyennganh ================================================

    @GetMapping("/quanlichuyennganh")
    public String quanlichuyennganh(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
//
        model.addAttribute("newSpecialized", new Specialized());
        model.addAttribute("listSpecialized", specializedRepository.findAll());
        return "sidebar/quanlichuyennganh";
    }

    @PostMapping("/dangKiChuyenNganh")
    public String dangKiChuyenNganh(Model model, Principal principal, @ModelAttribute Specialized newSpecialized) {
        String mess = null;
        if (specializedRepository.findById(newSpecialized.getId()).isPresent()) {
            mess = "Mã chuyên ngành đã tồn tại";
        } else if (specializedRepository.findByName(newSpecialized.getName()) != null) {
            mess = "Tên chuyên ngành đã tồn tại";
        } else {
            specializedRepository.save(newSpecialized);
        }
        model.addAttribute("mess", mess);
        return quanlichuyennganh(model, principal);
    }

    @GetMapping("xoaChuyenNganh/{idSpecialized}")
    public String xoaChuyenNganh(Model model, Principal principal, @PathVariable("idSpecialized") String idSpecialized) {
        specializedRepository.deleteById(idSpecialized);
        return quanlichuyennganh(model, principal);
    }

    @PostMapping("capNhatChuyenNganh/{index}")
    public String capNhatChuyenNganh(Model model, Principal principal, @PathVariable("index") int index, @RequestParam("nameSpecialized") String nameSpecialized) {
        Specialized s = specializedRepository.findAll().get(index);
        s.setName(nameSpecialized);

        specializedRepository.save(s);
        return quanlichuyennganh(model, principal);
    }

//    =========================================== laplichthi ================================================

    @GetMapping("/laplichthi")
    public String laplichthi(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
//
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("dateFormat", dateFormat);
        model.addAttribute("currentDate", new Date());

        model.addAttribute("listSubject", subjectRepository.findAll());

        return "sidebar/laplichthi";
    }

    @PostMapping("capNhatLichThi/{index}")
    public String capNhatLichThi(Model model, Principal principal, @PathVariable("index") int index, @RequestParam("ngayThi") String ngayThi,
                                 @RequestParam("caThi") String caThi) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Subject s = subjectRepository.findAll().get(index);
        s.setNgayThi(dateFormat.parse(ngayThi));
        s.setCaThi(caThi);

        subjectRepository.save(s);
        return laplichthi(model, principal);
    }

}
