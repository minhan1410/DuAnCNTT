package com.example.demo.repository;

import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,String> {
    Optional<Subject> findById(String id);

    Subject findByName(String name);
}
