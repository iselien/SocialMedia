DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_account` varchar(50) NOT NULL COMMENT '用户帐号',
  `user_password` varchar(100) NOT NULL COMMENT '用户密码',
  `user_nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `user_introduction` text NOT NULL COMMENT '用户自述',
  `user_email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `user_image` varchar(150) DEFAULT NULL COMMENT '用户头像',
  `user_status` int(11) NOT NULL COMMENT '用户状态:0=正常,1=冻结,2=注销',
  `user_create_time` datetime NOT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 DEFAULT CHARSET = utf8;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES
  (1, '130283492192', MD5(123), 'yove', 'hello everyone, my name is yove', 'yovelas@163.com', '17679391061', '130283492192_profile_image', 0, now()),
  (2, '129347190123', MD5(1234), 'abc', 'hello everyone, my name is abc', 'yovelas@gmail.com', '17679391063', '129347190123_profile_image', 0, now())
UNLOCK TABLES; 