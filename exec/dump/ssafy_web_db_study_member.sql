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
-- Table structure for table `study_member`
--

DROP TABLE IF EXISTS `study_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_member` (
  `studymember_no` bigint NOT NULL AUTO_INCREMENT,
  `attendance` bit(1) DEFAULT b'0',
  `authority` bit(1) DEFAULT b'0',
  `leader` bit(1) DEFAULT b'0',
  `score` int NOT NULL DEFAULT '0',
  `studytime` int NOT NULL DEFAULT '0',
  `study_no` bigint DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`studymember_no`),
  KEY `FK5ahg0e8wud7si7o4v74krktsa` (`study_no`),
  KEY `FKdkjhe6vrqdbg9meu09nqdwd5e` (`user_id`),
  CONSTRAINT `FK5ahg0e8wud7si7o4v74krktsa` FOREIGN KEY (`study_no`) REFERENCES `study` (`study_no`) ON DELETE CASCADE,
  CONSTRAINT `FKdkjhe6vrqdbg9meu09nqdwd5e` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_member`
--

LOCK TABLES `study_member` WRITE;
/*!40000 ALTER TABLE `study_member` DISABLE KEYS */;
INSERT INTO `study_member` VALUES (1,_binary '',_binary '',_binary '',0,0,1,'yoonjung12'),(2,_binary '\0',_binary '',_binary '',0,0,2,'seulgi1029'),(3,_binary '\0',_binary '',_binary '',0,0,3,'polyjjang1'),(4,_binary '\0',_binary '',_binary '',0,0,4,'gmldi135'),(6,_binary '',_binary '',_binary '',0,0,6,'heeya1008'),(7,_binary '\0',_binary '',_binary '',0,0,7,'seulgi1029'),(8,_binary '',_binary '',_binary '',0,0,8,'seulgi1029'),(9,_binary '\0',_binary '',_binary '',0,0,9,'heeya1008'),(11,_binary '\0',_binary '',_binary '',0,0,11,'kiseul1004'),(12,_binary '\0',_binary '',_binary '',0,0,12,'eunwoo1029'),(13,_binary '\0',_binary '',_binary '',2,0,13,'park1541'),(14,_binary '\0',_binary '',_binary '',0,0,14,'gmldi135'),(15,_binary '\0',_binary '\0',_binary '\0',2,0,12,'seulgi1029'),(16,_binary '\0',_binary '',_binary '',0,0,15,'sangsu123'),(18,_binary '\0',_binary '\0',_binary '\0',7,0,8,'eunwoo1029'),(23,_binary '\0',_binary '',_binary '',0,0,20,'park1541'),(24,_binary '\0',_binary '\0',_binary '\0',0,0,14,'v951230'),(25,_binary '',_binary '',_binary '',0,0,21,'heeya1008'),(26,_binary '\0',_binary '\0',_binary '\0',1,0,13,'do99999'),(28,_binary '\0',_binary '\0',_binary '\0',0,0,6,'park1541'),(29,_binary '\0',_binary '',_binary '',0,0,22,'billy'),(31,_binary '',_binary '',_binary '',0,0,24,'yoonjung12'),(34,_binary '\0',_binary '\0',_binary '\0',0,0,1,'do99999'),(35,_binary '\0',_binary '\0',_binary '\0',0,0,1,'gmldi135'),(38,_binary '\0',_binary '\0',_binary '\0',0,0,1,'polyjjang1'),(39,_binary '\0',_binary '\0',_binary '\0',0,0,1,'seulgi1029'),(53,_binary '\0',_binary '',_binary '',0,0,30,'kiseul1004'),(54,_binary '\0',_binary '\0',_binary '\0',0,0,7,'eunwoo1004'),(55,_binary '\0',_binary '\0',_binary '\0',0,0,2,'billy'),(56,_binary '\0',_binary '',_binary '',0,0,31,'seulgi1029'),(57,_binary '\0',_binary '\0',_binary '\0',7,0,31,'yoonjung12'),(58,_binary '\0',_binary '\0',_binary '\0',2,0,31,'heeya1008'),(63,_binary '\0',_binary '\0',_binary '\0',1,0,31,'gmldi135');
/*!40000 ALTER TABLE `study_member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:27
