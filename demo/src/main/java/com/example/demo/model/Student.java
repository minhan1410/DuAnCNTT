package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

// run bị lặp 3 trường sau trường user_id trong mysql
@Entity
@Table(name = "sinhvien")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "MaSV", nullable = false, length = 6)
    private String id;

    @Column(name = "MaCN")
    private String ChuyenNganhId;

    @Column(name = "GVCN")
    private String giaoVienId;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "user_id")
    private Long userId;

}
