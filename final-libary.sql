drop database  mydb;
CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `book`
--

CREATE TABLE Category
(
  locker_id INT NOT NULL,
  category_name VARCHAR(30) NOT NULL,
  `Category_id` INT NOT NULL,
  PRIMARY KEY (`Category_id`)
);
INSERT INTO `category` VALUES (110,'Classics',1),(115,'Coding',4),(120,'Story',5),(130,'Imaginary',2),(200,'Life',3),(210,'Cartoon',6),(220,'Cartoon',7),(350,'Fantacy',8);

-- SELECT book.Book_ID, book.Title, book.author, book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id
-- 						FROM book
-- 						INNER JOIN publisher ON book.pub_id=publisher.pub_id
-- 						INNER JOIN Category ON book.Category_id=Category.Category_id ;


DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `Book_ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `Category_id` int NOT NULL,
  `selling_price` float NOT NULL,
	`buying_price` float NOT NULL,
`Quantity` int NOT NULL,
  `pub_id` int DEFAULT NULL,
  	`buy_date` DATE NOT NULL,  
  PRIMARY KEY (`Book_ID`),
  UNIQUE KEY `Book_ID_UNIQUE` (`Book_ID`),
  KEY `pub_id_idx` (`pub_id`),
  

  
  
    CONSTRAINT `Category_id` FOREIGN KEY (`Category_id`) REFERENCES `Category` (`Category_id`) ON UPDATE CASCADE,
  CONSTRAINT `pub_id` FOREIGN KEY (`pub_id`) REFERENCES `publisher` (`pub_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Anxiety','Saleh Romani',1,15,5,2,1,'2022-01-12'),(3,'Mr. Robot','Ramdan Ahmad',4,55,25,1,4,'2022-03-22'),(4,'Story of Life','Ameer Rasras',1,33,20,8,5,'2022-04-15'),(5,'Animals','Mohammad Shahwan',1,10,3,3,5,'2022-05-22'),(6,'Dance with life','Ahmad Tuqan',3,15,5,0,6,'2022-02-02'),(7,'Lord of the Rings','Tolkien, J.R.',5,8,3,10,1,'2022-03-06'),(8,'Hamlet, Prince of Denmark','Shakespeare',5,13,5,4,7,'2021-12-29'),(9,'Harry Potter','Rowling, J.K.',2,20,10,6,8,'2022-06-8'),(10,'Tom Sawyer','Twain, Mark',7,10,5,3,4,'2022-04-9'),(11,'The Hours','Cunnningham, Michael',3,9,4.5,10,1,'2022-05-13'),(12,'Mrs. Dalloway','Woolf, Virginia',7,9,4,5,7,'2022-02-06'),(13,'Anna Karenina','Tolstoy, Leo',5,5,2,4,8,'2022-04-25'),(14,'War and Peace','Tolstoy, Leo',2,8,4,8,5,'2022-1-19'),(15,'Slave Of The Mountain','KIKU  GROSS',8,15,7,3,7,'2022-05-07');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `emp_id` int NOT NULL,
  `Book_id` int NOT NULL,
  `Member_id` int NOT NULL,
  `Borrow_date` date NOT NULL,
  `Return_date` date,
	`Due_date` DATE NOT NULL,
  `Borrow_SN` INT AUTO_INCREMENT,
  `late_fee` float NOT NULL,
