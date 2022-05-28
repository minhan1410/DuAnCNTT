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

import java.awt.*;
import java.security.Principal;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControllerAdmin {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentPointRepository studentPointRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherSubjectRepository teacherSubjectRepository;
    @Autowired
    private SpecializedRepository specializedRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/thongke")
    public String thongke(Model model, Principal principal) {
        User user = (User) ((Authentication) principal).getPrincipal();

        model.addAttribute("user", user);
        model.addAttribute("student", teacherRepository.findByUserId(user.getId()));

        // ===================================== Đếm số lượng User ===========================================

        Map<String, Long> mapCountingUser = userRepository.findAll().stream()
                .collect(Collectors.groupingBy(User::getPermissions, Collectors.counting()));
        List<Object> countingUser = new ArrayList<>();
        mapCountingUser.forEach((s, aLong) -> {
            countingUser.add(List.of(s, aLong));
        });

        model.addAttribute("countingUser", countingUser);

//        System.out.println("\nĐếm số lượng User");
//        countingUser.forEach(System.out::println);
//        System.out.println("\n");

        // ===================================== Thống kê điểm trung bình tích lũy ===========================================

        List<List<Object>> diemTongket = new ArrayList<>();
        List<List<Object>> soTinChi = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {
            double tbctl = 0;
            int soMonQua = 0;
            int tcttl = 0;

            for (StudentPoint studentPoint : studentPointRepository.findStudentPointByStudentId(student.getId())) {
                if (studentPoint.getDiemTongket() >= 4.0) {
                    tbctl += studentPoint.getDiemTongket();
                    soMonQua++;
                    tcttl += subjectRepository.findById(studentPoint.getSubjectId()).get().getSoTinChi();
                }
            }

            Double tb = tbctl / soMonQua;
            if (!Double.isNaN(tb)) {
                diemTongket.add(List.of(student.getId(), tb, getRgb()));
                soTinChi.add(List.of(student.getId(), tcttl, getRgb()));
            }
        }

        model.addAttribute("diemTongket", diemTongket);
        model.addAttribute("soTinChi", soTinChi);

//        System.out.println("\nThống kê điểm trung bình tích lũy");
//        diemTongket.forEach(System.out::println);
//        System.out.println("\n");
//
//        System.out.println("\nThống kê tổng số tín chỉ tích lũy");
//        soTinChi.forEach(System.out::println);
//        System.out.println("\n");

        // ===================================== Thống kê sv giỏi ===========================================

        List<List<Object>> svGioi = new ArrayList<>();

        for(List<Object> l: diemTongket){
            for(int i=0;i<l.size();i++){
                if(l.get(i) instanceof Double){
                    if((Double)(l.get(i)) > 8.0){
                        svGioi.add(List.of(l.get(i-1), l.get(i), getRgb()));
                    }
                }
            }
        }

        model.addAttribute("svGioi", svGioi);

//        System.out.println("\n");
//        svGioi.stream().forEach(System.out::println);
//        System.out.println("\n");


        return "sidebar/thongke";
    }

    public String getRgb() {
        Random random = new Random();

        float hue = random.nextFloat();
        float saturation = (random.nextInt(2000) + 1000) / 10000f;
        float luminance = 0.9f;
        Color color = Color.getHSBColor(hue, saturation, luminance);
        return  '#' + Integer.toHexString(color.getRGB() & 0xffffff | 0x1000000).substring(1);
    }
}
