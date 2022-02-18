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
-- Table structure for table `study_manage`
--

DROP TABLE IF EXISTS `study_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_manage` (
  `study_manage_no` bigint NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `studymanage_notice` varchar(2000) DEFAULT NULL,
  `study_no` bigint NOT NULL,
  PRIMARY KEY (`study_manage_no`,`study_no`),
  KEY `FKix5ufn1yxf6bhpsagjiasi1yx` (`study_no`),
  CONSTRAINT `FKix5ufn1yxf6bhpsagjiasi1yx` FOREIGN KEY (`study_no`) REFERENCES `study` (`study_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_manage`
--

LOCK TABLES `study_manage` WRITE;
/*!40000 ALTER TABLE `study_manage` DISABLE KEYS */;
INSERT INTO `study_manage` VALUES (1,'2022-02-14 06:57:38','<<매주 월요일 8시 시작하는 스프링 스터디 입니다. 일정은 스터디원의 의견을 반영하여 짤 계획입니다.>>\n\n?이번주는 지난주에 했던 내용을 복습하는 것으로 진도 나가겠습니다!! ',1),(1,'2022-02-14 07:15:55','매주 월 수 7시 스터디 진행합니다\n지각, 결석시 벌점 각 -5, -10점, 총합이 30점이 되면 강제퇴출 입니다',12),(1,'2022-02-14 07:45:43','무조건 열심히 공부해주세요!!!\n무조건 열심히 공부해주세요!!!\n무조건 열심히 공부해주세요!!!',14),(1,'2022-02-17 15:32:44','이번주는 백준 2178번 풀겠습니다!',31);
/*!40000 ALTER TABLE `study_manage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:23
