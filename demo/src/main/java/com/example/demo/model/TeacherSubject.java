package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "giaovien_monhoc")
@Data
public class TeacherSubject {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "MaGV", nullable = false, length = 6)
    private String teacherId;

    @Column(name = "MaMon", nullable = false, length = 6)
    private String subjectId;
}
