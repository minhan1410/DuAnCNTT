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

    @Column(name = "diem_qua_trinh")
    private Integer diemQuaTrinh;

    @Column(name = "diem_cuoi_ky")
    private Integer diemCuoiKy;

    @Column(name = "diem_tong_ket")
    private Integer diemTongket;
}