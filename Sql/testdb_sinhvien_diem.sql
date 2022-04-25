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
  `diem_qua_trinh` int DEFAULT NULL,
  `diem_cuoi_ky` int DEFAULT NULL,
  `diem_tong_ket` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_SinhVien_Diem_MonHoc` (`ma_mon`),
  KEY `FK_SinhVien_Diem_SinhVien` (`MaSV`),
  CONSTRAINT `FK_SinhVien_Diem_MonHoc` FOREIGN KEY (`ma_mon`) REFERENCES `monhoc` (`ma_mon`),
  CONSTRAINT `FK_SinhVien_Diem_SinhVien` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinhvien_diem`
--

LOCK TABLES `sinhvien_diem` WRITE;
/*!40000 ALTER TABLE `sinhvien_diem` DISABLE KEYS */;
INSERT INTO `sinhvien_diem` VALUES (41,'A00017','CS131',NULL,NULL,NULL),(42,'A00020','CS131',NULL,NULL,NULL),(43,'A00017','FA131',NULL,NULL,NULL),(45,'A00017','CS102',NULL,NULL,NULL),(46,'A00020','FA131',NULL,NULL,NULL);
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

-- Dump completed on 2022-04-25 13:59:35
