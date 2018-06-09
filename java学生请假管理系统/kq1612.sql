/*
Navicat MySQL Data Transfer

Source Server         : gcl
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : kq1612

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-06-08 21:18:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bj`
-- ----------------------------
DROP TABLE IF EXISTS `bj`;
CREATE TABLE `bj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yxid` int(11) DEFAULT NULL,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of bj
-- ----------------------------
INSERT INTO `bj` VALUES ('3', '4', 'bj1');
INSERT INTO `bj` VALUES ('4', '4', 'bj2');

-- ----------------------------
-- Table structure for `kq`
-- ----------------------------
DROP TABLE IF EXISTS `kq`;
CREATE TABLE `kq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `states` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `zj` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `times` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descs` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `kc` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `yx` int(11) DEFAULT NULL,
  `bj` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `tid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `yd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of kq
-- ----------------------------
INSERT INTO `kq` VALUES ('1', '通过', '1', '2016-10-13', '迟到', '3', '4', '3', '6', '2', null);
INSERT INTO `kq` VALUES ('2', '通过', '3', '2016-11-07', '迟到', 'kc1', '4', '3', '6', 'js1', null);

-- ----------------------------
-- Table structure for `qj`
-- ----------------------------
DROP TABLE IF EXISTS `qj`;
CREATE TABLE `qj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `yx` int(11) DEFAULT NULL,
  `bj` int(11) DEFAULT NULL,
  `states` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `btimes` date DEFAULT NULL,
  `etimes` date DEFAULT NULL,
  `s1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `s2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `s3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `s4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descs` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `result` varchar(500) COLLATE utf8_bin DEFAULT '请假成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qj
-- ----------------------------
INSERT INTO `qj` VALUES ('5', '7', '4', '3', '通过', '2016-12-21', '2016-12-25', '通过', '通过', '通过', '通过', '43225345', '/upfile/2016121822362869.doc', '6', '已撤销');
INSERT INTO `qj` VALUES ('6', '7', '4', '3', '待审核', '2018-06-01', '2018-06-30', '待审核', '待审核', '待审核', '待审核', 'asASasASasAS', '/upfile/2018060821141743.jpg', '6', '请假成功');

-- ----------------------------
-- Table structure for `stu`
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `roles` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `yx` int(11) DEFAULT NULL,
  `bj` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES ('6', '5', '5', '5', '17979879879', '分管班委', '4', '3');
INSERT INTO `stu` VALUES ('7', '6', '6', '6', '177909', '学生', '4', '3');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `roles` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `yx` int(11) DEFAULT NULL,
  `bj` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('4', '1', '1', '1', '1', '系统管理员', '1', '1');
INSERT INTO `users` VALUES ('5', '2', '2', '2', '18979080', '主管院长', '4', '3');
INSERT INTO `users` VALUES ('6', '3', '3', '3', '189809089', '辅导员', '4', '3');
INSERT INTO `users` VALUES ('7', '4', '4', '4', '177909', '班主任', '4', '3');

-- ----------------------------
-- Table structure for `yx`
-- ----------------------------
DROP TABLE IF EXISTS `yx`;
CREATE TABLE `yx` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `names` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of yx
-- ----------------------------
INSERT INTO `yx` VALUES ('4', 'yx1');
INSERT INTO `yx` VALUES ('5', 'yx2');
