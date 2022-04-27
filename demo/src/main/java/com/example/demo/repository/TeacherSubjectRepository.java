package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.StudentPoint;
import com.example.demo.model.Subject;
import com.example.demo.model.TeacherSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {
    @Query("select distinct subject " +
            "from TeacherSubject t join Subject subject on t.teacherId = subject.teacherId " +
            "where t.teacherId like :teacherId")
    Set<Subject> findSubjectsByTeacherId(@Param("teacherId") String teacherId);

    @Query("select distinct student " +
            "from StudentPoint studentP join Subject subject on studentP.subjectId = subject.id " +
            "join TeacherSubject teacherS on teacherS.teacherId = subject.teacherId " +
            "join Student student on studentP.studentId = student.id " +
            "where teacherS.teacherId like :teacherId")
    Set<Student> findStudentsByTeacherId(@Param("teacherId") String teacherId);


    @Query("select distinct student " +
            "from StudentPoint studentP join Subject subject on studentP.subjectId = subject.id " +
            "join TeacherSubject teacherS on teacherS.teacherId = subject.teacherId " +
            "join Student student on studentP.studentId = student.id " +
            "where teacherS.teacherId like :teacherId and teacherS.subjectId like :subjectId")
    Set<Student> findStudentsByTeacherIdAndSubjectId(@Param("teacherId") String teacherId, @Param("subjectId") String subjectId);
}
