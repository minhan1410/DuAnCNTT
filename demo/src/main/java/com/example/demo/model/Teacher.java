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

    @Column(name = "MaCN")
    private String ChuyenNganhId;

    @Column(name = "user_id")   
    private Long userId;
}
