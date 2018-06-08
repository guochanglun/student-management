# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: kq1612
# ------------------------------------------------------
# Server version 5.0.87-community-nt

DROP DATABASE IF EXISTS `kq1612`;
CREATE DATABASE `kq1612` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kq1612`;

#
# Source for table bj
#

DROP TABLE IF EXISTS `bj`;
CREATE TABLE `bj` (
  `id` int(11) NOT NULL auto_increment,
  `yxid` int(11) default NULL,
  `names` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table bj
#

LOCK TABLES `bj` WRITE;
/*!40000 ALTER TABLE `bj` DISABLE KEYS */;
INSERT INTO `bj` VALUES (5,7,'璁＄畻105鐝?');
INSERT INTO `bj` VALUES (6,8,'澶栬106鐝?');
/*!40000 ALTER TABLE `bj` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table kq
#

DROP TABLE IF EXISTS `kq`;
CREATE TABLE `kq` (
  `id` int(11) NOT NULL auto_increment,
  `states` varchar(50) collate utf8_bin default NULL,
  `zj` varchar(50) collate utf8_bin default NULL,
  `times` varchar(50) collate utf8_bin default NULL,
  `descs` varchar(50) collate utf8_bin default NULL,
  `kc` varchar(50) collate utf8_bin default NULL,
  `yx` int(11) default NULL,
  `bj` int(11) default NULL,
  `sid` int(11) default NULL,
  `tid` varchar(50) collate utf8_bin default NULL,
  `yd` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table kq
#

LOCK TABLES `kq` WRITE;
/*!40000 ALTER TABLE `kq` DISABLE KEYS */;
/*!40000 ALTER TABLE `kq` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table qj
#

DROP TABLE IF EXISTS `qj`;
CREATE TABLE `qj` (
  `id` int(11) NOT NULL auto_increment,
  `sid` int(11) default NULL,
  `yx` int(11) default NULL,
  `bj` int(11) default NULL,
  `states` varchar(50) collate utf8_bin default NULL,
  `btimes` date default NULL,
  `etimes` date default NULL,
  `s1` varchar(50) collate utf8_bin default NULL,
  `s2` varchar(50) collate utf8_bin default NULL,
  `s3` varchar(50) collate utf8_bin default NULL,
  `s4` varchar(50) collate utf8_bin default NULL,
  `descs` varchar(500) collate utf8_bin default NULL,
  `url` varchar(500) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table qj
#

LOCK TABLES `qj` WRITE;
/*!40000 ALTER TABLE `qj` DISABLE KEYS */;
INSERT INTO `qj` VALUES (6,8,7,5,'閫氳繃','2018-04-01','2018-04-03','閫氳繃','閫氳繃','閫氳繃','閫氳繃',X'E7949FE79785',X'2F757066696C652F323031383034313632333134333133302E6A7067');
/*!40000 ALTER TABLE `qj` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table stu
#

DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) collate utf8_bin default NULL,
  `password` varchar(50) collate utf8_bin default NULL,
  `realname` varchar(50) collate utf8_bin default NULL,
  `tel` varchar(50) collate utf8_bin default NULL,
  `roles` varchar(50) collate utf8_bin default NULL,
  `yx` int(11) default NULL,
  `bj` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table stu
#

LOCK TABLES `stu` WRITE;
/*!40000 ALTER TABLE `stu` DISABLE KEYS */;
INSERT INTO `stu` VALUES (8,'1001','12345','鐜嬪悓瀛?','189234423','瀛︾敓',7,5);
INSERT INTO `stu` VALUES (9,'1002','12345','鏉庡悓瀛?','1535345','鍒嗙鐝',7,5);
/*!40000 ALTER TABLE `stu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) collate utf8_bin default NULL,
  `password` varchar(50) collate utf8_bin default NULL,
  `realname` varchar(50) collate utf8_bin default NULL,
  `tel` varchar(50) collate utf8_bin default NULL,
  `roles` varchar(50) collate utf8_bin default NULL,
  `yx` int(11) default NULL,
  `bj` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'admin','12345','1','1','绯荤粺绠＄悊鍛?',1,1);
INSERT INTO `users` VALUES (8,'user1','12345','寮犱笁','18888888','鐝富浠?',7,5);
INSERT INTO `users` VALUES (9,'user2','12345','鏉庡洓','1534535345','杈呭鍛?',7,5);
INSERT INTO `users` VALUES (10,'user3','12345','鐜嬩簲','138345345','涓荤闄㈤暱',7,5);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table yx
#

DROP TABLE IF EXISTS `yx`;
CREATE TABLE `yx` (
  `id` int(11) NOT NULL auto_increment,
  `names` varchar(50) collate utf8_bin default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Dumping data for table yx
#

LOCK TABLES `yx` WRITE;
/*!40000 ALTER TABLE `yx` DISABLE KEYS */;
INSERT INTO `yx` VALUES (7,'璁＄畻鏈哄闄?');
INSERT INTO `yx` VALUES (8,'澶栬瀛﹂櫌');
/*!40000 ALTER TABLE `yx` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
