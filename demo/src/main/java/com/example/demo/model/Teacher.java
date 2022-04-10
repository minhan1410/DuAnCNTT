package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "giaovien")
@Data
public class Teacher {
    @Id
    @Column(name = "MaGV", nullable = false, length = 6)
    private String id;

    @Column(name = "TenGV")
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

    @Column(name = "user_id")
    private String userId;
}
