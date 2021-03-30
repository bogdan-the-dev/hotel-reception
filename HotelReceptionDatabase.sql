-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `bookingID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `RoomID` int DEFAULT NULL,
  `CheckInDate` date DEFAULT NULL,
  `CheckOutDate` date DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `CheckedIn` tinyint(1) DEFAULT NULL,
  `CheckedOut` tinyint(1) DEFAULT NULL,
  `Paid` tinyint(1) DEFAULT NULL,
  `Canceled` tinyint(1) DEFAULT NULL,
  `Status` int DEFAULT NULL,
  PRIMARY KEY (`bookingID`),
  KEY `bookings_customerinfo_ID_fk` (`CustomerID`),
  KEY `bookings_room_RoomID_fk` (`RoomID`),
  CONSTRAINT `bookings_customerinfo_ID_fk` FOREIGN KEY (`CustomerID`) REFERENCES `customerinfo` (`ID`),
  CONSTRAINT `bookings_room_RoomID_fk` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,3,2,'2021-01-13','2021-01-13',60,0,0,0,NULL,NULL),(8,3,512,'2021-01-14','2021-01-15',300,1,1,1,NULL,NULL),(13,3,507,'2021-01-14','2021-01-21',2100,1,0,1,NULL,NULL),(26,3,12,'2021-01-15','2021-01-16',60,0,0,0,NULL,NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credential.level`
--

DROP TABLE IF EXISTS `credential.level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credential.level` (
  `id` int NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `credential.level_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credential.level`
--

LOCK TABLES `credential.level` WRITE;
/*!40000 ALTER TABLE `credential.level` DISABLE KEYS */;
INSERT INTO `credential.level` VALUES (0,'Manager'),(1,'Chief Receptionist'),(2,'Receptionist'),(3,'Maid'),(4,'Repairman'),(5,'Customer');
/*!40000 ALTER TABLE `credential.level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerinfo`
--

DROP TABLE IF EXISTS `customerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerinfo` (
  `ID` int NOT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `PhoneNumber` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `Address` text,
  `SocialSecurityNumber` varchar(15) DEFAULT NULL,
  `CreditCard` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `customerInfo_ID_uindex` (`ID`),
  CONSTRAINT `customerInfo_login_userID_fk` FOREIGN KEY (`ID`) REFERENCES `login` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerinfo`
--

LOCK TABLES `customerinfo` WRITE;
/*!40000 ALTER TABLE `customerinfo` DISABLE KEYS */;
INSERT INTO `customerinfo` VALUES (3,'Darth','Vader','0568751205','darth.vader@galacticempire.ge','The Executor','1585624723021','134365'),(4,'Mike','Smith','0659875125','mike@smith.com','Atlanta','654465','465'),(8,'Jim','McCartney','0569875158','jim@microsoft.com','Sibiu','4658','468'),(10,'Iulius','Cezar','0268759852','iulius@spqr.it','Roma','654456','354');
/*!40000 ALTER TABLE `customerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `UserName` varchar(200) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `CredentialLevel` int DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `login_credential.level_id_fk` (`CredentialLevel`),
  CONSTRAINT `login_credential.level_id_fk` FOREIGN KEY (`CredentialLevel`) REFERENCES `credential.level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Bogdan','TheBestPassword007',0),(2,'Tavi','Suntmaibuncatoti',5),(3,'DarthV','darkside',5),(4,'Mike','145',5),(5,'Andrei','123',0),(6,'VictorEvents','vicevents',4),(7,'Jenny','123',3),(8,'Jim','132',5),(9,'Paul','paul',4),(10,'Iulius','Cezar',5),(11,'Ann','123',3),(12,'Tavi1','123',4),(13,'Tavi12','123',3),(15,'Mary','123',2);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `RoomID` int NOT NULL AUTO_INCREMENT,
  `StatusID` int DEFAULT NULL,
  `RoomType` int DEFAULT NULL,
  PRIMARY KEY (`RoomID`),
  KEY `room_roomstatus_StatusID_fk` (`StatusID`),
  KEY `room_roomtype_TypeID_fk` (`RoomType`),
  CONSTRAINT `room_roomstatus_StatusID_fk` FOREIGN KEY (`StatusID`) REFERENCES `roomstatus` (`StatusID`),
  CONSTRAINT `room_roomtype_TypeID_fk` FOREIGN KEY (`RoomType`) REFERENCES `roomtype` (`TypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=514 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,5,2),(2,2,1),(3,5,2),(4,4,1),(5,5,2),(6,4,1),(7,5,2),(8,2,1),(9,5,2),(10,2,1),(11,5,2),(12,2,1),(101,5,2),(102,5,2),(103,5,2),(104,5,2),(105,5,2),(106,5,2),(107,5,2),(108,5,2),(109,5,2),(110,5,2),(111,5,2),(112,5,2),(201,5,1),(202,5,1),(203,5,1),(204,5,1),(205,5,1),(206,5,1),(207,5,1),(208,5,1),(209,5,1),(210,5,1),(211,5,1),(212,5,1),(301,5,2),(302,5,2),(303,5,2),(304,5,2),(305,5,2),(306,5,2),(307,5,2),(308,5,2),(309,5,2),(310,5,2),(311,3,2),(312,5,2),(401,2,3),(402,1,3),(403,5,3),(404,1,3),(405,5,3),(406,1,3),(407,5,3),(408,5,4),(409,5,4),(410,5,4),(411,5,4),(412,5,4),(501,5,4),(502,5,4),(503,5,4),(504,5,4),(505,5,4),(506,2,5),(507,2,5),(508,5,5),(509,5,5),(510,5,5),(511,5,5),(512,1,5);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomstatus`
--

DROP TABLE IF EXISTS `roomstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomstatus` (
  `StatusID` int NOT NULL AUTO_INCREMENT,
  `Status` varchar(100) NOT NULL,
  `CredentiallevelForModify` int DEFAULT NULL,
  PRIMARY KEY (`StatusID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomstatus`
--

LOCK TABLES `roomstatus` WRITE;
/*!40000 ALTER TABLE `roomstatus` DISABLE KEYS */;
INSERT INTO `roomstatus` VALUES (1,'Unoccupied',1),(2,'Occupied',2),(3,'In cleaning',3),(4,'In repair',4),(5,'Available for booking',5);
/*!40000 ALTER TABLE `roomstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomtype` (
  `TypeID` int NOT NULL AUTO_INCREMENT,
  `Price` float DEFAULT NULL,
  `MaximumNumberOfPeople` int DEFAULT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`TypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,60,1,'Basic Room with a single bed'),(2,90,2,'Basic Room with a double bed'),(3,110,2,'Premium room with double bed'),(4,170,4,'Appartement with 2 rooms'),(5,300,6,'PentHouse');
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-14  4:48:32
