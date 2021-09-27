CREATE TABLE `user_info` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `user_name` varchar(255) NOT NULL COMMENT '用户名称',
                             `password` varchar(255) NOT NULL COMMENT '密码',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`)
);
insert into user_info(id,user_name, password) values (111,'test','MTIzNDU2');
insert into user_info(id,user_name, password) values (1112,'test2','MTIzNDU2');
CREATE TABLE `todo_list` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `status` tinyint COMMENT '完成状态,0:已完成;1:未完成',
                             `todo_list_name` varchar(255)  DEFAULT NULL COMMENT '待办名称',
                             `user_id` bigint(20) DEFAULT NULL COMMENT '组户主键',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             KEY `user_id` (`user_id`) USING BTREE
);
insert into todo_list(id, status, todo_list_name, user_id) values (123,1,'test用户数据',111);
insert into todo_list(id, status, todo_list_name, user_id) values (12333,1,'test2用户数据1',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12334,1,'test2用户数据2',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12335,1,'test2用户数据3',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12336,1,'test2用户数据4',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12337,1,'test2用户数据5',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12338,1,'test2用户数据6',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (12339,1,'test2用户数据7',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (123311,1,'test2用户数据8',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (123312,1,'test2用户数据9',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (123313,1,'test2用户数据11',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (1233123,1,'test2用户数据12',1112);
insert into todo_list(id, status, todo_list_name, user_id) values (1233222,1,'test2用户数据13',1112);