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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `board_no` bigint DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `FKshov4m2go1nq7usxrxx0ob84b` (`board_no`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `FKshov4m2go1nq7usxrxx0ob84b` FOREIGN KEY (`board_no`) REFERENCES `board` (`board_no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'저 신청했습니다 같이 하고싶어요!','2022-02-14 06:45:11',2,'seulgi1029'),(2,'문제 레벨은 어느 정도 인가요?','2022-02-14 06:58:42',3,'gmldi135'),(3,'코딩테스트 가입하고 싶습니다!! 신청 눌렀어요?','2022-02-14 06:58:58',3,'yoonjung12'),(4,'혹시 스프링스터디 처음이신가요 ?? 그렇다면 저희 스터디에서 함께해요 ^_^ 저도 스터디원 구하고 있습니다!!','2022-02-14 07:01:19',8,'yoonjung12'),(5,'저 하고싶어요! 신청 했습니다 수락해주세요 ??','2022-02-14 07:18:46',12,'seulgi1029'),(6,'혹시 괜찮으시면 저랑 하실래요..??','2022-02-14 07:19:06',14,'seulgi1029'),(7,'도커 너무 어렵던데 신청 한 번 해보겠습니다!','2022-02-14 07:19:46',13,'seulgi1029'),(8,'엇,,, 저희도 구하는데 같이 하실래요??','2022-02-14 07:20:36',8,'seulgi1029'),(9,'저도 정말 필요한 스터디인데 스터디장 하실 생각은 없으신지...?','2022-02-14 07:21:24',15,'seulgi1029'),(10,'저 신청했습니다! 혹시 리액트에는 관심 없으신가요 ㅎ 저도 구하는 중이라 관심있으시면 신청해주세요!','2022-02-14 07:22:19',7,'eunwoo1029'),(11,'저도 신청했습니다! 열심히 참여하겠습니다','2022-02-14 07:22:41',13,'eunwoo1029'),(12,'자바 스터디 구하고 있었는데 괜찮으시면 초대해주세요! 혹시 몰라서 신청도 완료했습니다','2022-02-14 07:23:34',9,'eunwoo1029'),(13,'counting star~','2022-02-14 07:36:25',17,'eunwoo1029'),(16,'저 파이썬 할 줄 아는데,,ㅎ 이름이 마음에 안 들어서 신청 안 합니다 수고','2022-02-14 07:40:13',17,'seulgi1029'),(17,'저희 곧 진행할까 하는데 열심히 참여하실 건가요??','2022-02-15 02:34:49',20,'kiseul1004'),(18,'스터디 이름이 너무 마음에 드네요! 신청하겠습니다~','2022-02-15 02:35:26',18,'kiseul1004'),(19,'저도 같이 하고 싶어요! 혹시 저도 데려가주실 분??','2022-02-15 02:36:52',22,'kiseul1004'),(20,'도커의 신 너무 좋네요','2022-02-15 02:42:51',13,'billy'),(21,'백준 레벨 몇?','2022-02-15 02:43:11',20,'billy'),(22,'면스 같이하면 너무 좋은 것 같아요!','2022-02-15 02:44:05',15,'billy'),(23,'결석 인정 3회라니 너무 좋은 스터디네요! 보충까지 굳굳','2022-02-15 02:44:46',19,'billy'),(24,'저 신청했어요! 받아주시궜어요??','2022-02-15 02:46:46',21,'billy'),(28,'c#스터디 저도 참가하고 싶습니다. 참가하려는 이유는 영상 처리에 사용을 하고자 스터디에 참가하고 싶습니다. 열심히 할게여!','2022-02-15 14:34:11',1,'park1541'),(34,'초심자도 가입 가능한가요 ?','2022-02-15 16:31:57',18,'v951230'),(36,'저 신청했습니다 초대 받아주세여','2022-02-15 16:44:57',2,'heeya1008'),(46,'스프링 스터디 현재 진행중이에요 ! 인원 추가할 때 다시 알려드려도 괜찮을까요 ???','2022-02-17 06:47:36',25,'yoonjung12'),(49,'스터디 신청합니다! 받아주세요~','2022-02-17 07:42:30',24,'eunwoo1004'),(50,'저 같이 하고싶어요 !! 초대해주세요 !!! 알고리즘 뿌수고싶습니다!','2022-02-17 15:30:00',33,'yoonjung12'),(52,'코테 알고리즘 혼자 공부하려니 너무 힘들고 막막해서 스터디 가입하고자 댓글 남겨요! 열심히 참여 할게요!','2022-02-17 15:30:30',33,'heeya1008'),(61,'네 초대해 주세요!','2022-02-17 17:10:39',8,'heeya1008');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:24
