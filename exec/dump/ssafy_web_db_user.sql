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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL,
  `join_date` timestamp NULL DEFAULT NULL,
  `total_attendance` int NOT NULL DEFAULT '0',
  `total_time` int NOT NULL DEFAULT '0',
  `user_email` varchar(200) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','2022-02-14 01:05:53',0,0,'noreply.nsnt.cosmos@gmail.com','관리자','$2a$10$UnriMwWBxBAFeDk4TIopqeB01tNWHzFfIgvzbbqEN1rVSkUsW2lgG'),('billy','2022-02-14 07:28:47',0,0,'billy@gmail.com','빌스카스가드','$2a$10$7362phCkrphQgRhtP9pgyOj9PFrZxb.FrE5RlBcoYjDbviquD1lp.'),('do99999','2022-02-14 01:51:59',0,0,'rlaehdwn9150@gmail.com','김동주','$2a$10$BypfBTFg9M0oScc7Mmxfze1f1dDYcf3QXBkK6biJ3h3wCLprlfxgm'),('eunwoo1004','2022-02-16 18:27:06',0,0,'tmfrlghtjr@naver.com','차은우','$2a$10$gsGUmNvXtA8nQ.jXjnTo6.x9H8AfpubyygXGdiOcDEx4psOIhLGxW'),('eunwoo1029','2022-02-14 07:03:51',0,0,'eunwoo@naver.com','차은우','$2a$10$7In2FXPnKQV1wv2o3MwZ1.3NJHoEw2siacdPa3RUtytcreF4OHyEy'),('gmldi135','2022-02-14 01:20:40',0,0,'gmldi1357@gmail.com','한성희','$2a$10$2nkvRmqRBpNdA75ZJQBoCeeZGfY7mzvpYbC7fNfmnwH2RbjWnas9S'),('heeya1008','2022-02-14 01:30:26',0,0,'hgi7201@naver.com','김광희','$2a$10$k8exuVVdu1t/mWQ/flW20O5aKKKEj8.nIeUhVDqJ/pcBEigHsQ.GS'),('kiseul1004','2022-02-14 07:03:01',0,0,'kiseul1004@naver.com','슬기문','$2a$10$R.M8UnC0AxrhxU0htzxMc.hqKuKDDX/6ji9h.SHI3ooHFFF.wxmVi'),('park1541','2022-02-14 07:02:26',0,0,'parksunguen@naver.com','박성은','$2a$10$1PMhvAK3NSQG7RKhHgL5te09PyblXEHV0u6kti7FlPm6RXGN1tFCq'),('polyjjang1','2022-02-14 01:04:28',0,0,'bee1404@naver.com','한폴리','$2a$10$8v1SFhFlkTGvgvPKTzZWpOlLIu7umWcHNJ4ZLfGoYpwkmUAXS1nfa'),('sangsu123','2022-02-14 07:27:30',0,0,'jungsangsu@gmail.com','정상수','$2a$10$7x.ua9MJ4SHOTCypUNnwfOiA5pRYKOyl6qM0vaZ/Sc3fh9KO55Lgu'),('seulgi1029','2022-02-14 01:04:56',0,0,'seulgi1029@naver.com','문슬기','$2a$10$mN8sn3fubFYgkWETju/J6.YZAviG6qV.BYXrUUItD03dyq8qPXuv.'),('testid','2022-02-17 05:04:58',0,0,'ssafy@ssafy.com','테스트','$2a$10$0V21Y24SCg5PfnRQT8ycluy2.EiZfWsFeFf5QBUdAK7tHuR34MWsi'),('v951230','2022-02-14 07:28:40',0,0,'v951230@gmail.com','김태형','$2a$10$byyUC6TALq2w1N9LQa5A9uI02DblVaHSLC4hRxfTrLbS9.LyW7Eeq'),('yoonjung12','2022-02-14 01:05:50',0,0,'yoonjung1205@naver.com','정윤정','$2a$10$deJ5KdzhOZkbefnAMiUeaexlXAUVgL4T.hCEM3kslXyFhxe9FzwPa');
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

-- Dump completed on 2022-02-18  9:57:23
