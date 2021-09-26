CREATE TABLE `user_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_name` varchar(255) NOT NULL COMMENT '用户名称',
                             `password` varchar(255) NOT NULL COMMENT '密码',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`)
);
insert into user_info(id,user_name, password) values (111,'test','MTIzNDU2');
CREATE TABLE `todo_list` (
                             `id` bigint(20) NOT NULL COMMENT '主键',
                             `status` tinyint COMMENT '完成状态,0:已完成;1:未完成',
                             `todo_list_name` varchar(255)  DEFAULT NULL COMMENT '待办名称',
                             `user_id` bigint(20) DEFAULT NULL COMMENT '组户主键',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `user_id` (`user_id`) USING BTREE
);