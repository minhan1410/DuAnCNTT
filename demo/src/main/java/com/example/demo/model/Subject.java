package com.example.demo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "monhoc")
@Data
public class Subject {
    @Id
    @Column(name = "ma_mon", nullable = false, length = 6)
    private String id;

    @Column(name = "MaGV", nullable = false, length = 6)
    private String teacherId;

    @Column(name = "macn")
    private String maChuyenNganh;

    @Column(name = "ten_mon")
    private String name;

    @Column(name = "ten_lop")
    private String tenLop;

    @Column(name = "phong_hoc")
    private String phongHoc;

    @Column(name = "sotc")
    private Integer soTinChi;

    @Column(name = "ca")
    private String ca;

    @Column(name = "thu")
    private String thu;

    @Column(name = "ngay_thi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayThi = new Date();

    @Column(name = "ca_thi")
    private String caThi;

    @Column(name = "gia_tien")
    private Long giaTien;

    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau = new Date();

    @Column(name = "so_luong_sv")
    private Integer soLuongSv;

    @Column(name = "so_luong_sv_dk", columnDefinition = "0")
    private Integer soLuongSvDaDk = 0;
}
