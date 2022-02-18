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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_no` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `content_status` bit(1) DEFAULT b'0',
  `content_title` varchar(60) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `header` bit(1) DEFAULT b'0',
  `recruit_number` int NOT NULL,
  `study_name` varchar(60) DEFAULT NULL,
  `study_no` bigint DEFAULT NULL,
  `studytype_name` varchar(50) DEFAULT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `reply_cnt` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`board_no`),
  KEY `FKfyf1fchnby6hndhlfaidier1r` (`user_id`),
  CONSTRAINT `FKfyf1fchnby6hndhlfaidier1r` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'c# 스터디 같이하실 분 구합니다! 함께해요',_binary '\0','c# 스터디 같이하실 분 구합니다! 함께해요','2022-02-14 06:41:29',_binary '\0',2,'c# 스터디',2,'C#','seulgi1029',7,1),(2,'스프링스터디 같이하실 분 구합니다! 저도 처음이라 수준이 비슷하신 분이면 좋겠어요^^\n인텔리제이 사용할 예정입니다~ 많이많이 신청해주세요~',_binary '\0','스프링스터디 같이하실 분 구해요!','2022-02-14 06:42:39',_binary '\0',5,'스프링스터디',1,'Spring','yoonjung12',7,2),(3,'주 5일 평일 기준으로 저녁 8시~ 10시 까지 백준, 프로그래머스 문제를 같이 정해서 풀고 같이 화면 공유를 통해 풀이할 거에요~ 다 같이 코테 뿌수실분 구해요. 현재 5분 더 모집합니다.',_binary '\0','코딩테스트 스터디원 구합니다.','2022-02-14 06:48:55',_binary '\0',5,'코딩테스트스터디',6,'기타','heeya1008',2,2),(4,'파이썬 스터디 같이하실분! 신청 후 댓글 남겨주세요~',_binary '\0','파이썬 스터디 같이하실분! 신청 후 댓글 남겨주세요~','2022-02-14 06:52:07',_binary '\0',4,'파이썬 스터디',7,'Python','seulgi1029',1,0),(5,'리액트 스터디 구합니다 저도 껴주세요 저도 하고 싶어요... 누가 진행하고 있으면 초대점..',_binary '\0','리액트 스터디 구합니다 저도 껴주세요','2022-02-14 06:52:51',_binary '',0,NULL,NULL,'React','seulgi1029',1,0),(6,'자바스크립 스터디 구합니다 껴주세요!\n열심히 참여하겠습니다!',_binary '\0','자바스크립트 스터디 구합니다!','2022-02-14 06:53:29',_binary '',0,NULL,NULL,'Javascript','seulgi1029',0,0),(7,'i love ruby에서 스터디원을 모십니다 많관부\n댓글, 신청 많이 해주세요!',_binary '\0','I love ruby 함께 하실 분??','2022-02-14 06:56:17',_binary '\0',5,'ruby 스터디',8,'RUBY','seulgi1029',1,1),(8,'스프링을 혼자 공부하려니 막막하고... 그래서 \n이번에 코스모스 사이트에서 제가 원하는 분류의 스터디를 찾고 싶어 게시글을 작성합니다.\n댓글로 연락주세요. 열심히 하겠습니다! ',_binary '\0','스프링 스터디 들어가고 싶어요!','2022-02-14 06:57:24',_binary '',0,NULL,NULL,'Spring','heeya1008',4,3),(9,'java 한 잔 하실 분? 자바 스터디 같이해요!!',_binary '\0','java 한 잔 하실 분?','2022-02-14 06:58:27',_binary '\0',5,'java를 job아라!',10,'Java','seulgi1029',2,1),(10,'php 스터디 같이 하실 분 신청해주세요!\n댓글로 남겨주시면 초대도 해드립니다',_binary '\0','php 스터디원 모집 중!','2022-02-14 07:05:38',_binary '\0',5,'php 스터디',11,'PHP','kiseul1004',0,0),(11,'ptthon 스터디 구해요! 오래 같이 하고 싶어요!',_binary '\0','python 스터디 구합니다','2022-02-14 07:06:19',_binary '',0,NULL,NULL,'Python','kiseul1004',1,0),(12,'은우랑 react 스터디 같이할 스터디원 구합니다! 출첵 엄격히 요구합니다~',_binary '\0','은우랑 react 스터디 같이할 사람 구함','2022-02-14 07:09:57',_binary '\0',5,'react 스터디',12,'React','eunwoo1029',2,1),(13,'도커의 기본적인 개념을 학습하고, 추가적으로 EC2에 Front, Back 도커 이미지를 배포해보는 과정으로 진행됩니다.\n\n따라서 하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!\n많은 연락 부탁드려요!',_binary '\0','도커의 신이 되어 보실분?','2022-02-14 07:33:59',_binary '\0',3,'도커의신',13,'기타','park1541',8,3),(14,'Kotlin 배우고 싶은데 같이 하실 분 없나요?? 저도 참여하고 싶어요! 초대 부탁드립니다',_binary '\0','Kotlin 스터디 하시는 분 없나요?','2022-02-14 07:10:48',_binary '',0,NULL,NULL,'Kotlin','eunwoo1029',6,1),(15,'상반기 취업시즌을 앞두고 면접스터디 함께 하실분 구해봅니다. \n대기업 취업을 목표로 하고 있지만, 목표 기업이 대기업이 아니여도 상관없습니다!\n평소에 면접 경험이 많으신 분이면 좋겠어요. 댓글에 면접경험을 적어주시면 감사하겠습니다!!',_binary '\0','면접 스터디 같이 하실 분 계신가요??','2022-02-14 07:16:02',_binary '',0,NULL,NULL,'기타','yoonjung12',6,2),(17,'현재 저 포함 6명을 Max로 모집 마감할 거에요.\n4차 산업혁명 기술중 하나인 인공지능을 Python 언어를 통해 오픈 소스 기반으로 실습을 하며 CS 지식을 스터디원들과 공유해 보아요.\n하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!\n많은 신청 부탁드려요!',_binary '\0','삐릭삐릭 AI 스터디  스터디원 구해요.','2022-02-14 07:32:50',_binary '\0',5,'인공지능스터디',15,'AI','sangsu123',7,2),(18,'스터디 이름 처럼 4차 산업 혁명 기술 중 하나인 빅 데이터를 함께 열심히 공부하며 데이터를 잘 다룰 수 있도록 같이 성장 하실분 구해요.\n\n하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!',_binary '\0','엄청난 데이터 수를 같이 다뤄 보실분 구해요!','2022-02-14 07:41:46',_binary '\0',4,'카운팅 데이터~ 밤하늘의 ~빅 데이터',20,'BigData','park1541',14,2),(19,'운영체제, 네트워크, 컴퓨터 구조를 돌아가며 공부 진행해요.\n[스터디 규칙] 입니다.\n스터디를 진행하는 기간동안 개인 사유로 총 3회 결석 가능. 단, 일주일에 최대 1번만 결석 가능. 결석한 당일의 진도는 일요일 혹은 주말에 보충으로 진행해야 함. 결석을 원할 경우 전날에 미리 알려줘야 함.\n\n하루 스터디 시간이 끝나갈 쯤 숙제를 각 스터디원 분들께 학습해오는 부분을 나눠서 스터디 당일날 화면 공유로 자기가 공부한 내용을 공부 하는 방식으로 진행됩니다.\n숙제를 꼭 해와서 스터디원들 에게 자신이 공부하며 이해한 내용을 같이 공유해 보아요!',_binary '\0','ABC~S STUDY원 구해요','2022-02-14 07:52:47',_binary '\0',4,'CS스터디',21,'기타','heeya1008',26,1),(20,'2022년 상반기 코딩테스트 대비해서 알고리즘 스터디 진행하는 스터디는 초대해주시면 감사하겠습니다!!!!!',_binary '','코딩 테스트 대비 알고리즘 스터디 초대 좀 해주세요','2022-02-15 16:42:52',_binary '',0,NULL,NULL,'기타','do99999',48,2),(21,'같이하실 분 신청 눌러주세요! 매주 화 목 8시에 진행할겁니다 출석 엄중히 요구!',_binary '\0','인원 더 모집합니다!','2022-02-15 02:31:11',_binary '\0',2,'c# 스터디',2,'C#','seulgi1029',7,1),(22,'프론트엔드에 관심이 많습니다! 스터디 참여하고 싶은데 혹시 진행중인 스터디 없을까요?? 열심히 참여하겠습니다!',_binary '\0','프론트엔드 스터디 하고 싶어요!','2022-02-15 02:33:16',_binary '',0,NULL,NULL,'Frontend','eunwoo1029',39,1),(23,'아무 공부나 그냥 편하게 같이하실 스터디원 구합니다! 저는 ai 공부하고 있습니다 혹시 같은 분야를 공부하면 모르는 건 서로 알려주고 열심히 참여하실 분 모집합니다! 많은 신청 부탁! 댓글 환영',_binary '\0','study 가취 할 사람~~~','2022-02-15 02:42:16',_binary '\0',5,'study with bill?',22,'기타','billy',20,0),(24,'빅데이터, ai 하려면 파이썬 배워야 되는데 막막막하신 분 같이 하실래요?\n월요일마다 7시에 진행하고 출석체크 합니다\n상벌점 제도 있고 캠 사용은 필!수!입니다(딴짓방지) 많이 신청해주세요!',_binary '\0','빅데이터? ai? 파이썬이면 어디든 갈 수 있어','2022-02-15 16:42:50',_binary '\0',5,'파이썬 스터디',7,'Python','seulgi1029',7,1),(25,'스프링 스터디 구합니다! 장구 말고 장고로 백엔드 공부하다가 스프링도 궁금해지고, 요즘은 스프링으로 백엔드를 많이 다룬다고 들었습니다.\n\n혹시나 하는 마음에 코스모스에서 스터디 구해봅니다 ✏✒',_binary '\0','스프링스터디 구합니다!','2022-02-15 16:44:13',_binary '',0,NULL,NULL,'Spring','v951230',13,1),(26,'4차 산업혁명 시대에 맞춰 인공지능에 대해 공부해 보고 싶은데 혼자서는 집중이 잘 안되어 스터디를 찾고자 해요! \n스터디에 열심히 참여할 자신 있습니다.\n댓글로 연락 주세요!',_binary '\0','AI 스터디 찾아요!!','2022-02-16 00:24:29',_binary '',0,NULL,NULL,'AI','heeya1008',32,0),(33,'알고리즘 스터디 같이 하실 분 \n신청이나 댓글 남겨주세요!',_binary '\0','@@알고리즘 스터디 같이 하실분@@','2022-02-17 15:28:56',_binary '\0',5,'알고리즘 스터디',31,'기타','seulgi1029',29,2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18  9:57:22
