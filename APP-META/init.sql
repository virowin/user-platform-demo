 CREATE DATABASE `user_platform`;
 SET character_set_client = utf8;
 use user_platform;
 set names utf8;
 ;CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` char(100) NOT NULL DEFAULT '' COMMENT 'email',
  `password` char(64) NOT NULL DEFAULT '' COMMENT 'md5 password',
  `nickname` char(64) NOT NULL DEFAULT '' COMMENT 'nickname',
  `dateline` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1:deleted',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
grant all privileges on *.* to 'virowin'@'%' identified by '123456';
grant all privileges on *.* to 'virowin'@'localhost' identified by '123456';
flush privileges;