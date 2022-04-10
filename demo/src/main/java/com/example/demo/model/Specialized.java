package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chuyennganh")
@Data
public class Specialized {
    @Id
    @Column(name = "MaCN", nullable = false, length = 6)
    private String id;

    @Column(name = "TenCN")
    private String name;
}
