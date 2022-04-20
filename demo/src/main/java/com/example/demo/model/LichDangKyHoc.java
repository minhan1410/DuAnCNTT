package com.example.demo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lichdangkyhoc")
@Data
public class LichDangKyHoc {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ma_mon", nullable = false, length = 6)
    private String subjectId;

    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayKetThuc;
}
