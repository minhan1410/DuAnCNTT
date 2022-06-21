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
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ControllerTrainingDepartment {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;
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
    public String registrationUser(@ModelAttribute User newUser, @RequestParam("chuyenNganh") String chuyenNganh, @RequestParam("gvcn") String gvcn) {
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

        return "redirect:/quanlisinhvien";
    }

    @PostMapping("/capNhatTrangThai/{index}")
    public String capNhatTrangThai(@PathVariable("index") int index, @ModelAttribute Student student) {
        Student s = studentRepository.findAll().get(index);
        s.setTrangThai(student.getTrangThai());
        studentRepository.save(s);

        return "redirect:/quanlisinhvien";
    }

//    =========================================== quanligiaovien ================================================

    @GetMapping("/quanligiaovien")
    public String quanligiaovien(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();
        Teacher teacher = teacherRepository.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("student", teacher);
//
        model.addAttribute("newUser", new User());
        model.addAttribute("listSpecialized", specializedRepository.findAll());

        if (user.getPermissions().equals("ROLE_Admin")) {
            model.addAttribute("listTeacher", teacherRepository.findAll()
                    .stream().filter(t -> !t.getId().equals(teacher.getId())).collect(Collectors.toList()));
        } else {
            List<Teacher> listTeacher = new ArrayList<Teacher>();
            for (Teacher t : teacherRepository.findAll()) {
                if (userRepository.findById(t.getUserId()).get().getPermissions().equals("ROLE_Teacher")) {
                    listTeacher.add(t);
                }
            }

            model.addAttribute("listTeacher", listTeacher);
        }

        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanligiaovien";
    }

    @PostMapping("/dangKiGiaoVien")
    public String dangKiGiaoVien(@ModelAttribute User newUser, @RequestParam("chuyenNganh") String chuyenNganh, @RequestParam("gvcn") Boolean gvcn) {
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

        return "redirect:/quanligiaovien";
    }

    @PostMapping("capNhatTrangThaiGV/{index}")
    public String capNhatTrangThaiGV(@PathVariable("index") int index, @RequestParam("trangThai") String trangThai) {
        Teacher s = teacherRepository.findAll().get(index);
        s.setTrangThai(trangThai);

        teacherRepository.save(s);
        return "redirect:/quanligiaovien";
    }

//    =========================================== quanlichuyennganh ================================================

    @GetMapping("/quanlichuyennganh")
    public String quanlichuyennganh(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
//
        model.addAttribute("newSpecialized", new Specialized());
        model.addAttribute("listSpecialized", specializedRepository.findAll()
                .stream().filter(specialized -> !specialized.getId().equals("AD") && !specialized.getId().equals("DT")).collect(Collectors.toList()));
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

    @GetMapping("/xoaChuyenNganh/{idSpecialized}")
    public String xoaChuyenNganh(@PathVariable("idSpecialized") String idSpecialized) {
        specializedRepository.deleteById(idSpecialized);
        return "redirect:/quanlichuyennganh";
    }

    @PostMapping("/capNhatChuyenNganh/{id}")
    public String capNhatChuyenNganh(@PathVariable("id") String id, @RequestParam("nameSpecialized") String nameSpecialized) {
        Specialized s = specializedRepository.findById(id).get();
        s.setName(nameSpecialized);

        specializedRepository.save(s);
        return "redirect:/quanlichuyennganh";
    }

//  =========================================== quanlimonhoc ================================================

    @GetMapping("/quanlimonhoc")
    public String quanlimonhoc(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
        //
        model.addAttribute("newSubject", new Subject());
        model.addAttribute("listSubject", subjectRepository.findAll());
        model.addAttribute("listSpecialized", specializedRepository.findAll());
        model.addAttribute("listTeacher", teacherRepository.findAll());
        model.addAttribute("teacherRepository", teacherRepository);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/quanlimonhoc";
    }

    @PostMapping("/dangKiMonHoc")
    public String dangKiMonHoc(Model model, Principal principal, @ModelAttribute Subject newSubject) {
        String mess = null;
        if (subjectRepository.findById(newSubject.getId()).isPresent()) {
            mess = "Mã môn đã tồn tại";
        } else if (subjectRepository.findByName(newSubject.getName()) != null) {
            mess = "Tên môn đã tồn tại";
        } else {
            subjectRepository.save(newSubject);
            TeacherSubject ts =  TeacherSubject.builder().id((long)teacherSubjectRepository.findAll().size()).subjectId(newSubject.getId()).teacherId(newSubject.getTeacherId()).build();
            teacherSubjectRepository.save(ts);
        }
        model.addAttribute("mess", mess);
        return quanlimonhoc(model, principal);
    }

    @GetMapping("/xoaMonHoc/{idSubject}")
    public String xoaMonHoc(@PathVariable("idSubject") String idSubject) {
        subjectRepository.deleteById(idSubject);
        return "redirect:/quanlimonhoc";
    }

    @PostMapping("/capNhatMonHoc/{index}")
    public String capNhatMonHoc(@PathVariable("index") int index, @RequestParam("name") String name,
                                @RequestParam("tenLop") String tenLop, @RequestParam("ca") String ca, @RequestParam("thu") String thu,
                                @RequestParam("phongHoc") String phongHoc, @RequestParam("soTinChi") int soTinChi,
                                @RequestParam("soLuongSv") int soLuongSv) {
        Subject s = subjectRepository.findAll().get(index);
        s.setName(name);
        s.setTenLop(tenLop);
        s.setCa(ca);
        s.setThu(thu);
        s.setPhongHoc(phongHoc);
        s.setSoTinChi(soTinChi);
        s.setSoLuongSv(soLuongSv);

        subjectRepository.save(s);
        return "redirect:/quanlimonhoc";
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

    @PostMapping("/capNhatLichThi/{index}")
    public String capNhatLichThi(@PathVariable("index") int index, @RequestParam("ngayThi") String ngayThi,
                                 @RequestParam("caThi") String caThi) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Subject s = subjectRepository.findAll().get(index);
        s.setNgayThi(dateFormat.parse(ngayThi));
        s.setCaThi(caThi);

        subjectRepository.save(s);
        return "redirect:/laplichthi";
    }

//    =========================================== phanconggiangday ================================================

    @GetMapping("/phanconggiangday")
    public String phanconggiangday(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));
//
        model.addAttribute("listSubject", subjectRepository.findAll());
        model.addAttribute("listTeacher", teacherRepository.findAll());
        model.addAttribute("teacherRepository", teacherRepository);
        model.addAttribute("userRepository", userRepository);

        return "sidebar/phanconggiangday";
    }

    @PostMapping("/capnhapPCGD/{index}")
    public String capnhapPCGD(@PathVariable("index") int index, @RequestParam("gvID") String gvID) {
        Subject s = subjectRepository.findAll().get(index);
        s.setTeacherId(gvID);
        subjectRepository.save(s);

        TeacherSubject teacherRepository = teacherSubjectRepository.findAll().stream()
                .filter(teacherSubject -> teacherSubject.getSubjectId().equals(s.getId()))
                .collect(Collectors.toList()).get(0);
        teacherRepository.setTeacherId(gvID);
        teacherSubjectRepository.save(teacherRepository);

        return "redirect:/phanconggiangday";
    }
}