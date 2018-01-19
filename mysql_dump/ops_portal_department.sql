CREATE DATABASE  IF NOT EXISTS `ops_portal` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ops_portal`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ops_portal
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) DEFAULT NULL,
  `department_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'AVM-HC','Horizontal'),(2,'Healthcare India','Vertical'),(3,'EAS IPM','Horizontal'),(4,'Healthcare','Vertical'),(5,'CDB-AIM - Big Data','Horizontal'),(6,'QE&A','Horizontal'),(7,'CDB-AIM - BISQUAD','Horizontal'),(8,'CIS','Horizontal'),(9,'AVM-AIM','Horizontal'),(10,'ERSS','Horizontal'),(11,'CDB-AIM - HC','Horizontal'),(12,'NFT COE','Horizontal'),(13,'CDB-AIM','Horizontal'),(14,'CDB-INT-HC','Horizontal'),(15,'QEA BPM','Horizontal'),(16,'People function Shared Service','Vertical'),(17,'Corporate Deployable Pool','Horizontal'),(18,'AVM-EAS-CRM','Horizontal'),(19,'Admin-EAS-Oracle-GDC','Horizontal'),(20,'EAS Analytics','Horizontal'),(21,'CDB-INT-Cust-Exp-ECM','Horizontal'),(22,'EAS CRM','Horizontal'),(23,'Admin-EAS-CRM-GDC','Horizontal'),(24,'QEA CRM','Horizontal'),(25,'AVM-ISmO','Horizontal'),(26,'TECHNOLOGY CONSULTING','Horizontal'),(27,'CDB-INT-Digital-Learning-LC','Horizontal'),(28,'CDB-INT-BFS','Horizontal'),(29,'CDB-INT-Cust-Exp-ContentWCM','Horizontal'),(30,'CIS-CCS','Horizontal'),(31,'AVM-DEP','Horizontal'),(32,'AVM-EAS-IPM','Horizontal'),(33,'CDE-DevOps-SGA','Horizontal'),(34,'CDE-Agile-SGA','Horizontal'),(35,'AVM-Engineering','Horizontal'),(36,'Corporate Security','Horizontal'),(37,'AVM-EAS-OSP','Horizontal'),(38,'AVM','Horizontal'),(39,'QEandA Technology CoE','Horizontal'),(40,'CDB-INT','Horizontal');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-18 13:35:21
