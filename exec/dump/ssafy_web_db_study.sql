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
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `study_no` bigint NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NULL DEFAULT NULL,
  `image` varchar(150) NOT NULL,
  `number_of_member` int NOT NULL DEFAULT '0',
  `study_name` varchar(100) DEFAULT NULL,
  `study_password` varchar(10) DEFAULT NULL,
  `study_rule` varchar(500) DEFAULT NULL,
  `total_member` int NOT NULL DEFAULT '0',
  `url` varchar(255) DEFAULT NULL,
  `studytype_no` int DEFAULT NULL,
  PRIMARY KEY (`study_no`),
  UNIQUE KEY `STUDYNAME_URL_UNIQUE` (`study_name`,`url`),
  KEY `FKp2bs3x7l60softjrn0ey07sno` (`studytype_no`),
  CONSTRAINT `FKp2bs3x7l60softjrn0ey07sno` FOREIGN KEY (`studytype_no`) REFERENCES `study_type` (`studytype_no`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (1,'2022-02-14 06:37:06','https://i.ibb.co/J2Jwsdc/spring.png',5,'스프링스터디','1234','스프링 스터디 같이해요! 인텔리제이로 함께합니다 ??',6,'https://i6e103.p.ssafy.io/springstudy',13),(2,'2022-02-14 06:37:46','https://i.ibb.co/3vdLtWV/C.png',2,'c# 스터디','1234','c# 스터디 만들었습니다 같이 하실 분!',6,'https://i6e103.p.ssafy.io/cosmosstudy',5),(3,'2022-02-14 06:37:55','https://i.ibb.co/QQ6TrHB/mysql.png',1,'MySQL 고수 스터디','1234','MySQL Workbench를 이용합니다.',6,'https://i6e103.p.ssafy.io/mysql',19),(4,'2022-02-14 06:40:41','https://i.ibb.co/jz2xT5n/css.webp',1,'CSS 고수들의 모임','1234','Visual Studio Code를 설치해주세요.',4,'https://i6e103.p.ssafy.io/css_style',10),(6,'2022-02-14 06:47:30','https://i.ibb.co/B6dP6Rh/study2.gif',2,'코딩테스트스터디','1234','코딩테스트를 위한 스터디입니다.\n매일 저녁 8시에서 10시까지 백준, 프로그래머스 문제를 스터디 방 공용 타이머를 통해 시간제한을 두면서 풀 것입니다. 열심히 참여해 주세요.',6,'https://i6e103.p.ssafy.io/codingtest',32),(7,'2022-02-14 06:51:32','https://i.ibb.co/Jpf8cjs/python.jpg',2,'파이썬 스터디','1234','파이썬 스터디 만들었어요! 같이합시다',5,'https://i6e103.p.ssafy.io/python',4),(8,'2022-02-14 06:54:21','https://i.ibb.co/Z200ZZR/ruby.jpg',2,'ruby 스터디','1234','ruby 스터디 같이하실 분 있나요> 많관부',5,'https://i6e103.p.ssafy.io/rubyruby',8),(9,'2022-02-14 06:55:15','https://i.ibb.co/YXrWmN9/react.jpg',1,'React스터디','1234','주 3일 월, 수, 금 저녁 8시에서 9시 30분 까지 1시간 30 정도 진행 됩니다.\n다들 시간에 맞춰 스터디 룸에 들어와 주세요! 시간을 잘 지킵시다!',6,'https://i6e103.p.ssafy.io/reactstudy',14),(10,'2022-02-14 06:57:33','https://i.ibb.co/TT3sVMd/java.png',0,'java를 job아라!','1234','java 스터디 매주 화 목 8시에 진행합니다.\n출첵 엄격히 진행',6,'https://i6e103.p.ssafy.io/java',1),(11,'2022-02-14 07:05:02','https://i.ibb.co/Tg2cF9M/php.jpg',1,'php 스터디','1234','php 스터디 출첵 엄격하게 요구합니다\n지각, 결석 3회 이상시 강퇴',6,'https://i6e103.p.ssafy.io/php',6),(12,'2022-02-14 07:08:00','https://i.ibb.co/YXrWmN9/react.jpg',2,'react 스터디','1234','react 스터디 오래 같이 하실 분 모집합니다!\n매주 월 수 7시 스터디 진행',6,'https://i6e103.p.ssafy.io/react',14),(13,'2022-02-14 07:09:17','https://i.ibb.co/47bWKZM/docker.png',2,'도커의신','1541','따라서 하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!',4,'https://i6e103.p.ssafy.io/docker',32),(14,'2022-02-14 07:22:27','https://i.ibb.co/B6dP6Rh/study2.gif',2,'열공','1234','무조건 열공입니다.',6,'https://i6e103.p.ssafy.io/studyhard',32),(15,'2022-02-14 07:30:45','https://i.ibb.co/PNzm2Cw/artificial-intelligence-4111582-1920.jpg',1,'인공지능스터디','1234','4차 산업혁명 기술중 하나인 인공지능 스터디에 들어와 주셔서 감사합니다.\n하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!',6,'https://i6e103.p.ssafy.io/ai',25),(20,'2022-02-14 07:39:45','https://i.ibb.co/CQmhf14/bigdata.jpg',1,'카운팅 데이터~ 밤하늘의 ~빅 데이터','1541','하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!',5,'https://i6e103.p.ssafy.io/countingstart',27),(21,'2022-02-14 07:51:15','https://i.ibb.co/B6dP6Rh/study2.gif',1,'CS스터디','1234','스터디를 진행하는 기간동안 개인 사유로 총 3회 결석 가능. 단, 일주일에 최대 1번만 결석 가능. 결석한 당일의 진도는 일요일 혹은 주말에 보충으로 진행해야 함. 결석을 원할 경우 전날에 미리 알려줘야 함.',5,'https://i6e103.p.ssafy.io/csstudy',32),(22,'2022-02-15 02:40:51','https://i.ibb.co/gg6hwsG/studywithme.jpg',1,'study with bill?','1234','study with me\nno 결석 no 지각\n아무 공부나 together',6,'https://i6e103.p.ssafy.io/billy',32),(24,'2022-02-15 15:32:42','https://i.ibb.co/0yXpH3X/dog.jpg',1,'면접스터디','1234','면접스터디 입니다. 상반기 취직을 목표로 하고 있습니다!',4,'https://i6e103.p.ssafy.io/interview',32),(30,'2022-02-17 04:30:27','https://i.ibb.co/KxD9SzZ/C.png',1,'c###','1234','c### 같이 할 사람!\n캠 켜주세요! 마이크 꺼주세요!\n공지 확인 필수!',5,'https://i6e103.p.ssafy.io/csharp',5),(31,'2022-02-17 15:28:19','https://i.ibb.co/NZ4vqMw/algorithm.jpg',4,'알고리즘 스터디','1234','방 입장 비밀번호 1234입니다!\n지각하지 말고 들어오세요! ',6,'https://i6e103.p.ssafy.io/algogogo',32);
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:26
