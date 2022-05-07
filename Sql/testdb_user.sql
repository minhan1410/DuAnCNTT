CREATE DATABASE  IF NOT EXISTS `testdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `testdb`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	8.0.29

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
  `avatar` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `dien_thoai` varchar(10) DEFAULT NULL,
  `gioi_tinh` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (17,'$2a$10$EtQnWITrI2dIz7PmRUUAqe6Ay/0VyYuu4y5R7NWoNBIRbE1Y7MGKi','ROLE_Student','minhan','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','111','https://afamilycdn.com/150157425591193600/2020/3/21/batch6615a53a-51f3-336e-b7ba-ed456e3c89ca-15848021328151422868524.jpg','2001-10-14','0965240637','Nam'),(18,'$2a$10$VqjIUBFl7aPOfObk6zPcj.reNUEpJjIPWeWRZiG5ghMxPQ3H7UUSO','ROLE_Teacher','minhan1','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','222','https://cdn.vntrip.vn/cam-nang/wp-content/uploads/2020/10/bi-quyet-chup-anh-dep-ngay-tai-nha-4.jpg','2022-04-22','0123456789','Nam'),(19,'$2a$10$Jd.Hqxwr7ZW5zzvBj.K8ROTw3Yt2DZGQ7GnYd5Ru6zB8z9T1YB2Me','ROLE_Teacher','vietanh','Nguyễn Việt Anh','Thường Tín','vietanh@gmail.com','111','http://studiochupanhdep.com//Upload/Images/Album/a','2022-04-22','0123456789','Nam'),(20,'$2a$10$uAtNjCize1mGubd/nWaYcO.kVCllQ/gWj4yu9ub99b8udC9hxSuQC','ROLE_Student','vietanh1','Nguyễn Việt Anh','Thường Tín','vietanh1@gmail.com','123','https://luv.vn/wp-content/uploads/2021/08/hinh-anh-gai-xinh-73.jpg','2022-04-25','0123456789','Nam'),(21,'$2a$10$PQgn6lObRNc3HKjy7qGK9OXSTvD8pWhfC39k8fJn6Y/YYwqwbdH/i','ROLE_TrainingDepartment','minhan2','Nguyễn Minh An','Thường Tín','minhan14102001@gmail.com','111','https://keomoi.com/wp-content/uploads/2019/05/anh-gai-dep-de-thuong-hinh-6.jpg','2022-05-03','0123456789','Nam'),(30,'$2a$10$JHzih./6cUV8EKHWiFrhSeFgfDvw9Y2h3Zn4fXh90lLdl7AhAa8Yi','ROLE_Student','A00003','Trần Duy Anh Tú','Yên Lãng','tudu@gmail.com','444','https://hinhgaixinh.com/wp-content/uploads/2021/11/hinh-anh-gai-xinh-deo-mat-kinh-dep-nhat-the-gioi.jpg','2022-05-07','0123456789','Nam');
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

-- Dump completed on 2022-05-08  2:48:26
