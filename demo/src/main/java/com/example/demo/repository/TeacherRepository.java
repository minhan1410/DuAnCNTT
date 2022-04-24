package com.example.demo.repository;

import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Optional<Teacher> findById(String id);

    Teacher findByUserId(Long userId);
}
