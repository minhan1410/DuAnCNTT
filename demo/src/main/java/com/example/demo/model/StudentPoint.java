package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sinhvien_diem")
@Data
public class StudentPoint {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "MaSV")
    private String studentId;

    @Column(name = "ma_mon")
    private String subjectId;

    @Column(name = "diem_qua_trinh", columnDefinition = "0.0")
    private Double diemQuaTrinh = 0.0;

    @Column(name = "diem_cuoi_ky", columnDefinition = "0.0")
    private Double diemCuoiKy = 0.0;

    @Column(name = "diem_tong_ket", columnDefinition = "0.0")
    private Double diemTongket = 0.0;

    @Column(name = "tinh_trang", columnDefinition = "Bình thường")
    private String tinhTrang = "Bình thường";
}