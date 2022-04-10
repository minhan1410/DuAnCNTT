package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

// không biết bỏ được cột id
//@Entity
@Table(name = "sinhvien_diem")
@Data
public class StudentPoint {
    @Column(name = "MaSV")
    private String studentId;

    @Column(name = "MaMon")
    private String subjectId;

    @Column(name = "DiemQuaTrinh")
    private String diemQuaTrinh;

    @Column(name = "DiemCuoiKy")
    private String diemCuoiKy;

    @Column(name = "DiemTongke")
    private String diemTongket;
}