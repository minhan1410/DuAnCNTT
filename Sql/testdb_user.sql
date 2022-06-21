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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `Ten` varchar(50) DEFAULT NULL,
  `Diachi` varchar(100) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `CMND` varchar(12) DEFAULT NULL,
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dien_thoai` varchar(10) DEFAULT NULL,
  `gioi_tinh` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (34,'$2a$10$ErAwMRoZXFQTkTVFiboRdeLrY8cCstX4V/rL.YiLsuXSHpHVcMzey','ROLE_Admin','admin','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','111','https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-73.jpg','2022-06-16','0123456789','Nam'),(35,'$2a$10$IGmvKIfk.OUvcA6TSjtcWODo1g6l0mXYke9X3ges1WX2zpjsTAw9i','ROLE_TrainingDepartment','B00002','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','111','https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-73.jpg','2022-06-16','0123456789','Nam'),(36,'$2a$10$F5ppdAeMFMvK/PtN/YNvgOsxHdumv9P4xIRnEwY7HK5nl425VO9BW','ROLE_Teacher','B00003','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','444','https://digitalphoto.com.vn/wp-content/uploads/2018/06/Bi-kip-chup-anh-mytour-2.jpg','2022-06-16','0123456789','Nam'),(37,'$2a$10$Yd3IH/y3I9PA1cJmmeQ5keuH8HM9BgOXgLRTVXvVwJEdarLYXmj5u','ROLE_Teacher','B00004','Nguyễn Việt Anh','Thường Tín','vietanh@gmail.com','222','https://phongvu.vn/cong-nghe/wp-content/uploads/2019/09/img_7866.jpg','2022-06-16','0123456789','Nam'),(38,'$2a$10$TTWmjqPOcWQKCNzccl8cIOjTYbo6w9iKNbr.HizZPrPf8XbPgR/dq','ROLE_Teacher','B00005','Bùi Văn Hưng','Thường Tín','vanhung@gmail.com','123','https://aphoto.vn/wp-content/uploads/2019/07/anh-chan-dung-nghe-thuat-top-aphoto5.jpg','2022-06-16','0123456789','Nam'),(39,'$2a$10$UU88W0JnsXgM9wSV5J1MN.2xO5EjkCia53ZHzlvnw/0HTI/FD5hYq','ROLE_Student','A00001','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','444','http://static.ybox.vn/2018/10/3/1539164880071-750x750.hbrtbb.jpg.jpg','2022-06-16','0123456789','Nam'),(40,'$2a$10$EN1kR/AFlr03ZvGyNdTmQeMpMSUbk9aM.GUpp18r99rG/Tg7oip7.','ROLE_Student','A00002','Trần Duy Anh Tú','Yên Lãng','tudu@gmail.com','123','https://phongkhammayo.vn/nhung-kieu-chup-anh-dep-nhat/imager_61995.jpg','2022-06-16','0123456789','Nam'),(41,'$2a$10$Gz0H0LMwt8UkIKAXC4idv.OWIO7p4ZAXXpmWBx45nfuw/AEssPgfa','ROLE_Teacher','B00006','Trần Duy Anh Tú','Yên Lãng','tudu@gmail.com','123','https://dotobjyajpegd.cloudfront.net/photo/5d54e6c7250d29257ece0b4d','2022-06-16','0123456789','Nam');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
