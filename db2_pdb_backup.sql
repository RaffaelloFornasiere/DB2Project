-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: db2_pdb
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activation_schedule`
--

DROP TABLE IF EXISTS `activation_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activation_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `activation_date` date NOT NULL,
  `deactivation_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `activation_schedule_id_uindex` (`id`),
  KEY `activation_schedule_orders_order_id_fk` (`order_id`),
  KEY `activation_schedule_users_username_fk` (`username`),
  CONSTRAINT `activation_schedule_orders_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `activation_schedule_users_username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activation_schedule`
--

LOCK TABLES `activation_schedule` WRITE;
/*!40000 ALTER TABLE `activation_schedule` DISABLE KEYS */;
INSERT INTO `activation_schedule` VALUES (1,1565,'admin4','2022-03-11','2022-08-11'),(2,1566,'admin4','2022-03-11','2022-08-11'),(3,1567,'admin4','2022-03-11','2022-08-11'),(4,1568,'Bubu','2022-03-11','2022-08-11'),(5,1569,'Bubu','2022-03-11','2022-08-11'),(6,1570,'Bubu','2022-03-11','2022-08-11'),(7,1571,'Bubu','2022-03-11','2022-08-11'),(8,1572,'Bubu','2022-03-11','2022-08-11'),(9,1573,'Bubu','2022-03-11','2022-08-11'),(10,1574,'Bubu','2022-03-11','2022-08-11'),(11,1575,'semocagai','2022-03-11','2022-08-11'),(12,1576,'semocagai','2022-03-11','2022-05-11'),(13,1577,'semocagai','2022-02-28','2022-07-28'),(14,1578,'semocagai','2039-12-15','2040-12-15'),(15,1579,'Elisa','2022-03-11','2023-03-11'),(16,1580,'Elisadmin','2022-03-11','2022-08-11'),(19,1583,'admin','2022-03-12','2022-08-12'),(20,1584,'admin','2022-03-12','2022-08-12'),(21,1585,'admin','2022-03-12','2022-08-12'),(22,1588,'aaaaaa','2022-03-12','2022-08-12'),(23,1587,'aaaaaa','2022-03-12','2022-08-12'),(24,1589,'admin4','2022-03-12','2022-08-12'),(25,1590,'aaaaaa','2022-03-13','2022-08-13'),(26,1591,'swanhes','2022-03-13','2022-08-13'),(27,1592,'swanhes','2022-03-13','2022-08-13'),(28,1593,'sdfdfadsadminasdfasdf','2022-03-13','2022-08-13'),(29,1594,'sdfdfadsadminasdfasdf','2022-03-13','2022-08-13'),(30,1596,'Elisa','2022-03-15','2022-08-15'),(31,1597,'Elisa','2022-03-15','2022-08-15'),(32,1599,'admin','2022-03-15','2022-08-15'),(33,1600,'admin','2022-03-16','2022-08-16'),(34,1601,'admin','2022-03-16','2022-08-16'),(35,1602,'admin','2022-03-16','2022-08-16'),(36,1603,'admin','2022-03-16','2022-08-16'),(37,1604,'admin','2022-03-16','2022-08-16'),(38,1605,'admin','2022-03-16','2026-09-16'),(39,1606,'admin','2022-03-16','2026-09-16'),(40,1607,'nuovo','2022-01-16','2022-08-16'),(41,1608,'nuovo','2022-03-16','2023-03-16'),(42,1609,'admin','2022-02-07','2022-07-07'),(43,1610,'admin','2022-02-07','2022-07-07'),(44,1611,'admin','2022-02-07','2022-03-07');
/*!40000 ALTER TABLE `activation_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `last_billing` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `alerts_pk` (`id`,`username`),
  KEY `alerts_users_username_fk` (`username`),
  KEY `alerts_billings_id_fk` (`last_billing`),
  CONSTRAINT `alerts_billings_id_fk` FOREIGN KEY (`last_billing`) REFERENCES `billings` (`id`),
  CONSTRAINT `alerts_users_username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts`
--

LOCK TABLES `alerts` WRITE;
/*!40000 ALTER TABLE `alerts` DISABLE KEYS */;
INSERT INTO `alerts` VALUES (25,'sdfdfadsadminasdfasdf',298,NULL),(26,'Elisa',303,NULL);
/*!40000 ALTER TABLE `alerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billings`
--

DROP TABLE IF EXISTS `billings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `result` tinyint(1) NOT NULL,
  `billing_date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `billings_id_uindex` (`id`),
  KEY `billings_orders_order_id_fk` (`order_id`),
  CONSTRAINT `billings_orders_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billings`
--

LOCK TABLES `billings` WRITE;
/*!40000 ALTER TABLE `billings` DISABLE KEYS */;
INSERT INTO `billings` VALUES (224,1565,1,'2022-03-11 12:20:47'),(233,1566,1,'2022-03-11 13:12:05'),(237,1567,1,'2022-03-11 13:13:04'),(239,1568,1,'2022-03-11 13:29:43'),(240,1569,1,'2022-03-11 13:29:52'),(241,1570,1,'2022-03-11 13:30:05'),(242,1571,1,'2022-03-11 13:30:11'),(243,1572,1,'2022-03-11 13:30:19'),(244,1573,1,'2022-03-11 13:31:29'),(249,1574,1,'2022-03-11 13:33:11'),(250,1575,1,'2022-03-11 13:34:00'),(252,1576,1,'2022-03-11 13:34:17'),(255,1577,1,'2022-03-11 13:34:36'),(256,1578,1,'2022-03-11 13:37:13'),(257,1579,1,'2022-03-11 19:08:23'),(258,1580,1,'2022-03-11 19:12:14'),(275,1582,1,'2022-03-12 01:57:44'),(276,1583,0,'2022-03-12 13:02:43'),(277,1583,1,'2022-03-12 13:02:47'),(278,1584,0,'2022-03-12 13:05:42'),(279,1584,1,'2022-03-12 13:06:02'),(280,1585,0,'2022-03-12 13:08:12'),(281,1585,1,'2022-03-12 13:08:18'),(282,1586,0,'2022-03-12 13:08:27'),(283,1586,0,'2022-03-12 13:08:33'),(284,1586,0,'2022-03-12 13:08:33'),(285,1587,0,'2022-03-12 13:10:02'),(286,1587,0,'2022-03-12 13:10:05'),(287,1588,1,'2022-03-12 13:10:20'),(288,1587,1,'2022-03-12 14:06:15'),(289,1589,1,'2022-03-12 14:06:52'),(290,1590,1,'2022-03-13 08:38:04'),(291,1591,1,'2022-03-13 12:12:17'),(292,1592,0,'2022-03-13 12:12:51'),(293,1592,1,'2022-03-13 12:13:08'),(294,1593,1,'2022-03-13 12:32:12'),(295,1594,1,'2022-03-13 12:32:30'),(296,1595,0,'2022-03-13 12:33:22'),(297,1595,0,'2022-03-13 12:33:29'),(298,1595,0,'2022-03-13 12:33:29'),(299,1596,1,'2022-03-15 18:35:47'),(300,1597,1,'2022-03-15 19:47:10'),(301,1598,0,'2022-03-15 19:47:18'),(302,1598,0,'2022-03-15 19:47:19'),(303,1598,0,'2022-03-15 19:47:20'),(304,1598,0,'2022-03-15 19:47:21'),(305,1598,0,'2022-03-15 19:47:22'),(306,1599,1,'2022-03-16 04:40:27'),(307,1600,1,'2022-03-16 04:43:13'),(308,1601,1,'2022-03-16 04:45:33'),(309,1602,1,'2022-03-16 04:46:25'),(310,1603,1,'2022-03-16 04:47:26'),(311,1604,1,'2022-03-16 04:47:32'),(312,1605,1,'2022-03-16 05:06:53'),(313,1606,1,'2022-03-16 05:07:36'),(314,1607,1,'2022-03-16 05:24:01'),(315,1608,1,'2022-03-16 06:09:47'),(316,1609,1,'2022-03-16 06:34:52'),(317,1610,1,'2022-03-16 06:36:53'),(318,1611,1,'2022-03-16 06:39:14');
/*!40000 ALTER TABLE `billings` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `mark_insolvent_users` AFTER INSERT ON `billings` FOR EACH ROW BEGIN
    IF (!new.result)
    THEN
        update users
        set insolvent = 1
        where username in
              (
                  select distinct user
                  from orders
                  where order_id = NEW.order_id
              );
    ELSE
        update users
        set insolvent = 0
        where username in
              (
                  select distinct user
                  from orders
                  where order_id = NEW.order_id
              );
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `SalesPackage_manager` AFTER INSERT ON `billings` FOR EACH ROW BEGIN
    declare _package_id int;
    declare _total_sold int;
    declare _total_sold_with int;
    declare _total_sold_without int;
    declare _average_optional_sold double;


    
    set _package_id := (select package_id from orders where order_id = NEW.order_id);
    set _total_sold := (select count(*) from orders where package_id = _package_id);
    set _total_sold_with := (select count(*)
                             from (select order_id from orders where package_id = _package_id) as orders
                             where order_id in (select oop.id_order from orders_optional_packages oop));
    set _total_sold_without := (select count(*)
                                from (select order_id from orders where package_id = _package_id) as orders
                                where order_id not in (select oop.id_order from orders_optional_packages oop));
    set _average_optional_sold := ((select count(*)  from orders_optional_packages oop
                       join orders o on o.order_id = oop.id_order
              where o.package_id = _package_id)  /
        (select count(*) from orders o2 where o2.package_id = _package_id));


    
    
    if (_package_id in (select package_id from sales_package_report)) THEN
        update sales_package_report
        set total_sold        = _total_sold,
            total_w_optional  = _total_sold_with,
            total_wo_optional = _total_sold_without,
            average_optional  = _average_optional_sold
        where package_id = _package_id;
    else
        insert into sales_package_report (package_id, total_sold, total_w_optional, total_wo_optional, average_optional)
        values (_package_id, _total_sold, _total_sold_with, _total_sold_without, _average_optional_sold);
    end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `activation_schedule_insert` AFTER INSERT ON `billings` FOR EACH ROW BEGIN

    declare _username varchar(255);
    declare _activation_date date;
    declare _deactivation_date date;

    if(new.result = 1) then

    set _username = (SELECT o.user from orders o where o.order_id = new.order_id);
    set _activation_date = (SELECT start_date from orders o where o.order_id = new.order_id);
    set _deactivation_date = DATE_ADD(_activation_date,
        INTERVAL (
            select months from validity_periods join orders o2 on validity_periods.id = o2.validity_period_id
            where o2.order_id = NEW.order_id) MONTH);

    insert into activation_schedule (order_id, username, activation_date, deactivation_date)
        value (new.order_id, _username, _activation_date, _deactivation_date);
    end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `alert_management` AFTER INSERT ON `billings` FOR EACH ROW BEGIN
    declare failed_payments integer;
    declare _username varchar(255);
    SET _username = (select user from orders o  where o.order_id = new.order_id);
    #  returns the number of the lasts failed payments
    #  (if the last payment is successful it will return 0)

    SET failed_payments := (select count(*) from billings b where b.order_id in (select o.order_id from orders o where o.suspended =1
    and b.order_id = o.order_id and o.user = _username));

#     SET failed_payments := (select count(*)
#                             from (select b.*
#                                   from billings b join orders o2 on b.order_id = o2.order_id
#                                   where o2.user = _username
#                                   order by billing_date_time desc
#                                   limit 3
#                                  ) as lastThree
#                             where lastThree.result = false);



    # if there are more then 3 payments failed it will add an alert (if not already present)

    IF (NEW.result = 0 and failed_payments >= 3
        and (select count(*) from alerts a where a.username = _username) = 0) THEN
        insert into alerts (username, last_billing, amount) values (_username, new.id, (select orders.total_value from orders where orders.order_id = new.order_id));
    end if;

    # if a successful payment is added and there are previous failed payments
    # it deletes the old alert
    if (new.result = true and failed_payments > 0) THEN
        delete from alerts a where a.username = _username;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `optional_packages`
--

DROP TABLE IF EXISTS `optional_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optional_packages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `monthly_fee` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `optional_packages__id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optional_packages`
--

LOCK TABLES `optional_packages` WRITE;
/*!40000 ALTER TABLE `optional_packages` DISABLE KEYS */;
INSERT INTO `optional_packages` VALUES (1,'Sky','Description Sky',11.8),(2,'Netflix','Description Netflix',16),(3,'Infinity','Description Infinity',10.78),(4,'Apple Music','Description Apple Music',16.39),(5,'Spotify','Description Spotify',10),(6,'NordVPN','Description NordVPN',9.39),(7,'YouTube Premium','Description YouTube Premium',9.78),(8,'Disney+','Description Disney+',13.39);
/*!40000 ALTER TABLE `optional_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `package_id` int NOT NULL,
  `start_date` date DEFAULT NULL,
  `validity_period_id` int DEFAULT NULL,
  `suspended` tinyint(1) DEFAULT NULL,
  `total_value` double DEFAULT NULL,
  UNIQUE KEY `purchases_id_uindex` (`order_id`),
  KEY `purchases_users_username_fk` (`user`),
  KEY `purchases_packages_id_fk` (`package_id`),
  KEY `orders_validity_periods_id_fk` (`validity_period_id`),
  CONSTRAINT `orders_packages_id_fk` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_validity_periods_id_fk` FOREIGN KEY (`validity_period_id`) REFERENCES `validity_periods` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `purchases_users_username_fk` FOREIGN KEY (`user`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1612 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1565,'admin4','2022-03-11 11:20:47',14,'2022-03-11',1,0,54.8),(1566,'admin4','2022-03-11 12:10:49',15,'2022-03-11',1,0,41.8),(1567,'admin4','2022-03-11 12:13:03',14,'2022-03-11',1,0,54.8),(1568,'Bubu','2022-03-11 12:29:39',16,'2022-03-11',1,0,26.78),(1569,'Bubu','2022-03-11 12:29:52',14,'2022-03-11',1,0,54.8),(1570,'Bubu','2022-03-11 12:30:04',14,'2022-03-11',1,0,54.8),(1571,'Bubu','2022-03-11 12:30:11',11,'2022-03-11',1,0,41.8),(1572,'Bubu','2022-03-11 12:30:19',15,'2022-03-11',1,0,41.8),(1573,'Bubu','2022-03-11 12:31:29',14,'2022-03-11',1,0,54.8),(1574,'Bubu','2022-03-11 12:32:03',14,'2022-03-11',1,0,54.8),(1575,'semocagai','2022-03-11 12:34:01',11,'2022-03-11',1,0,41.8),(1576,'semocagai','2022-03-11 12:34:15',20,'2022-03-11',2,0,41.78),(1577,'semocagai','2022-03-11 12:34:33',14,'2022-02-28',1,0,167.57999999999998),(1578,'semocagai','2022-03-11 12:37:13',19,'2039-12-15',3,0,NULL),(1579,'Elisa','2022-03-11 18:08:22',20,'2022-03-11',3,0,NULL),(1580,'Elisadmin','2022-03-11 18:12:13',17,'2022-03-11',1,0,52),(1582,'Elisa','2022-03-11 18:14:41',11,'2022-03-11',1,0,41.8),(1583,'admin','2022-03-12 12:02:43',14,'2022-03-12',1,0,54.8),(1584,'admin','2022-03-12 12:05:42',14,'2022-03-12',1,0,54.8),(1585,'admin','2022-03-12 12:08:08',14,'2022-03-12',1,0,54.8),(1586,'admin','2022-03-12 12:08:27',14,'2022-03-12',1,1,54.8),(1587,'aaaaaa','2022-03-12 12:10:02',11,'2022-03-12',1,0,208.75),(1588,'aaaaaa','2022-03-12 12:10:19',13,'2022-03-12',1,0,85.39),(1589,'admin4','2022-03-12 13:06:52',14,'2022-03-12',1,0,167.57999999999998),(1590,'aaaaaa','2022-03-13 07:38:03',14,'2022-03-13',1,0,54.8),(1591,'swanhes','2022-03-13 11:12:17',14,'2022-03-13',1,0,54.8),(1592,'swanhes','2022-03-13 11:12:51',11,'2022-03-13',1,0,41.8),(1593,'sdfdfadsadminasdfasdf','2022-03-13 11:32:12',14,'2022-03-13',1,0,54.8),(1594,'sdfdfadsadminasdfasdf','2022-03-13 11:32:29',11,'2022-03-13',1,0,41.8),(1595,'sdfdfadsadminasdfasdf','2022-03-13 11:33:22',14,'2022-03-13',1,1,54.8),(1596,'Elisa','2022-03-15 17:35:47',14,'2022-03-15',1,0,54.8),(1597,'Elisa','2022-03-15 18:47:10',14,'2022-03-15',1,0,54.8),(1598,'Elisa','2022-03-15 18:47:18',14,'2022-03-15',1,1,54.8),(1599,'admin','2022-03-16 03:40:27',14,'2022-03-15',1,0,54.8),(1600,'admin','2022-03-16 03:43:13',14,'2022-03-16',1,0,54.8),(1601,'admin','2022-03-16 03:44:26',14,'2022-03-16',1,0,54.8),(1602,'admin','2022-03-16 03:46:20',14,'2022-03-16',1,0,54.8),(1603,'admin','2022-03-16 03:47:04',14,'2022-03-16',1,0,54.8),(1604,'admin','2022-03-16 03:47:30',14,'2022-03-16',1,0,54.8),(1605,'admin','2022-03-16 04:06:51',14,'2022-03-16',11,0,124.8),(1606,'admin','2022-03-16 04:07:32',14,'2022-03-16',11,0,160.97),(1607,'nuovo','2022-03-16 04:24:01',14,'2022-03-16',1,0,63.8),(1608,'nuovo','2022-03-16 05:09:46',11,'2022-03-16',3,0,114.75),(1609,'admin','2022-03-16 05:34:52',14,'2022-02-07',1,0,63.8),(1610,'admin','2022-03-16 05:36:53',11,'2022-02-07',1,0,41.8),(1611,'admin','2022-03-16 05:39:14',15,'2022-02-07',12,0,32.8);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `sales_validity_manager` AFTER INSERT ON `orders` FOR EACH ROW BEGIN
    declare _package_id int;
    declare _validity_id int;
    declare _total int;

    set _package_id := new.package_id;
    set _validity_id := new.validity_period_id;

    set _total := (
        select count(*)
        from orders
        where _package_id = package_id
          and _validity_id = validity_period_id
    );

    if ((_package_id, _validity_id) in
        (select svr.package_id, svr.validity_period_id from sales_validity_report svr))
    then
        update sales_validity_report svr
        set svr.package_id = _package_id,
            svr.total      = _total
        where svr.package_id = _package_id
          and svr.validity_period_id = _validity_id;
    else
        insert into sales_validity_report (package_id, validity_period_id, total)
        values (_package_id, _validity_id, _total);
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `orders_optional_packages`
--

DROP TABLE IF EXISTS `orders_optional_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_optional_packages` (
  `id_order` int DEFAULT NULL,
  `id_optional_package` int DEFAULT NULL,
  KEY `orders_optional_packages_orders_order_id_fk` (`id_order`),
  KEY `orders_optional_packages_optional_packages_id_fk` (`id_optional_package`),
  CONSTRAINT `orders_optional_packages_optional_packages_id_fk` FOREIGN KEY (`id_optional_package`) REFERENCES `optional_packages` (`id`),
  CONSTRAINT `orders_optional_packages_orders_order_id_fk` FOREIGN KEY (`id_order`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_optional_packages`
--

LOCK TABLES `orders_optional_packages` WRITE;
/*!40000 ALTER TABLE `orders_optional_packages` DISABLE KEYS */;
INSERT INTO `orders_optional_packages` VALUES (1565,1),(1566,1),(1567,1),(1568,3),(1569,1),(1570,1),(1571,1),(1572,1),(1573,1),(1574,1),(1575,1),(1576,7),(1577,1),(1577,2),(1577,3),(1578,2),(1578,6),(1578,8),(1580,2),(1582,1),(1583,1),(1584,1),(1585,1),(1586,1),(1588,2),(1588,6),(1587,1),(1587,3),(1587,4),(1587,5),(1587,7),(1589,1),(1589,2),(1589,3),(1590,1),(1591,1),(1592,1),(1593,1),(1594,1),(1595,1),(1596,1),(1597,1),(1598,1),(1599,1),(1600,1),(1601,1),(1602,1),(1603,1),(1604,1),(1605,1),(1605,2),(1605,5),(1606,1),(1606,2),(1606,3),(1606,4),(1606,5),(1607,1),(1608,1),(1608,2),(1608,3),(1608,4),(1608,5),(1608,7),(1609,1),(1610,1),(1611,1);
/*!40000 ALTER TABLE `orders_optional_packages` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `sales_optional_insert` AFTER INSERT ON `orders_optional_packages` FOR EACH ROW BEGIN
    
    if (new.id_optional_package not in (select s.optional_package_id from sales_optional_report s)) then
        insert into sales_optional_report (optional_package_id, score) value (new.id_optional_package, 0);
    end if;

    update sales_optional_report s
    set s.score = (CAST((select count(*)
                         from orders_optional_packages oop
                         where oop.id_optional_package = s.optional_package_id) as DOUBLE) /
                   CAST((select count(*) from orders_optional_packages) as DOUBLE));

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `sales_optional_update` AFTER UPDATE ON `orders_optional_packages` FOR EACH ROW BEGIN


    if (new.id_optional_package not in (select s.optional_package_id from sales_optional_report s)) then
        insert into sales_optional_report (optional_package_id, score) value (new.id_optional_package, 0);
    end if;

    update sales_optional_report s
    set s.score = (CAST((select count(*)
                         from orders_optional_packages oop
                         where oop.id_optional_package = s.optional_package_id) as DOUBLE) /
                   CAST((select count(*) from orders_optional_packages) as DOUBLE));


end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`asus`@`%`*/ /*!50003 TRIGGER `sales_optional_delete` AFTER DELETE ON `orders_optional_packages` FOR EACH ROW BEGIN
    
    
    if ((select count(*) from orders_optional_packages) > 0) then
        update sales_optional_report s
        set s.score =
                (CAST((select count(*)
                       from orders_optional_packages oop
                       where oop.id_optional_package = old.id_optional_package) as DOUBLE)
                    / CAST((select count(*) from orders_optional_packages) as DOUBLE))
        where s.optional_package_id = old.id_optional_package;
        
    else
        delete from sales_optional_report s where s.optional_package_id = old.id_optional_package;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `packages`
--

DROP TABLE IF EXISTS `packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages` (
  `package_id` int NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) NOT NULL,
  UNIQUE KEY `packages_id_uindex` (`package_id`),
  UNIQUE KEY `packages_package_name_uindex` (`package_name`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages`
--

LOCK TABLES `packages` WRITE;
/*!40000 ALTER TABLE `packages` DISABLE KEYS */;
INSERT INTO `packages` VALUES (14,'100 GB'),(11,'Family+'),(15,'Infinity'),(16,'Premium Fibra'),(13,'Red Max'),(12,'Red Pro'),(18,'Shake it easy'),(17,'Shake it fun'),(19,'Simple'),(20,'Simple+');
/*!40000 ALTER TABLE `packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packages_optional_packages`
--

DROP TABLE IF EXISTS `packages_optional_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages_optional_packages` (
  `id_service_package` int NOT NULL,
  `id_optional_package` int NOT NULL,
  UNIQUE KEY `packages_optional_packages_pk` (`id_service_package`,`id_optional_package`),
  KEY `packages_optional_packages_optional_packages_id_fk` (`id_optional_package`),
  CONSTRAINT `packages_optional_packages_optional_packages_id_fk` FOREIGN KEY (`id_optional_package`) REFERENCES `optional_packages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages_optional_packages`
--

LOCK TABLES `packages_optional_packages` WRITE;
/*!40000 ALTER TABLE `packages_optional_packages` DISABLE KEYS */;
INSERT INTO `packages_optional_packages` VALUES (11,1),(12,1),(14,1),(15,1),(18,1),(33,1),(34,1),(38,1),(42,1),(43,1),(44,1),(45,1),(46,1),(11,2),(12,2),(13,2),(14,2),(17,2),(19,2),(21,2),(38,2),(42,2),(43,2),(46,2),(48,2),(50,2),(51,2),(11,3),(12,3),(14,3),(16,3),(17,3),(48,3),(11,4),(12,4),(14,4),(15,4),(18,4),(49,4),(50,4),(11,5),(12,5),(13,5),(14,5),(15,5),(49,5),(13,6),(15,6),(16,6),(18,6),(19,6),(11,7),(12,7),(13,7),(16,7),(17,7),(18,7),(20,7),(16,8),(17,8),(19,8);
/*!40000 ALTER TABLE `packages_optional_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packages_services`
--

DROP TABLE IF EXISTS `packages_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packages_services` (
  `package_id` int DEFAULT NULL,
  `service_id` int DEFAULT NULL,
  UNIQUE KEY `packages_services_pk` (`package_id`,`service_id`),
  KEY `packages_services_services_service_id_fk` (`service_id`),
  CONSTRAINT `packages_services_packages_package_id_fk` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`),
  CONSTRAINT `packages_services_services_service_id_fk` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages_services`
--

LOCK TABLES `packages_services` WRITE;
/*!40000 ALTER TABLE `packages_services` DISABLE KEYS */;
INSERT INTO `packages_services` VALUES (11,2),(12,2),(12,4),(13,2),(14,2),(14,3),(14,4),(14,12),(14,14),(15,2),(16,4),(17,2),(17,4),(18,2),(20,2),(20,3);
/*!40000 ALTER TABLE `packages_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL,
  `role` varchar(31) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_optional_report`
--

DROP TABLE IF EXISTS `sales_optional_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_optional_report` (
  `optional_package_id` int NOT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`optional_package_id`),
  UNIQUE KEY `SalesOptionalReport_optional_package_id_uindex` (`optional_package_id`),
  CONSTRAINT `sales_optional_report_optional_packages_id_fk` FOREIGN KEY (`optional_package_id`) REFERENCES `optional_packages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_optional_report`
--

LOCK TABLES `sales_optional_report` WRITE;
/*!40000 ALTER TABLE `sales_optional_report` DISABLE KEYS */;
INSERT INTO `sales_optional_report` VALUES (1,0.5970149253731343),(2,0.11940298507462686),(3,0.08955223880597014),(4,0.04477611940298507),(5,0.05970149253731343),(6,0.029850746268656716),(7,0.04477611940298507),(8,0.014925373134328358);
/*!40000 ALTER TABLE `sales_optional_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_package_report`
--

DROP TABLE IF EXISTS `sales_package_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_package_report` (
  `package_id` int DEFAULT NULL,
  `total_sold` int DEFAULT NULL,
  `total_w_optional` int DEFAULT NULL,
  `total_wo_optional` int DEFAULT NULL,
  `average_optional` double DEFAULT NULL,
  KEY `package_statistics_packages_package_id_fk` (`package_id`),
  CONSTRAINT `package_statistics_packages_package_id_fk` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_package_report`
--

LOCK TABLES `sales_package_report` WRITE;
/*!40000 ALTER TABLE `sales_package_report` DISABLE KEYS */;
INSERT INTO `sales_package_report` VALUES (14,29,29,0,1.344827586),(11,8,8,0,2.125),(15,3,3,0,1),(16,2,2,0,2),(18,2,2,0,4),(17,3,3,0,2),(20,3,2,1,0.666666666),(13,1,1,0,2),(19,1,1,0,3);
/*!40000 ALTER TABLE `sales_package_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_validity_report`
--

DROP TABLE IF EXISTS `sales_validity_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_validity_report` (
  `package_id` int NOT NULL,
  `validity_period_id` int NOT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`package_id`,`validity_period_id`),
  KEY `sales_validity_report_validity_periods_id_fk` (`validity_period_id`),
  CONSTRAINT `sales_validity_report_validity_periods_id_fk` FOREIGN KEY (`validity_period_id`) REFERENCES `validity_periods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SalesValidityReport_packages_package_id_fk` FOREIGN KEY (`package_id`) REFERENCES `packages` (`package_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_validity_report`
--

LOCK TABLES `sales_validity_report` WRITE;
/*!40000 ALTER TABLE `sales_validity_report` DISABLE KEYS */;
INSERT INTO `sales_validity_report` VALUES (11,1,7),(11,2,19),(11,3,1),(11,4,20),(12,1,22),(12,2,12),(12,3,31),(12,4,24),(13,1,1),(13,2,19),(13,3,25),(13,4,19),(14,1,27),(14,2,15),(14,3,31),(14,4,21),(14,11,2),(15,1,2),(15,2,22),(15,3,17),(15,4,16),(15,12,1),(16,1,2),(16,2,20),(16,3,20),(16,4,22),(17,1,3),(17,2,21),(17,3,24),(17,4,23),(18,1,17),(18,2,13),(18,3,1),(18,4,21),(19,1,18),(19,2,18),(19,3,1),(19,4,23),(20,1,24),(20,2,2),(20,3,1),(20,4,25);
/*!40000 ALTER TABLE `sales_validity_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `service_name` varchar(255) DEFAULT NULL,
  `service_details` json DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `services__service_id_uindex` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'Fixed Internet','{\"@type\": \"it.polimi.db2.teleco_app.services.models.packagedetails.FixedInternetDetails\", \"costMonth\": 20, \"gigabytes\": 4000, \"extraGigaBytesFee\": 5}'),(3,'Mobile Phone','{\"sms\": 1000, \"@type\": \"it.polimi.db2.teleco_app.services.models.packagedetails.MobilePhoneDetails\", \"minutes\": 1000, \"costMonth\": 7, \"extraSmsFee\": 0.15, \"extraMinutesFee\": 0.14}'),(4,'Mobile Internet','{\"@type\": \"it.polimi.db2.teleco_app.services.models.packagedetails.MobileInternetDetails\", \"costMonth\": 6, \"gigabytes\": 100, \"extraGigaBytesFee\": 3}'),(12,'Fixed Phone','{\"@type\": \"it.polimi.db2.teleco_app.services.models.packagedetails.FixedPhoneDetails\", \"costMonth\": 5}'),(14,'New Service','{\"@type\": \"it.polimi.db2.teleco_app.services.models.packagedetails.FixedInternetDetails\", \"costMonth\": 4, \"gigabytes\": 5, \"extraGigaBytesFee\": null}');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `user_accumulative_services`
--

DROP TABLE IF EXISTS `user_accumulative_services`;
/*!50001 DROP VIEW IF EXISTS `user_accumulative_services`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `user_accumulative_services` AS SELECT 
 1 AS `user`,
 1 AS `minutes`,
 1 AS `sms`,
 1 AS `gigabytes`,
 1 AS `cost`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  UNIQUE KEY `user_roles_pk` (`user_id`,`role_id`),
  KEY `user_roles_roles_id_fk` (`role_id`),
  CONSTRAINT `user_roles_roles_id_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_roles_users_username_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('aaaaaa',1),('Bubu',1),('Elisa',1),('nuovo',1),('Perdibole',1),('Peribole',1),('raffa',1),('raffa_forna',1),('Semocagai',1),('swanhes',1),('admin',2),('admin3',2),('admin4',2),('Elisadmin',2),('Ffghjadmin',2),('sdfdfadsadminasdfasdf',2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `insolvent` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `users_username_uindex` (`username`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('aaaaaa','$2a$10$MbC4PrkRZROyHI5AJmBJueymBquA1limAbbkmHrA/enIOx1zKc5ei','aaaaaa11','aaaaaa','aaa@aaa.com',0),('admin','$2a$10$4gLIxdHqTI1vTo/wlozg9uNM4as7Mdt8JKECyyHasXsaUkvgLdkNG','Admin1','Admin1','aaa1@aaa.com',0),('admin2','$2a$10$3RcDRHzzvNZx0nv9/eJ8F.fiDmGdCmOhAFWvgElP4ZSyg0XdO9Iju','Admin2','Admin2','aaa2@aaa.com',NULL),('admin3','$2a$10$3r7qKkXs2zeLD5WIp/jvHe4rusVq3Mqrq.XZfjFo9jp4knYNBs8aa','Admin3','Admin3','aaa3@aaa.com',0),('admin4','$2a$10$0Ak7rX7CO0jVFMjWFZnXSOXPRSYjvo8QrDxqkqGVh4cEkY3HtyZZy','admin4','admin4','aaa4@aaa.com',0),('Bubu','$2a$10$sp9hIPnjIPPp1D.9eFvNcu06FAOVXAGEzrtEdjBZO3WDIihrMDJgG','Bibu','Babu','aaa5@aaa.com',0),('Elisa','$2a$10$VZamsJM9BMwe4YOcrD1DYupjzq2K207vfOFtdxSk7llYSnkJaHL/i','Elisa','Brollo','aaa6@aaa.com',1),('Elisadmin','$2a$10$./eX39v0h4QH9YWN5ypnV.Kx5ca0Qw5G79FX98POf8ycAGmfF0kwq','Elisa2','Brollo','aaa7@aaa.com',0),('Ffghjadmin','$2a$10$KHpQajmzi8s2sk2riMUFquGGvh5ZFLgiG14Nl6xhFgTcUMnV41D1u','Gjiijhg','Ggghjjj','aaa8@aaa.com',NULL),('nuovo','$2a$10$Lf/auyvRNMzFyF5dH320beS8uN1Wb7kbM6P4ybXcxBOCKofoOXA32','nuovo','nuovo','nuovo@nuovo.it',0),('Perdibole','$2a$10$FjCWAwgVcZHj8ogHoDbgrOWjAavs7IXgbLrqkYwV7Z8ElANxm0Dxu','Perdibole','Perdibole','aaa9@aaa.com',NULL),('Peribole','$2a$10$unXcA8djPo3zQwJP4.iIYeGBNgw7mv77xys7nCyX6q5EcsQ5Vi4Vq','Antani','Comesefosse','aaa10@aaa.com',NULL),('raffa','$2a$10$y0l8sUUAKsP8N1dY5okerOiIihiKZ35PgICf5A2.wIm91ttfQye9a','RaffaelloRR','FornasiereRR','aaa11@aaa.com',1),('raffa_forna','$2a$10$3cyPiP5cYknGkWaRraxe1O.0uoupj7nDVK3O72W7KHz.3OLiHAoBi','raffa','Fornasiere','aaa@aaa3.com',NULL),('sdfdfadsadminasdfasdf','$2a$10$4OKiWtdKzNrMawgtuiR5uO7ODIGm2y6cRSQoGcLxxfq1aU9X6U8cS','Nome','Cognome','nome.cognome@emai.com',1),('Semocagai','$2a$10$3Bg60XBJxhGQF0JOBwb1tu63/1xzlpFZSt0GXg20kRDc6Y8Pk3YKq','Caro','Cogoi','aaa12@aaa.com',0),('swanhes','$2a$10$GXUtVHV4u04C0JRye8KKGOzWjVmTvWeFpB6Gz24CooxTNQxqRHmeS','Riccardp','Burato','riccardo.burato@icloud.com',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validity_period_package`
--

DROP TABLE IF EXISTS `validity_period_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_period_package` (
  `packageId` int NOT NULL,
  `validityPeriodId` int NOT NULL,
  PRIMARY KEY (`packageId`,`validityPeriodId`),
  KEY `validity_period_package_validity_periods_id_fk` (`validityPeriodId`),
  CONSTRAINT `validity_period_package_validity_periods_id_fk` FOREIGN KEY (`validityPeriodId`) REFERENCES `validity_periods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_period_package`
--

LOCK TABLES `validity_period_package` WRITE;
/*!40000 ALTER TABLE `validity_period_package` DISABLE KEYS */;
INSERT INTO `validity_period_package` VALUES (11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(33,1),(34,1),(38,1),(44,1),(45,1),(48,1),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(38,2),(42,2),(43,2),(48,2),(51,2),(11,3),(12,3),(13,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3),(21,3),(42,3),(43,3),(49,3),(50,3),(51,3),(11,4),(14,4),(21,4),(43,4),(49,4),(51,4),(14,11),(15,12);
/*!40000 ALTER TABLE `validity_period_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `validity_periods`
--

DROP TABLE IF EXISTS `validity_periods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `validity_periods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `months` int NOT NULL,
  `fee` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `validity_periods`
--

LOCK TABLES `validity_periods` WRITE;
/*!40000 ALTER TABLE `validity_periods` DISABLE KEYS */;
INSERT INTO `validity_periods` VALUES (1,5,10),(2,2,5),(3,12,20),(4,5,4),(11,54,54),(12,1,1);
/*!40000 ALTER TABLE `validity_periods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `user_accumulative_services`
--

/*!50001 DROP VIEW IF EXISTS `user_accumulative_services`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`asus`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `user_accumulative_services` AS select `partial`.`user` AS `user`,`partial`.`minutes` AS `minutes`,`partial`.`sms` AS `sms`,`partial`.`gigabytes` AS `gigabytes`,(select sum(`o2`.`total_value`) from `orders` `o2` where (`o2`.`user` = `o`.`user`) group by `o2`.`user`) AS `cost` from ((select `o`.`user` AS `user`,sum(json_extract(replace(`s`.`service_details`,'@',''),'$.minutes')) AS `minutes`,sum(json_extract(replace(`s`.`service_details`,'@',''),'$.sms')) AS `sms`,sum(json_extract(replace(`s`.`service_details`,'@',''),'$.gigabytes')) AS `gigabytes` from ((((`packages` `p` join `orders` `o` on((`p`.`package_id` = `o`.`package_id`))) join `packages_services` `ps` on((`o`.`package_id` = `ps`.`package_id`))) join `services` `s` on((`ps`.`service_id` = `s`.`service_id`))) join `activation_schedule` `activation` on((`o`.`order_id` = `activation`.`order_id`))) where ((`activation`.`activation_date` <= curdate()) and (curdate() < `activation`.`deactivation_date`)) group by `o`.`user`) `partial` join `orders` `o` on((`o`.`user` = `partial`.`user`))) group by `partial`.`user` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-16 17:24:43
