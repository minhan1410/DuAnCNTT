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
-- Table structure for table `monhoc`
--

DROP TABLE IF EXISTS `monhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monhoc` (
  `ma_mon` varchar(6) NOT NULL,
  `magv` varchar(6) NOT NULL,
  `macn` varchar(10) NOT NULL,
  `ten_mon` varchar(255) NOT NULL,
  `ten_lop` varchar(255) NOT NULL,
  `sotc` int NOT NULL,
  `phong_hoc` varchar(255) DEFAULT NULL,
  `ca` varchar(5) DEFAULT NULL,
  `thu` varchar(10) DEFAULT NULL,
  `ngay_thi` varchar(255) DEFAULT NULL,
  `ca_thi` varchar(255) DEFAULT NULL,
  `gia_tien` bigint DEFAULT NULL,
  `ngay_bat_dau` date DEFAULT NULL,
  `so_luong_sv` int DEFAULT NULL,
  `so_luong_sv_dk` int DEFAULT '0',
  PRIMARY KEY (`ma_mon`),
  KEY `fk_ChuyenNganh_MonHoc_idx` (`macn`),
  KEY `fk_ChuyenNganh_gv_idx` (`magv`),
  CONSTRAINT `fk_ChuyenNganh_MonHoc` FOREIGN KEY (`macn`) REFERENCES `chuyennganh` (`MaCN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monhoc`
--

LOCK TABLES `monhoc` WRITE;
/*!40000 ALTER TABLE `monhoc` DISABLE KEYS */;
INSERT INTO `monhoc` VALUES ('ctdl','B00004','TT','Cấu trúc dữ liệu','ctdl.1',3,'A704','1-2','3','2022-06-28 00:00:00','4',NULL,'2022-06-16',80,1),('gt1','B00006','TI','Giải tích 1','Giải tích sáng',2,'BoMon','1-5','Cn','2022-06-16 23:19:33.127',NULL,NULL,'2022-06-16',700,0),('iot','B00004','TT','IOT','IOT.1',3,'A704','3-5','5','2022-06-16 22:38:07.367',NULL,NULL,'2022-06-16',100,1),('java','B00003','TT','Java core','JavaCore.1',3,'A703','1-2','2','2022-06-20 00:00:00','2',NULL,'2022-06-16',70,2),('java2','B00003','TT','JavaFX','JavaFX.2',3,'A703','3-5','3','2022-06-16 22:35:35.239',NULL,NULL,'2022-06-16',70,0),('java3','B00003','TT','Spring boot','SpringBoot.3',3,'A703','6-10','4','2022-06-16 22:36:34.863',NULL,NULL,'2022-06-16',50,1),('oop','B00005','TT','Lập trình hướng đối tượng','OOP1',3,'A708','3-5','7','2022-07-02 00:00:00','1',NULL,'2022-06-16',60,1);
/*!40000 ALTER TABLE `monhoc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 23:23:32
