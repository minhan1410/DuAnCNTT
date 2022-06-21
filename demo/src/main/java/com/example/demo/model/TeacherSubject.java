package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
