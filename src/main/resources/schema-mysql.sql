-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jdbc
-- ------------------------------------------------------
-- Server version	8.0.25

--
-- Table structure for table `jdbc_account`
--
DROP TABLE IF EXISTS `jdbc_account`;
CREATE TABLE `jdbc_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号Id',
  `name` varchar(128) NOT NULL COMMENT '账号名',
  `balance` decimal(15,2) NOT NULL COMMENT '账号余额',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '账号状态。1表示启用，0表示禁用',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户更新时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `jdbc_user`
--
DROP TABLE IF EXISTS `jdbc_user`;
CREATE TABLE `jdbc_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `name` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(45) NOT NULL COMMENT '用户密码',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `jdbc_role`
--
DROP TABLE IF EXISTS `jdbc_role`;
CREATE TABLE `jdbc_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `name` varchar(128) NOT NULL COMMENT '角色名',
  `desc` varchar(256) COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Table structure for table `jdbc_user_role`
--
DROP TABLE IF EXISTS `jdbc_user_role`;
CREATE TABLE `jdbc_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色Id',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `role_id` bigint(20) NOT NULL COMMENT '角色Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;