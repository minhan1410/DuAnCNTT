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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "MaSV", nullable = false, length = 6)
    private String id;

    @Column(name = "TenSV")
    private String name;

    @Column(name = "Diachi")
    private String address;

    @Column(name = "Email")
    private String email;

    @Column(name = "DienThoai", length = 10)
    private String phoneNumber;

    @Column(name = "CMND")
    private String peopleID;

    @Column(name = "NgaySinh")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "GioiTinh",length = 4)
    private String sex;

    @Column(name = "MaCN")
    private String ChuyenNganhId;

    @Column(name = "GVCN")
    private String giaoVienId;

    @Column(name = "user_id")
    private String userId;

}
