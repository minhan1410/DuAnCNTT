package com.example.demo.controller;

import com.example.demo.model.Specialized;
import com.example.demo.model.User;
import com.example.demo.repository.SpecializedRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;


@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpecializedRepository specializedRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

//    @GetMapping(value = {"/"})
//    public String homepage() {
//        return "home";
//    }

    @GetMapping(value = {"/","/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/doimatkhau"})
    public String doimatkhau() {
        return "doimatkhau";
    }

    @PostMapping(value = {"/doimatkhau"})
    public String doimatkhau(Model model, Principal principal,@RequestParam("password") String password){
        User user = (User) ((Authentication) principal).getPrincipal();
        model.addAttribute("user", user);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncode = passwordEncoder.encode(password);

        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            model.addAttribute("mess","Mật khẩu trùng với mật khẩu cũ");
        }else{
            model.addAttribute("mess","Đổi mật khẩu thành công");
            user.setPassword(passwordEncode);
            userRepository.save(user);
        }

        return doimatkhau();
    }

//    @GetMapping("/registrationSpecialized")
//    public String registrationSpecialized(Model model) {
//        model.addAttribute("specialized", new Specialized());
//        return "registration/registrationSpecialized";
//    }
//
//    @PostMapping("/registrationSpecialized")
//    public String registrationSpecialized(@ModelAttribute Specialized specialized, Model model) {
//        if (specializedRepository.findByName(specialized.getName()) != null) {
//            model.addAttribute("mess", "specialized name unique");
//            return "registration/registrationSpecialized";
//        }
//
//        if (!specializedRepository.findById(specialized.getId()).isEmpty()) {
//            model.addAttribute("mess", "specialized id unique");
//            return "registration/registrationSpecialized";
//        }
//        specializedRepository.save(specialized);
//        return "home";
//    }
//
//    @GetMapping("/registrationUser")
//    public String registrationUser(Model model) {
//        model.addAttribute("user", new User());
//        return "registration/registrationUser";
//    }
//
//    @PostMapping("/registrationUser")
//    public String registrationUser(@ModelAttribute User newUser) {
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
//        System.out.printf("\n\n%s\n\n", newUser);
//
//        if (userRepository.findByUsername(newUser.getUsername()) != null) {
//            return "registration/registrationUser";
//        }
//        userRepository.save(newUser);
//        return "redirect:/";
//    }
//
//    @GetMapping("/registrationTeacher")
//    public String registrationTeacher(Model model) {
//        model.addAttribute("listSpecialized", specializedRepository.findAll());
//        return "registration/registrationTeacher";
//    }
}

//https://openplanning.net/11705/tao-ung-dung-login-voi-spring-boot-spring-security-jpa#a13946602
//https://techmaster.vn/posts/36586/spring-security-authorization#3-user---role---post
//https://loda.me/articles/sb9-gii-thch-cch-thymeleaf-vn-hnh-expression-demo-full#79d0540ecdf74fdc90639a81e7e86a49
//https://loda.me/articles/sb13-chi-tit-spring-boot-thymeleaf-mysql-i18n-web-demo
//https://loda.me/articles/ss-huong-dan-spring-security-jpa-hibernate#569f55c47e1c4c029a0e93a6ab5f3632
