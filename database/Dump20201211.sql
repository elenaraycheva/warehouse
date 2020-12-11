-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: warehouse1
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.11-MariaDB

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
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brands` (
  `id_brand` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  PRIMARY KEY (`id_brand`),
  UNIQUE KEY `unique_index` (`brand`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (10,'Здраве');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash_register`
--

DROP TABLE IF EXISTS `cash_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cash_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` double DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index` (`name`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`money` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash_register`
--

LOCK TABLES `cash_register` WRITE;
/*!40000 ALTER TABLE `cash_register` DISABLE KEYS */;
INSERT INTO `cash_register` VALUES (1,50.319999999999936,'Каса 1');
/*!40000 ALTER TABLE `cash_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id_clients` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `company` varchar(30) NOT NULL,
  `adress` varchar(45) NOT NULL,
  PRIMARY KEY (`id_clients`),
  UNIQUE KEY `unique_index` (`first_name`,`last_name`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (3,'Mariq','Spasova','0886667543','m.spasova@gmail.com','Здравец','София, ул Васил Левски 4');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note_items`
--

DROP TABLE IF EXISTS `delivery_note_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_note_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_delivery` double DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL,
  `delivery_note_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stock_id` (`stock_id`),
  KEY `delivery_note_id` (`delivery_note_id`),
  CONSTRAINT `delivery_note_items_ibfk_1` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id_stock`),
  CONSTRAINT `delivery_note_items_ibfk_2` FOREIGN KEY (`delivery_note_id`) REFERENCES `delivery_notes` (`id_delivery`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`amount_delivery` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note_items`
--

LOCK TABLES `delivery_note_items` WRITE;
/*!40000 ALTER TABLE `delivery_note_items` DISABLE KEYS */;
INSERT INTO `delivery_note_items` VALUES (1,3,14,4),(2,2,15,4),(3,5,14,5),(4,2,15,6),(5,2,14,7),(6,3,14,9),(7,3,15,10),(8,2,14,11),(9,1,15,12),(10,2,14,13),(11,1,14,14),(12,2,15,15),(13,1,14,16),(14,1,14,17),(15,1,14,18),(16,1,14,19),(17,1,14,20),(18,1,14,21),(19,1,15,22),(20,1,15,23),(21,2,15,24),(22,12,14,25),(23,12,15,25),(24,12,14,26),(25,5,15,27),(26,400,14,28);
/*!40000 ALTER TABLE `delivery_note_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_notes`
--

DROP TABLE IF EXISTS `delivery_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_notes` (
  `id_delivery` int(11) NOT NULL AUTO_INCREMENT,
  `ddate` date DEFAULT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `supplier_id` (`supplier_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `delivery_notes_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id_supplier`),
  CONSTRAINT `delivery_notes_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `operators` (`id_operator`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`total_price` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_notes`
--

LOCK TABLES `delivery_notes` WRITE;
/*!40000 ALTER TABLE `delivery_notes` DISABLE KEYS */;
INSERT INTO `delivery_notes` VALUES (2,'2020-11-25',1,16,NULL),(3,'2020-11-25',1,16,NULL),(4,'2020-11-25',1,16,NULL),(5,'2020-11-26',1,16,NULL),(6,'2020-11-26',1,17,NULL),(7,'2020-12-01',1,16,NULL),(9,'2020-12-01',1,16,NULL),(10,'2020-12-01',1,16,NULL),(11,'2020-12-01',1,16,NULL),(12,'2020-12-01',1,16,NULL),(13,'2020-12-01',1,16,NULL),(14,'2020-12-01',1,16,NULL),(15,'2020-12-02',1,16,NULL),(16,'2020-12-02',1,16,NULL),(17,'2020-12-02',1,16,NULL),(18,'2020-12-02',1,16,NULL),(19,'2020-12-02',1,16,NULL),(20,'2020-12-02',1,16,NULL),(21,'2020-12-02',1,16,NULL),(22,'2020-12-02',1,16,NULL),(23,'2020-12-02',1,16,NULL),(24,'2020-12-02',1,16,NULL),(25,'2020-12-03',1,16,84),(26,'2020-12-05',1,16,36),(27,'2020-12-05',1,16,20),(28,'2020-12-09',5,16,1200);
/*!40000 ALTER TABLE `delivery_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id_invoice` int(11) NOT NULL AUTO_INCREMENT,
  `ddate` date DEFAULT NULL,
  `clients_id` int(11) DEFAULT NULL,
  `operator_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id_invoice`),
  KEY `clients_id` (`clients_id`),
  KEY `operator_id` (`operator_id`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`clients_id`) REFERENCES `clients` (`id_clients`),
  CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`operator_id`) REFERENCES `operators` (`id_operator`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`total_price` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (25,'2020-11-25',3,16,NULL),(26,'2020-11-25',3,16,NULL),(27,'2020-11-25',3,16,NULL),(28,'2020-11-25',3,16,NULL),(29,'2020-12-03',3,16,7.2),(30,'2020-12-03',3,16,4.8),(31,'2020-12-05',NULL,16,86.4),(32,'2020-12-05',3,16,14.399999999999999),(33,'2020-12-09',3,16,64.8),(36,'2020-12-09',3,16,25.2),(37,'2020-12-09',3,16,18);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_items`
--

DROP TABLE IF EXISTS `invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_delivery` double DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stock_id` (`stock_id`),
  KEY `invoice_id` (`invoice_id`),
  CONSTRAINT `invoice_items_ibfk_1` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id_stock`),
  CONSTRAINT `invoice_items_ibfk_2` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id_invoice`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`amount_delivery` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_items`
--

LOCK TABLES `invoice_items` WRITE;
/*!40000 ALTER TABLE `invoice_items` DISABLE KEYS */;
INSERT INTO `invoice_items` VALUES (31,1,14,25),(32,1,15,26),(33,2,14,27),(34,3,15,27),(35,2,14,27),(36,3,15,27),(37,2,14,28),(38,4,15,28),(39,2,14,29),(40,1,15,30),(41,12,14,31),(42,12,14,31),(43,3,15,32),(44,18,14,33),(45,3,14,36),(46,3,15,36),(47,5,14,37);
/*!40000 ALTER TABLE `invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operators`
--

DROP TABLE IF EXISTS `operators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operators` (
  `id_operator` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_operator`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `unique_index` (`first_name`,`last_name`,`email`),
  CONSTRAINT `operators_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operators`
--

LOCK TABLES `operators` WRITE;
/*!40000 ALTER TABLE `operators` DISABLE KEYS */;
INSERT INTO `operators` VALUES (16,'Simona','Petrova','088324343','simona@gmail.com',35),(17,'Deqn','Ivanov','088835454','d.ivanov@gmail.com',36),(18,'Stoqn','Dimitrov','0885444321','s.dimitrov@gmail.com',46);
/*!40000 ALTER TABLE `operators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Operator');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocks` (
  `id_stock` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price_for_buying` double DEFAULT NULL,
  PRIMARY KEY (`id_stock`),
  UNIQUE KEY `unique_index` (`brand_id`,`type_id`,`name`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `stocks_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id_brand`),
  CONSTRAINT `stocks_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `type` (`id_type`),
  CONSTRAINT `CONSTRAINT_1` CHECK (`price_for_buying` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (14,409,10,6,'крем невен',3),(15,18,10,6,'крем мента',4);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id_supplier` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `company` varchar(30) NOT NULL,
  `adress` varchar(45) NOT NULL,
  PRIMARY KEY (`id_supplier`),
  UNIQUE KEY `unique_index` (`first_name`,`last_name`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Nikolay','Penev','08812333557','n.penev@gmail.com','Nivea','Sofia, ul Marica 5'),(5,'Nikoleta','Petrova','0848353434','n.petrova@abv.bg','Nivea','ul. Han Asparuh');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (4,'душ гел'),(6,'крем');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) DEFAULT NULL,
  `ppassword` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `unique_index` (`user_name`,`ppassword`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'elena','fgdg','elii2323@abv.bg','0885355650',1),(2,'TihomirT','1234','tihomir.totev@gmail.com','0882211323',1),(35,'Simona12','1234','simona@gmail.com','088324343',2),(36,'Deqn13','1234','d.ivanov@gmail.com','088835454',2),(46,'Stoqn21','1234','s.dimitrov@gmail.com','0885444321',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11 12:52:58
