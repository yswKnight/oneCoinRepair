/*
Navicat MySQL Data Transfer

Source Server         : wangys
Source Server Version : 50550
Source Host           : localhost:3306
Source Database       : onecoinrepair

Target Server Type    : MYSQL
Target Server Version : 50550
File Encoding         : 65001

Date: 2017-05-04 16:30:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for c_org
-- ----------------------------
DROP TABLE IF EXISTS `c_org`;
CREATE TABLE `c_org` (
  `id` varchar(64) NOT NULL,
  `org_name` varchar(20) NOT NULL,
  `org_address` varchar(30) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `invalid_time` datetime DEFAULT NULL COMMENT 'ʧЧʱ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_org
-- ----------------------------
INSERT INTO `c_org` VALUES ('402880f35bc7345b015bc73e70ee0000', '上海微企科技股份有限公司', '延安西路1146号', '2017-05-02 11:40:09', '2017-05-02 11:40:09', null);
INSERT INTO `c_org` VALUES ('402880f35bc7345b015bc74038ed0001', '阿里巴巴', '浙江杭州', '2017-05-02 11:42:06', '2017-05-02 11:42:06', null);
INSERT INTO `c_org` VALUES ('402880f35bc7345b015bc740ee920002', '腾讯', '广州天河', '2017-05-02 11:42:53', '2017-05-02 11:42:53', null);
INSERT INTO `c_org` VALUES ('402880f35bc7345b015bc7438c6d0004', '华为科技有限公司', '武汉光谷软件园', '2017-05-02 11:45:44', '2017-05-02 11:45:44', null);
INSERT INTO `c_org` VALUES ('402880f35bcc5500015bcc56230b0000', '上海致宇科技有限公司', '武汉光谷软件园', '2017-05-03 11:24:08', '2017-05-03 11:24:08', null);

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '?û???',
  `create_time` datetime NOT NULL COMMENT '????ʱ??',
  `update_time` datetime NOT NULL COMMENT '????ʱ?? ',
  `invalid_time` datetime DEFAULT NULL COMMENT 'ʧЧʱ??',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_role
-- ----------------------------
INSERT INTO `c_role` VALUES ('402880f35bb25534015bb255a9760000', 'Boss', '2017-04-28 10:13:22', '2017-04-28 10:13:22', null);
INSERT INTO `c_role` VALUES ('402880f35bb25534015bb2569c680001', 'SuperAdmin', '2017-04-28 10:14:32', '2017-04-28 10:14:32', null);
INSERT INTO `c_role` VALUES ('402880f35bb25b5f015bb26b8e770001', 'Administrator', '2017-04-28 10:37:24', '2017-04-28 10:37:24', null);

-- ----------------------------
-- Table structure for c_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_role_menu`;
CREATE TABLE `c_role_menu` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(64) NOT NULL,
  `menu_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '?û???',
  `create_time` datetime NOT NULL COMMENT '????ʱ??',
  `update_time` datetime NOT NULL COMMENT '????ʱ?? ',
  `invalid_time` datetime DEFAULT NULL COMMENT 'ʧЧʱ??',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for c_test
-- ----------------------------
DROP TABLE IF EXISTS `c_test`;
CREATE TABLE `c_test` (
  `id` varchar(64) NOT NULL,
  `sheet_name` varchar(255) DEFAULT NULL,
  `color_name` varchar(255) DEFAULT NULL,
  `title_name` varchar(255) DEFAULT NULL,
  `clo1` varchar(255) DEFAULT NULL,
  `clo2` varchar(255) DEFAULT NULL,
  `clo3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_test
-- ----------------------------

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '?û???',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '????',
  `open_id` varchar(100) DEFAULT NULL,
  `user_ch_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '????????',
  `role_id` varchar(64) DEFAULT NULL,
  `org_id` varchar(64) DEFAULT NULL,
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '?ֻ?????',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '????????',
  `user_type` int(2) DEFAULT NULL,
  `channel_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '????id',
  `activity` int(11) DEFAULT NULL COMMENT '?Ƿ????0??? 1??ֹͣ??',
  `create_time` datetime NOT NULL COMMENT '????ʱ??',
  `update_time` datetime NOT NULL COMMENT '????ʱ?? ',
  `invalid_time` datetime DEFAULT NULL COMMENT 'ʧЧʱ??',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_user
-- ----------------------------
INSERT INTO `c_user` VALUES ('402880f35bae207f015bae29c1170000', 'Knight', '123321', null, '骑士', '402880f35bb25b5f015bb26b8e770001', '402880f35bc7345b015bc74038ed0001', '13545693466', 'knight@cava.com.cn', null, null, null, '2017-04-27 14:47:03', '2017-04-27 14:47:03', null);
INSERT INTO `c_user` VALUES ('402880f35bae207f015bae2c41290002', 'Knight', '123321', null, '勒布朗詹姆斯', '402880f35bb25534015bb2569c680001', '402880f35bc7345b015bc74038ed0001', '13545693466', 'lebron@cava.com.cn', null, null, null, '2017-04-27 14:49:47', '2017-04-27 14:49:47', null);
INSERT INTO `c_user` VALUES ('402880f35bae84dc015bae84ebc30000', 'wangysKnight', '1234321', null, '王永圣', '402880f35bb25534015bb255a9760000', '402880f35bc7345b015bc73e70ee0000', '17671658823', 'wangys@visionet.com.cn', null, null, null, '2017-04-27 16:26:38', '2017-04-27 16:26:38', null);
INSERT INTO `c_user` VALUES ('402880f35bb25b5f015bb2802afa0002', 'kingjames', '1234321', null, '詹皇', '402880f35bb25534015bb255a9760000', '402880f35bc7345b015bc740ee920002', '17666666666', 'kingjames@gmail.com', null, null, null, '2017-04-28 10:59:55', '2017-04-28 10:59:55', null);
INSERT INTO `c_user` VALUES ('402880f35bc8a4ee015bc8a5c3250000', 'asheheSf', '123456', null, '小C', '402880f35bb25b5f015bb26b8e770001', '402880f35bc7345b015bc740ee920002', '17666666666', 'kingjames@gmail.com', null, null, null, '2017-05-02 18:12:26', '2017-05-02 18:12:26', null);
INSERT INTO `c_user` VALUES ('402880f35bc8a766015bc8a8ff3c0000', 'ouwen', '123456', null, '欧文', '402880f35bb25b5f015bb26b8e770001', '402880f35bc7345b015bc7438c6d0004', '17666666666', 'ouwen@gmail.com', null, null, null, '2017-05-02 18:15:58', '2017-05-02 18:15:58', null);
INSERT INTO `c_user` VALUES ('402880f35bcbed0a015bcbf22f560000', 'love', '654321', null, '乐福', '402880f35bb25b5f015bb26b8e770001', '402880f35bc7345b015bc7438c6d0004', '17666666687', 'love@gmail.com', null, null, null, '2017-05-03 09:34:58', '2017-05-03 09:34:58', null);
INSERT INTO `c_user` VALUES ('402880f35bcd1f43015bcd218dee0000', 'mayun', '654321', null, '马云', '402880f35bb25534015bb255a9760000', '402880f35bc7345b015bc74038ed0001', '17666666687', 'mayun@gmail.com', null, null, null, '2017-05-03 15:06:19', '2017-05-03 15:06:19', null);

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(64) NOT NULL,
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '?û???',
  `create_time` datetime NOT NULL COMMENT '????ʱ??',
  `update_time` datetime NOT NULL COMMENT '????ʱ?? ',
  `invalid_time` datetime DEFAULT NULL COMMENT 'ʧЧʱ??',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_user_role
-- ----------------------------
