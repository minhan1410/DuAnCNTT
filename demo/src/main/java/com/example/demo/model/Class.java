package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lop")
@Data
public class Class {
    @Id
    @Column(name = "MaLop", nullable = false, length = 6)
    private String id;

    @Column(name = "TenLop")
    private String name;
}
