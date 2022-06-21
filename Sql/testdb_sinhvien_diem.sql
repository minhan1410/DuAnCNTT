CREATE DATABASE  IF NOT EXISTS `testdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `testdb`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sinhvien_diem`
--

DROP TABLE IF EXISTS `sinhvien_diem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinhvien_diem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `MaSV` varchar(6) DEFAULT NULL,
  `ma_mon` varchar(6) DEFAULT NULL,
  `diem_qua_trinh` double DEFAULT '0',
  `diem_cuoi_ky` double DEFAULT '0',
  `diem_tong_ket` double DEFAULT '0',
  `tinh_trang` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'Bình thường',
  PRIMARY KEY (`id`),
  KEY `FK_SinhVien_Diem_MonHoc` (`ma_mon`),
  KEY `FK_SinhVien_Diem_SinhVien` (`MaSV`),
  CONSTRAINT `FK_SinhVien_Diem_MonHoc` FOREIGN KEY (`ma_mon`) REFERENCES `monhoc` (`ma_mon`),
  CONSTRAINT `FK_SinhVien_Diem_SinhVien` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien_diem`
--

LOCK TABLES `sinhvien_diem` WRITE;
/*!40000 ALTER TABLE `sinhvien_diem` DISABLE KEYS */;
INSERT INTO `sinhvien_diem` VALUES (75,'A00001','iot',0,0,0,'Bình thường'),(76,'A00001','java',9,8,8.4,'Bình thường'),(77,'A00002','java3',0,0,0,'Bình thường'),(78,'A00002','java',7,9,8.2,'Bình thường'),(79,'A00002','oop',0,0,0,'Bình thường'),(80,'A00001','ctdl',0,0,0,'Bình thường');
/*!40000 ALTER TABLE `sinhvien_diem` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 23:23:31
