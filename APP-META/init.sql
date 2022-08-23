 CREATE DATABASES `user_platform`;
 use user_platform;
 set names utf8;
 ;CREATE TABLE `user` IF NOT EXISTS (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` char(100) NOT NULL DEFAULT '' COMMENT 'email',
  `password` char(64) NOT NULL DEFAULT '' COMMENT 'md5 password',
  `nickname` char(64) NOT NULL DEFAULT '' COMMENT 'nickname',
  `dateline` datetime DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1:deleted',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;