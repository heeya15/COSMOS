-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: i6e103.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `study_type`
--

DROP TABLE IF EXISTS `study_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_type` (
  `studytype_no` int NOT NULL AUTO_INCREMENT,
  `studytype_name` varchar(50) NOT NULL,
  PRIMARY KEY (`studytype_no`),
  UNIQUE KEY `UKfrvl26llbj96jm1dj5dqtcqsn` (`studytype_name`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_type`
--

LOCK TABLES `study_type` WRITE;
/*!40000 ALTER TABLE `study_type` DISABLE KEYS */;
INSERT INTO `study_type` VALUES (25,'AI'),(22,'Android'),(16,'AngularJS'),(29,'Arduino'),(21,'Backend'),(27,'BigData'),(26,'BlockChain'),(2,'C'),(5,'C#'),(3,'C++'),(18,'CS'),(10,'CSS'),(19,'Database'),(17,'Django'),(20,'Frontend'),(9,'HTML'),(23,'iOS'),(28,'IoT'),(1,'Java'),(7,'Javascript'),(11,'Kotlin'),(24,'Mobile'),(6,'PHP'),(4,'Python'),(30,'RaspberryPi'),(14,'React'),(8,'RUBY'),(13,'Spring'),(31,'Study With Me'),(12,'Swift'),(15,'Vuejs'),(32,'기타');
/*!40000 ALTER TABLE `study_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:20
