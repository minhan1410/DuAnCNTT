-- MySQL Script generated by MySQL Workbench
-- Wed Apr 20 23:49:07 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema testdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `testdb` ;

-- -----------------------------------------------------
-- Schema testdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `testdb` ;

-- -----------------------------------------------------
-- Table `testdb`.`chuyennganh`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`chuyennganh` ;

CREATE TABLE IF NOT EXISTS `testdb`.`chuyennganh` (
  `MaCN` VARCHAR(10) NOT NULL,
  `TenCN` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`MaCN`),
  UNIQUE INDEX `MaCN_UNIQUE` (`MaCN` ASC) VISIBLE,
  UNIQUE INDEX `TenCN_UNIQUE` (`TenCN` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`user` ;

CREATE TABLE IF NOT EXISTS `testdb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `permissions` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NOT NULL,
  `Ten` VARCHAR(50) NULL DEFAULT NULL,
  `Diachi` VARCHAR(100) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `CMND` VARCHAR(12) NULL DEFAULT NULL,
  `avatar` VARCHAR(1000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `ngay_sinh` DATE NULL DEFAULT NULL,
  `dien_thoai` VARCHAR(10) NULL DEFAULT NULL,
  `gioi_tinh` VARCHAR(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`giaovien`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`giaovien` ;

CREATE TABLE IF NOT EXISTS `testdb`.`giaovien` (
  `MaGV` VARCHAR(6) NOT NULL,
  `MaCN` VARCHAR(10) NULL DEFAULT NULL,
  `GVCN` TINYINT(1) NULL DEFAULT NULL,
  `TrangThai` INT NULL DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`MaGV`),
  UNIQUE INDEX `MaGV_UNIQUE` (`MaGV` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `MaCN_UNIQUE` (`MaCN` ASC) VISIBLE,
  INDEX `FK_ChuyenNganh_GiaoVien` (`MaCN` ASC) VISIBLE,
  INDEX `fk_giaovien_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_ChuyenNganh_GiaoVien`
    FOREIGN KEY (`MaCN`)
    REFERENCES `testdb`.`chuyennganh` (`MaCN`),
  CONSTRAINT `fk_giaovien_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `testdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`monhoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`monhoc` ;

CREATE TABLE IF NOT EXISTS `testdb`.`monhoc` (
  `ma_mon` VARCHAR(6) NOT NULL,
  `magv` VARCHAR(6) NOT NULL,
  `ten_mon` VARCHAR(255) NULL DEFAULT NULL,
  `sotc` INT NULL DEFAULT NULL,
  `macn` VARCHAR(10) NULL DEFAULT NULL,
  `ca` VARCHAR(5) NULL DEFAULT NULL,
  `thu` VARCHAR(10) NULL DEFAULT NULL,
  `ngay_thi` VARCHAR(255) NULL DEFAULT NULL,
  `ca_thi` VARCHAR(255) NULL DEFAULT NULL,
  `gia_tien` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ma_mon`),
  INDEX `fk_ChuyenNganh_MonHoc_idx` (`macn` ASC) VISIBLE,
  INDEX `fk_ChuyenNganh_gv_idx` (`magv` ASC) VISIBLE,
  CONSTRAINT `fk_ChuyenNganh_gv`
    FOREIGN KEY (`magv`)
    REFERENCES `testdb`.`giaovien` (`MaGV`),
  CONSTRAINT `fk_ChuyenNganh_MonHoc`
    FOREIGN KEY (`macn`)
    REFERENCES `testdb`.`chuyennganh` (`MaCN`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`giaovien_monhoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`giaovien_monhoc` ;

CREATE TABLE IF NOT EXISTS `testdb`.`giaovien_monhoc` (
  `id` BIGINT NOT NULL,
  `MaGV` VARCHAR(6) NULL DEFAULT NULL,
  `ma_mon` VARCHAR(6) NULL DEFAULT NULL,
  INDEX `FK_GiaoVien_MonHoc_MonHoc` (`ma_mon` ASC) VISIBLE,
  INDEX `FK_GiaoVien_MonHoc_Giaovien` (`MaGV` ASC) VISIBLE,
  CONSTRAINT `FK_GiaoVien_MonHoc_Giaovien`
    FOREIGN KEY (`MaGV`)
    REFERENCES `testdb`.`giaovien` (`MaGV`),
  CONSTRAINT `FK_GiaoVien_MonHoc_MonHoc`
    FOREIGN KEY (`ma_mon`)
    REFERENCES `testdb`.`monhoc` (`ma_mon`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`lichdangkyhoc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`lichdangkyhoc` ;

CREATE TABLE IF NOT EXISTS `testdb`.`lichdangkyhoc` (
  `id` BIGINT NOT NULL,
  `ma_mon` VARCHAR(6) NOT NULL,
  `ngay_bat_dau` DATE NULL DEFAULT NULL,
  `ngay_ket_thuc` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `dkh_mon_idx` (`ma_mon` ASC) VISIBLE,
  CONSTRAINT `dkh_mon`
    FOREIGN KEY (`ma_mon`)
    REFERENCES `testdb`.`monhoc` (`ma_mon`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`lop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`lop` ;

CREATE TABLE IF NOT EXISTS `testdb`.`lop` (
  `ma_lop` VARCHAR(6) NOT NULL,
  `ma_mon` VARCHAR(6) NOT NULL,
  `phong` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ma_lop`),
  INDEX `lop_mon_idx` (`ma_mon` ASC) VISIBLE,
  CONSTRAINT `lop_mon`
    FOREIGN KEY (`ma_mon`)
    REFERENCES `testdb`.`monhoc` (`ma_mon`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`lop_chunhiem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`lop_chunhiem` ;

CREATE TABLE IF NOT EXISTS `testdb`.`lop_chunhiem` (
  `id` VARCHAR(255) NOT NULL,
  `ten_lop` VARCHAR(255) NOT NULL,
  `MaGV` VARCHAR(6) NOT NULL,
  `MaSV` VARCHAR(6) NOT NULL,
  `MaCN` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Lop_ChuNhiem_ChuyenNganh` (`MaCN` ASC) VISIBLE,
  INDEX `FK_Lop_ChuNhiem_GV_idx` (`MaGV` ASC) VISIBLE,
  CONSTRAINT `FK_Lop_ChuNhiem_ChuyenNganh`
    FOREIGN KEY (`MaCN`)
    REFERENCES `testdb`.`chuyennganh` (`MaCN`),
  CONSTRAINT `FK_Lop_ChuNhiem_GV`
    FOREIGN KEY (`MaGV`)
    REFERENCES `testdb`.`giaovien` (`MaGV`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`sinhvien`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`sinhvien` ;

CREATE TABLE IF NOT EXISTS `testdb`.`sinhvien` (
  `MaSV` VARCHAR(6) NOT NULL,
  `MaCN` VARCHAR(10) NULL DEFAULT NULL,
  `GVCN` VARCHAR(10) NULL DEFAULT NULL,
  `trang_thai` VARCHAR(255) NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`MaSV`),
  UNIQUE INDEX `MaSV_UNIQUE` (`MaSV` ASC) VISIBLE,
  INDEX `FK_ChuyenNganh_SinhVien` (`MaCN` ASC) INVISIBLE,
  INDEX `fk_sinhvien_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_ChuyenNganh_SinhVien`
    FOREIGN KEY (`MaCN`)
    REFERENCES `testdb`.`chuyennganh` (`MaCN`),
  CONSTRAINT `fk_sinhvien_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `testdb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `testdb`.`sinhvien_diem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testdb`.`sinhvien_diem` ;

CREATE TABLE IF NOT EXISTS `testdb`.`sinhvien_diem` (
  `id` INT NOT NULL,
  `MaSV` VARCHAR(6) NULL DEFAULT NULL,
  `ma_mon` VARCHAR(6) NULL DEFAULT NULL,
  `diem_qua_trinh` INT NULL DEFAULT NULL,
  `diem_cuoi_ky` INT NULL DEFAULT NULL,
  `diem_tong_ket` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_SinhVien_Diem_MonHoc` (`ma_mon` ASC) VISIBLE,
  INDEX `FK_SinhVien_Diem_SinhVien` (`MaSV` ASC) VISIBLE,
  CONSTRAINT `FK_SinhVien_Diem_MonHoc`
    FOREIGN KEY (`ma_mon`)
    REFERENCES `testdb`.`monhoc` (`ma_mon`),
  CONSTRAINT `FK_SinhVien_Diem_SinhVien`
    FOREIGN KEY (`MaSV`)
    REFERENCES `testdb`.`sinhvien` (`MaSV`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
