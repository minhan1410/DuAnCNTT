package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.StudentPoint;
import com.example.demo.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentPointRepository extends JpaRepository<StudentPoint, Long> {
    @Override
    Optional<StudentPoint> findById(Long id);

    @Query("select distinct b " +
            "from StudentPoint a join Subject b on a.subjectId = b.id " +
            "where a.studentId like :studentId")
    Set<Subject> findSubjectsByStudentId(@Param("studentId") String studentId);

    @Query("select distinct b " +
            "from StudentPoint a join Student b on a.studentId = b.id " +
            "where a.subjectId like :subjectId")
    Set<Student> findStudentsBySubjectId(@Param("subjectId") String subjectId);

    @Query("select distinct a " +
            "from StudentPoint a join Student b on a.studentId = b.id " +
            "where a.studentId like :studentId")
    List<StudentPoint> findStudentPointByStudentId(@Param("studentId") String studentId);

    @Query("select a from StudentPoint a where a.studentId like :studentId and a.subjectId like :subjectId")
    StudentPoint findStudentPointByStudentIdAndSubjectId(@Param("studentId") String studentId,@Param("subjectId") String subjectId);
}
