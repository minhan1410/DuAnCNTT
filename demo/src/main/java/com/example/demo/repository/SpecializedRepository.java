package com.example.demo.repository;

import com.example.demo.model.Specialized;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializedRepository extends JpaRepository<Specialized,String> {
    Specialized findByName(String name);

    Optional<Specialized> findById(String id);
}
