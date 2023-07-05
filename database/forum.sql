/*
Navicat MySQL Data Transfer

Source Server         : forum
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : forum

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2023-06-03 13:09:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `forum_article`
-- ----------------------------
DROP TABLE IF EXISTS `forum_article`;
CREATE TABLE `forum_article` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `content` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '文章简介',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '文章首图',
  `published` bit(1) DEFAULT NULL COMMENT '是否公开',
  `commentabled` bit(1) DEFAULT NULL COMMENT '是否开启评论',
  `page_view` bigint(20) DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) DEFAULT '0' COMMENT '点赞数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` bit(1) DEFAULT b'0' COMMENT '审核状态',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_article
-- ----------------------------


-- ----------------------------
-- Table structure for `forum_article_tag`
-- ----------------------------
DROP TABLE IF EXISTS `forum_article_tag`;
CREATE TABLE `forum_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `aid` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `tag_id` bigint(20) DEFAULT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_article_type`
-- ----------------------------
DROP TABLE IF EXISTS `forum_article_type`;
CREATE TABLE `forum_article_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `aid` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `type_id` bigint(20) DEFAULT NULL COMMENT '类型ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forum_article_type
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_article_user`
-- ----------------------------
DROP TABLE IF EXISTS `forum_article_user`;
CREATE TABLE `forum_article_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `aid` bigint(20) DEFAULT NULL COMMENT '文章ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_article_user
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_comment`
-- ----------------------------
DROP TABLE IF EXISTS `forum_comment`;
CREATE TABLE `forum_comment` (
  `com_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `aid` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `uid` bigint(20) DEFAULT NULL COMMENT '评论者ID',
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '评论父ＩＤ',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` bit(1) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_tag`
-- ----------------------------
DROP TABLE IF EXISTS `forum_tag`;
CREATE TABLE `forum_tag` (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_type`
-- ----------------------------
DROP TABLE IF EXISTS `forum_type`;
CREATE TABLE `forum_type` (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类型ID',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of forum_type
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_user`
-- ----------------------------
DROP TABLE IF EXISTS `forum_user`;
CREATE TABLE `forum_user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名称',
  `account` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户账户',
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户密码',
  `age` int(11) DEFAULT NULL COMMENT '用户年龄',
  `gender` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户性别',
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户邮箱',
  `mobile` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户号码',
  `avator` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户头像',
  `description` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户简介',
  `admin` bit(1) DEFAULT b'0' COMMENT '是否是管理员',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of forum_user
-- ----------------------------
INSERT INTO `forum_user` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '17', '男', '123@qq.com', '12121145123', 'https://img0.baidu.com/it/u=1091210682,206783907&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1684602000&t=1813754cb45a25a646263c4b3a711514', '测试管理员', '');
