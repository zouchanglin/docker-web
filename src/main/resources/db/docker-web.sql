-- 创建数据库容器表
drop table if exists container_info;

create table if not exists container_info(
     container_id varchar(255) not null comment '容器ID',
     container_name varchar(255) not null comment '容器名称'
);