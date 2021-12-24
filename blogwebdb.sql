/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : blogwebdb

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-12-23 10:57:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `articleid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `publishtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `userid` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'publish user',
  PRIMARY KEY (`articleid`),
  KEY `userid_fk` (`userid`),
  CONSTRAINT `userid_fk` FOREIGN KEY (`userid`) REFERENCES `t_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', 'Car title ', 'TRent color: silver;', '2021-12-22 20:58:56', 'Bill123');
INSERT INTO `t_article` VALUES ('2', 'loginuser', 'gggg1111', '2021-12-15 07:37:51', 'beike1');
INSERT INTO `t_article` VALUES ('4', 'Editing Article Everyday', 'Editing kgfdjgfd', '2021-12-19 10:46:29', 'beike1');
INSERT INTO `t_article` VALUES ('5', 'yvon', '  kathmandu', '2021-12-12 11:19:33', 'yvon');
INSERT INTO `t_article` VALUES ('13', 'title', 'address', '2021-12-21 09:19:18', 'beike1');
INSERT INTO `t_article` VALUES ('14', 'holy', 'holllllier than thou', '2021-12-21 22:19:59', 'beike1');
INSERT INTO `t_article` VALUES ('16', 'a new color: silver;color: silver;', 'a article', '2021-12-22 20:56:07', 'Bill123');

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `commentid` int NOT NULL AUTO_INCREMENT,
  `textcomment` text,
  `userid` varchar(300) DEFAULT NULL,
  `articleid` int DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentid`),
  KEY `comment_fk` (`articleid`),
  CONSTRAINT `comment_fk` FOREIGN KEY (`articleid`) REFERENCES `t_article` (`articleid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', 'dsfsdgsdgsdgs', 'beike1', '4', '2021-12-14 18:24:19');
INSERT INTO `t_comment` VALUES ('2', 'fdggdrgdfgdg', 'beike1', '4', '2021-12-14 18:26:09');
INSERT INTO `t_comment` VALUES ('3', 'iiyiyiyiyiy', 'beike1', '4', '2021-12-14 18:41:50');
INSERT INTO `t_comment` VALUES ('4', 'sgsdgdgsdgd', 'beike1', '2', '2021-12-14 18:44:54');
INSERT INTO `t_comment` VALUES ('5', 'wtetwtwwtetwetw', 'yvon', '2', '2021-12-14 18:46:24');
INSERT INTO `t_comment` VALUES ('6', 'Yvon commenting this article', 'yvon', '4', '2021-12-14 19:02:03');
INSERT INTO `t_comment` VALUES ('7', 'dsfsdgsdgsdgs', 'yvon', '5', '2021-12-14 19:08:18');
INSERT INTO `t_comment` VALUES ('8', 'dsfsdgsdgsdgs', 'yvon', '5', '2021-12-14 19:08:26');
INSERT INTO `t_comment` VALUES ('13', '6urtyuru', 'beike1', '2', '2021-12-14 21:18:17');
INSERT INTO `t_comment` VALUES ('21', 'polhyfrd', 'Bill123', '1', '2021-12-16 15:59:15');
INSERT INTO `t_comment` VALUES ('23', 'sddsddsd', 'beike1', '13', '2021-12-21 13:45:05');
INSERT INTO `t_comment` VALUES ('24', 'ewewewew', 'Bill123', '2', '2021-12-21 14:06:05');
INSERT INTO `t_comment` VALUES ('25', 'ewewewewe', 'Bill123', '4', '2021-12-21 14:06:24');
INSERT INTO `t_comment` VALUES ('26', '433444', 'Bill123', '1', '2021-12-21 14:14:32');
INSERT INTO `t_comment` VALUES ('27', 'hjjjhhll', 'trrrr', '13', '2021-12-22 18:11:40');

-- ----------------------------
-- Table structure for `t_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_content`;
CREATE TABLE `t_content` (
  `contentid` int NOT NULL AUTO_INCREMENT,
  `articleid` int DEFAULT NULL,
  `textcontent` text,
  `photourl` text,
  `orderid` int DEFAULT NULL,
  `isrecomand` bit(1) DEFAULT NULL,
  PRIMARY KEY (`contentid`),
  KEY `articleid_fk` (`articleid`),
  CONSTRAINT `articleid_fk` FOREIGN KEY (`articleid`) REFERENCES `t_article` (`articleid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_content
-- ----------------------------
INSERT INTO `t_content` VALUES ('1', '1', 'Impedit atque vel, tempore at cum eos non, commodi autem. Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum! Vitae possimus facilis a quae, ullam molestias laudantium tenetur exercitationem, itaque doloremque id nulla laborum corrupti, cupiditate sit sapiente? Perferendis culpa, repellat nesciunt sapiente voluptatibus illo at laboriosam blanditiis officia?', 'beautiful.jpg', '1', '');
INSERT INTO `t_content` VALUES ('2', '1', 'Adipisicing elit. Temporibus, perferendis labore quisquam exercitationem impedit atque vel, tempore at cum eos non, commodi autem. Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum! Vitae possimus facilis a quae, ullam molestias laudantium tenetur exercitationem, itaque doloremque id nulla laborum corrupti, cupiditate sit sapiente? Perferendis culpa, repellat nesciunt sapiente voluptatibus illo at laboriosam blanditiis officia?', 'sofitel.jpg', '2', '');
INSERT INTO `t_content` VALUES ('3', '4', 'Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum! Vitae possimus facilis a quae, ullam molestias laudantium tenetur exercitationem, itaque doloremque id nulla laborum corrupti, cupiditate sit sapiente? Perferendis culpa, repellat nesciunt sapiente voluptatibus illo at laboriosam blanditiis officia?', 'blog-bg-01.jpg', '1', '');
INSERT INTO `t_content` VALUES ('5', '5', 'Lorem, ipsum dolor sit amet consectetur adipisicing elit. Temporibus, perferendis labore quisquam exercitationem impedit atque vel, tempore at cum eos non, commodi autem. Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum!', 'pizza.gif', '1', '');
INSERT INTO `t_content` VALUES ('6', '5', ' Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum!', 'senew.png', '2', '');
INSERT INTO `t_content` VALUES ('10', '4', ' Magnam earum magni veniam, doloribus provident harum. Quidem totam omnis id corporis excepturi asperiores ratione beatae. Esse, repellat velit ipsam ipsa asperiores amet earum tempore expedita nulla odio eveniet ut ratione exercitationem culpa fuga a temporibus ipsum!', 'pexels-pixabay-206648.jpg', '3', null);
INSERT INTO `t_content` VALUES ('22', '13', 'lorem lorem lorem lorem lorem lorem loremlorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem  lorem lorem lorem lorem lorem lorem lorem', 'giphy.webp', '1', null);
INSERT INTO `t_content` VALUES ('23', '14', 'asafsafaaffff afsaffsaaf safsaf sasaafsfsa', 'neo-sakura-success.png', '1', null);
INSERT INTO `t_content` VALUES ('24', '2', 'lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem lorem', 'login_pic.png', '1', null);
INSERT INTO `t_content` VALUES ('26', '16', 'aaaa lorem lorem lorem lorem lorem lorem lorem loooolorem lorem lorem lorem lorem lorem', 'iphone.jpg', '1', null);
INSERT INTO `t_content` VALUES ('27', '1', 'title Impedit atque vel, tempore at cum eos non, commodi autem. Magnam earum magni veniam, d', null, '2', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(300) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `pwd` varchar(200) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'register time',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('beike1', 'beike john', '000000', 'beikeMAIN.com', '976566556', '2021-12-14 19:33:18');
INSERT INTO `t_user` VALUES ('Bill123', 'Apedo Bill', '1111', 'ononon@gmail.com', '343344433', '2021-12-22 17:52:35');
INSERT INTO `t_user` VALUES ('save', 'Bill123', '1234', 'sdsd@gmail.om', '132312313', '2021-12-21 09:57:59');
INSERT INTO `t_user` VALUES ('trrrr', 'tree treww', '12345', 'aass@gmail.com', '12313333', '2021-12-22 18:09:14');
INSERT INTO `t_user` VALUES ('weeeee', 'ewewewew', '1234', 'sasasa@gmail', '544554454545', '2021-12-21 13:31:56');
INSERT INTO `t_user` VALUES ('yvon', 'yvon', '1234', 'yvon@gmail.om', '124353', '2021-12-12 11:15:56');
