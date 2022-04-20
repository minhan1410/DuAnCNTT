package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lop")
@Data
public class Class {
    @Id
    @Column(name = "ma_lop", nullable = false, length = 6)
    private String id;

    @Column(name = "phong")
    private String phongHoc;

    @Column(name = "ma_mon", nullable = false, length = 6)
    private String subjectId;
}
