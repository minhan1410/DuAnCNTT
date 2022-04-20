package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "ten_mon")
    private String name;

    @Column(name = "sotc")
    private Integer soTinChi;

    @Column(name = "macn")
    private String maChuyenNganh;

    @Column(name = "ca")
    private String ca;

    @Column(name = "thu")
    private String thu;

    @Column(name = "ngay_thi")
    private Date ngayThi;

    @Column(name = "ca_thi")
    private String caThi;

    @Column(name = "gia_tien")
    private Integer giaTien;
}
