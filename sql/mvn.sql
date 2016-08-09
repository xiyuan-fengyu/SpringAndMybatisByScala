/*
Navicat MySQL Data Transfer
Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : mvn
Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001
Date: 2016-06-30 17:16:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES ('1', '0', '000');
INSERT INTO `tb_test` VALUES ('2', '1', '111');
INSERT INTO `tb_test` VALUES ('4', 'newName', 'newContent');