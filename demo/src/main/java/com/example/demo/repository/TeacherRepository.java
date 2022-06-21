package com.example.demo.repository;

import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    Optional<Teacher> findById(String id);

    Teacher findByUserId(Long userId);

    List<Teacher> findByGvChuNhiem(Boolean gvChuNhiem);

    @Query("select t from Teacher t join User u on t.userId = u.id where t.ChuyenNganhId = :chuyenNganhId and u.permissions = 'ROLE_Teacher' ")
    List<Teacher> findByChuyenNganhId(@Param("chuyenNganhId") String chuyenNganhId);

    @Query("select t from Teacher t join User u on t.userId = u.id where u.permissions = 'ROLE_Teacher' ")
    List<Teacher> findByGv();
}