primary key(Borrow_SN),
  CONSTRAINT `Book_id` FOREIGN KEY (`Book_id`) REFERENCES `book` (`Book_ID`),
  CONSTRAINT `emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`),
  CONSTRAINT `Member_id` FOREIGN KEY (`Member_id`) REFERENCES `member` (`member_id`)
) AUTO_INCREMENT=10000,ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--
select *
from
borrow;

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow`(`emp_id`, `Book_id`,`Member_id`,`Borrow_date`,`Due_date`) VALUES
 (101,3,119002,'2022-01-08','2022-06-10'),
(102,5,119001,'2022-01-08','2022-01-10'),
(101,3,119002,'2022-01-12','2022-01-19');

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow`(`emp_id`, `Book_id`,`Member_id`,`Borrow_date`,`Due_date`,`Return_date`,`late_fee`) VALUES
(101,3,119002,'2022-01-12','2022-01-19','2022-06-19',20);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy` (
  `emp_id` int NOT NULL,
  `Book_id` int NOT NULL,
  `Member_id` int NOT NULL,
  `Date` date NOT NULL,
   Discount_percent int NOT NULL,
  KEY `emp_id_idx` (`emp_id`),
  KEY `Book_id_idx` (`Book_id`),
  KEY `Member_id_idx` (`Member_id`),
  CONSTRAINT `Book_id_buy` FOREIGN KEY (`Book_id`) REFERENCES `book` (`Book_ID`),
  CONSTRAINT `emp_id_buy` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`),
  CONSTRAINT `Member_id_buy` FOREIGN KEY (`Member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (101,3,119001,'2021-12-28',10),(101,6,119002,'2022-01-12',0),(101,6,119002,'2022-01-12',30),(101,6,119001,'2022-01-12',5),(101,2,119008,'2022-01-28',15);
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (101,'Omar Etkaidek','0597533023','Ramallah','1234'),(102,'Hakim Yasin','0594141252','Jenin','0999'),(103,'Mahdi Javid','0597400433','Ramallah','2783'),(104,'Bilal Ally','0598557632','Ramallah','0022'),(107,'Siraj Abdallah','09558333888','Hebron','5577'),(109,'Abdulrahman Aman','0595797427','Hebron','0909'),(110,'Sharif Ishmael','0595333969','Birzeit','0808');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Birthdate` date NOT NULL,
  `Start_date` date NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (119001,'Alia Hussein','Female','0598774420','Hebron','2007-04-16','2021-12-24'),(119002,'Mohammad Ali','Male','0598633223','Ramallah & El-Bireh','2011-03-13','2021-12-25'),(119003,'Abeer Moghaddam','Female','0593073777','Jerusalem','2000-08-22','2019-04-25'),(119004,'Anbar Salehi','Female','0598114 665','Bethlehem','2000-10-23','2019-05-16'),(119005,'Riyad Hasen','Male','0599070457','Bethlehem','2001-02-12','2019-08-20'),(119006,'Muhsin Sawaya','Male','0592670306','Hebron','2004-07-29','2020-01-03'),(119007,'Aniya Cook','Female','0595343968','Jerusalem','2005-07-06','2020-06-03'),(119008,'Kamal Khalifa','Male','0564877410','Hebron','2006-12-01','2020-10-05'),(119009,'Jimell Youssef','Male','0596191649','Ramallah & El-Bireh','2010-02-12','2021-08-31'),(119010,'Mahibah Akram','Female','0565036195','Ramallah & El-Bireh','2011-08-26','2021-11-15');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `pub_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`pub_id`),
  UNIQUE KEY `pub_id_UNIQUE` (`pub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Huda Library','02-24987544','Ein Sara','huda@gmail.com'),(4,'Hussein Bin Ali','02-2999222','Haras St.','hussein@gmail.com'),(5,'Dar Al-Waleed','02-2023881','Ramallah','waleed@gmail.com'),(6,'Dar Al-Doaa','02-2235678','Jenin','doaa@gmail.com'),(7,'Dar Al-Farouk','02-2289943','Nablus','farouk@gmail.com'),(8,'Dar Al-Mostaqbal','02-2276549','Hebron','mostaqbal@gmail.com');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 13:55:00





drop database if exists  mydb;
CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `book`
--

CREATE TABLE Category
(
  locker_id INT NOT NULL,
  category_name VARCHAR(30) NOT NULL,
  `Category_id` INT NOT NULL,
  PRIMARY KEY (`Category_id`)
);
INSERT INTO `category` VALUES (110,'Classics',1),(115,'Coding',4),(120,'Story',5),(130,'Imaginary',2),(200,'Life',3),(210,'Cartoon',6),(220,'Cartoon',7),(350,'Fantacy',8);

-- SELECT book.Book_ID, book.Title, book.author, book.selling_price, book.quantity, publisher.name,book.buying_price,Category.category_name,Category.locker_id
-- 						FROM book
-- 						INNER JOIN publisher ON book.pub_id=publisher.pub_id
-- 						INNER JOIN Category ON book.Category_id=Category.Category_id ;


DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `Book_ID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `Author` varchar(45) NOT NULL,
  `Category_id` int NOT NULL,
  `selling_price` float NOT NULL,
	`buying_price` float NOT NULL,
`Quantity` int NOT NULL,
  `pub_id` int DEFAULT NULL,
  	`buy_date` DATE NOT NULL,  
  PRIMARY KEY (`Book_ID`),
    CONSTRAINT `Category_id` FOREIGN KEY (`Category_id`) REFERENCES `Category` (`Category_id`) ON UPDATE CASCADE,
  CONSTRAINT `pub_id` FOREIGN KEY (`pub_id`) REFERENCES `publisher` (`pub_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Anxiety','Saleh Romani',1,15,5,2,1,'2022-01-12'),(3,'Mr. Robot','Ramdan Ahmad',4,55,25,1,4,'2022-03-22'),(4,'Story of Life','Ameer Rasras',1,33,20,8,5,'2022-04-15'),(5,'Animals','Mohammad Shahwan',1,10,3,3,5,'2022-05-22'),(6,'Dance with life','Ahmad Tuqan',3,15,5,0,6,'2022-02-02'),(7,'Lord of the Rings','Tolkien, J.R.',5,8,3,10,1,'2022-03-06'),(8,'Hamlet, Prince of Denmark','Shakespeare',5,13,5,4,7,'2021-12-29'),(9,'Harry Potter','Rowling, J.K.',2,20,10,6,8,'2022-06-8'),(10,'Tom Sawyer','Twain, Mark',7,10,5,3,4,'2022-04-9'),(11,'The Hours','Cunnningham, Michael',3,9,4.5,10,1,'2022-05-13'),(12,'Mrs. Dalloway','Woolf, Virginia',7,9,4,5,7,'2022-02-06'),(13,'Anna Karenina','Tolstoy, Leo',5,5,2,4,8,'2022-04-25'),(14,'War and Peace','Tolstoy, Leo',2,8,4,8,5,'2022-1-19'),(15,'Slave Of The Mountain','KIKU  GROSS',8,15,7,3,7,'2022-05-07');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `emp_id` int NOT NULL,
  `Book_id` int NOT NULL,
  `Member_id` int NOT NULL,
  `Borrow_date` date NOT NULL,
  `Return_date` date,
	`Due_date` DATE NOT NULL,
  `Borrow_SN` INT AUTO_INCREMENT,
  `late_fee` float NOT NULL,
primary key(Borrow_SN),
  CONSTRAINT `Book_id` FOREIGN KEY (`Book_id`) REFERENCES `book` (`Book_ID`),
  CONSTRAINT `emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`),
  CONSTRAINT `Member_id` FOREIGN KEY (`Member_id`) REFERENCES `member` (`member_id`)
) AUTO_INCREMENT=10000,ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--


LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow`(`emp_id`, `Book_id`,`Member_id`,`Borrow_date`,`Due_date`) VALUES
 (101,3,119002,'2022-01-08','2022-06-10'),
(102,5,119001,'2022-01-08','2022-01-10'),
(101,3,119002,'2022-01-12','2022-01-19');

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow`(`emp_id`, `Book_id`,`Member_id`,`Borrow_date`,`Due_date`,`Return_date`,`late_fee`) VALUES
(101,3,119002,'2022-01-12','2022-01-19','2022-06-19',20);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy` (
  `emp_id` int NOT NULL,
  `Book_id` int NOT NULL,
  `Member_id` int NOT NULL,
  `Date` date NOT NULL,
   Discount_percent int NOT NULL,
  KEY `emp_id_idx` (`emp_id`),
  KEY `Book_id_idx` (`Book_id`),
  KEY `Member_id_idx` (`Member_id`),
  CONSTRAINT `Book_id_buy` FOREIGN KEY (`Book_id`) REFERENCES `book` (`Book_ID`),
  CONSTRAINT `emp_id_buy` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`ID`),
  CONSTRAINT `Member_id_buy` FOREIGN KEY (`Member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
INSERT INTO `buy` VALUES (101,3,119001,'2021-12-28',10),(101,6,119002,'2022-01-12',0),(101,6,119002,'2022-01-12',30),(101,6,119001,'2022-01-12',5),(101,2,119008,'2022-01-28',15);
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (101,'Ahmad Qatu','0597533023','Ramallah','1234'),(102,'Hakim Yasin','0594141252','Jenin','0999'),(103,'Mahdi Javid','0597400433','Ramallah','2783'),(104,'Bilal Ally','0598557632','Ramallah','0022'),(107,'Siraj Abdallah','09558333888','Hebron','5577'),(109,'Abdulrahman Aman','0595797427','Hebron','0909'),(110,'Sharif Ishmael','0595333969','Birzeit','0808');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Gender` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Birthdate` date NOT NULL,
  `Start_date` date NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

  
LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (119001,'Alia Hussein','Female','0598774420','Hebron','2007-04-16','2021-12-24'),(119002,'Mohammad Ali','Male','0598633223','Ramallah & El-Bireh','2011-03-13','2021-12-25'),(119003,'Abeer Moghaddam','Female','0593073777','Jerusalem','2000-08-22','2019-04-25'),(119004,'Anbar Salehi','Female','0598114 665','Bethlehem','2000-10-23','2019-05-16'),(119005,'Riyad Hasen','Male','0599070457','Bethlehem','2001-02-12','2019-08-20'),(119006,'Muhsin Sawaya','Male','0592670306','Hebron','2004-07-29','2020-01-03'),(119007,'Aniya Cook','Female','0595343968','Jerusalem','2005-07-06','2020-06-03'),(119008,'Kamal Khalifa','Male','0564877410','Hebron','2006-12-01','2020-10-05'),(119009,'Jimell Youssef','Male','0596191649','Ramallah & El-Bireh','2010-02-12','2021-08-31'),(119010,'Mahibah Akram','Female','0565036195','Ramallah & El-Bireh','2011-08-26','2021-11-15');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `pub_id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`pub_id`),
  UNIQUE KEY `pub_id_UNIQUE` (`pub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Huda Library','02-24987544','Ein Sara','huda@gmail.com'),(4,'Hussein Bin Ali','02-2999222','Haras St.','hussein@gmail.com'),(5,'Dar Al-Waleed','02-2023881','Ramallah','waleed@gmail.com'),(6,'Dar Al-Doaa','02-2235678','Jenin','doaa@gmail.com'),(7,'Dar Al-Farouk','02-2289943','Nablus','farouk@gmail.com'),(8,'Dar Al-Mostaqbal','02-2276549','Hebron','mostaqbal@gmail.com');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 13:55:00

