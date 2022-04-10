package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "monhoc")
@Data
public class Subject {
    @Id
    @Column(name = "MaMon", nullable = false, length = 6)
    private String id;

    @Column(name = "TenMon")
    private String name;

    @Column(name = "SoTC")
    private String soTinChi;

    @Column(name = "MaCN")
    private String maChuyenNganh;

    @Column(name = "Ca")
    private String ca;

    @Column(name = "Thu")
    private String thu;

    @Column(name = "NgayThi")
    private String ngayThi;

    @Column(name = "CaThi")
    private String caThi;

    @Column(name = "GiaTien")
    private String giaTien;
}
