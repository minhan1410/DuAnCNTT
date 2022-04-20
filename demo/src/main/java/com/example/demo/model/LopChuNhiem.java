package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lop_chunhiem")
@Data
public class LopChuNhiem {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "TenLop", nullable = false)
    private String name;

    @Column(name = "MaGV", nullable = false, length = 6)
    private String teacherId;

    @Column(name = "MaSV", nullable = false, length = 6)
    private String studentId;

    @Column(name = "MaCN", nullable = false, length = 6, unique = true)
    private String specializedId;
}
