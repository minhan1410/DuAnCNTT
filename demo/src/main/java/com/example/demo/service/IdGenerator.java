package com.example.demo.service;

import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdGenerator {
    private static int idStudent;
    private static int idTeacher;

    @Autowired
    public static StudentRepository studentRepository;

    public static String getIdSV() {
        if(studentRepository.findAll().size() != 0){
            idStudent = Integer.parseInt(studentRepository.maxStudentId().substring(1));
        }
        return String.format("A%05d", ++idStudent);
    }

    public static String getIdGV() {
        return String.format("B%05d", ++idTeacher);
    }
}