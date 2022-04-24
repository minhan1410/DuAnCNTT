package com.example.demo.repository;

import com.example.demo.model.StudentPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPointRepository extends JpaRepository<StudentPoint, Long> {
}
