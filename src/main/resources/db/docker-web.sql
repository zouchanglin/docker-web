-- 创建数据库容器表
drop table if exists container_info;

create table if not exists container_info(
     container_id varchar(255) not null comment '容器ID',
     container_name varchar(255) not null comment '容器名称'
);

drop table if exists CONTAINER_CONFIG;
create table CONTAINER_CONFIG
(
    CONTAINER_ID   VARCHAR(255) not null,
    HOST_PORT      INT,
    CONTAINER_PORT INT,
    HOST_PATH      VARCHAR(50),
    CONTAINER_PATH VARCHAR(50),
    DISK_SIZE      INT,
    MEMORY_SIZE    FLOAT DEFAULT 1.0,
    CPU_SHARE      TINYINT,
    ENV_K          VARCHAR(50),
    ENV_V          VARCHAR(50),
    IMAGE          VARCHAR(255) not null,
    CONTAINER_NAME VARCHAR(255) not null,
    constraint CONTAINER_CONFIG_PK
        primary key (CONTAINER_ID)
);