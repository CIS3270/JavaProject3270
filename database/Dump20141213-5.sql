-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	5.6.22-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `flight_num` int(11) DEFAULT NULL,
  `airline` varchar(15) DEFAULT NULL,
  `depart_city` varchar(25) DEFAULT NULL,
  `depart_state` varchar(2) DEFAULT NULL,
  `depart_date` date DEFAULT NULL,
  `dest_city` varchar(25) DEFAULT NULL,
  `dest_state` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flight`
--

DROP TABLE IF EXISTS `flight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flight` (
  `flight_num` int(11) NOT NULL,
  `airline` varchar(15) NOT NULL,
  `depart_city` varchar(25) NOT NULL,
  `depart_state` varchar(2) NOT NULL,
  `depart_date` date NOT NULL,
  `dest_city` varchar(25) NOT NULL,
  `dest_state` varchar(2) NOT NULL,
  `capacity` int(11) NOT NULL,
  `rem_seats` int(11) NOT NULL,
  PRIMARY KEY (`flight_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flight`
--

LOCK TABLES `flight` WRITE;
/*!40000 ALTER TABLE `flight` DISABLE KEYS */;
INSERT INTO `flight` VALUES (123,'spirit','atlanta','ga','2014-12-25','houston','tx',300,300),(456,'delta','miami','fl','2015-01-20','detroit ','mi',300,300),(789,'southwest','san diego','ca','2015-05-22','memphis','tn',250,250);
/*!40000 ALTER TABLE `flight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `SSN` int(9) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `email` varchar(45) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `address` varchar(35) NOT NULL,
  `zip` int(11) NOT NULL,
  `state` varchar(2) NOT NULL,
  `security_question` varchar(50) NOT NULL,
  `security_answer` varchar(50) NOT NULL,
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (123456780,1,'Bobby ','Brown','bobbybrown@yahoo.com','bobby','brown','456 spooner street',10029,'PA','whats your first name?','bobby'),(123456781,2,'Ray ','Charles','raycharles@hotmail.com','ray','charles','000 where am I lane',0,'NY','are you seeing anyone?','no'),(123456789,3,'Young','Shooter','youngshooter@gmail.com','young','shooter','123 gangster lane',30032,'GA','how hood are you?','very hood');
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

-- Dump completed on 2014-12-13 19:52:00
