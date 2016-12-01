/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : mvn

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-10-18 16:48:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_score
-- ----------------------------
DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE `tb_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '0:男生，1女生',
  `score` double(10,0) NOT NULL DEFAULT '0' COMMENT '成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_score
-- ----------------------------
INSERT INTO `tb_score` VALUES ('1', 'Tom', '0', '10');
INSERT INTO `tb_score` VALUES ('2', 'Cat', '0', '7');
INSERT INTO `tb_score` VALUES ('3', 'Jucy', '1', '9');
INSERT INTO `tb_score` VALUES ('4', 'Caselin', '1', '10');

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '名字',
  `content` varchar(255) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES ('2', '1', '111');
INSERT INTO `tb_test` VALUES ('4', 'newName', 'newContent');
INSERT INTO `tb_test` VALUES ('6', 'new name', '1L');
