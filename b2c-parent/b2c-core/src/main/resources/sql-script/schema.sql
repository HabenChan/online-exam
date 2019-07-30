-- Spring Security使用的表结构
drop table if exists users;
create table users (
  username                varchar(20) primary key
  comment '用户名',
  nickname                varchar(20)  not null default ''
  comment '别名',
  gender                  char(1)      not null default ''
  comment '性别,M:男;F:女',
  password                varchar(128) not null
  comment '密码',
  pic_url                 varchar(128) not null default ''
  comment '头像url',
  account_non_expired     tinyint(1)   not null default true
  comment '账户永不过期',
  account_non_locked      tinyint(1)   not null default true
  comment '账户永不锁定',
  credentials_non_expired tinyint(1)   not null default true
  comment '凭证永不过期',
  enabled                 tinyint(1)   not null default true
  comment '账户启用状态',
  del_flag                tinyint(1)   not null default false
  comment '账户删除状态',
  mobile                  varchar(11)  not null default ''
  comment '手机',
  email                   varchar(50)  not null default ''
  comment '邮箱',
  remark                  varchar(50)  not null default ''
  comment '备注',
  create_username         varchar(20)  not null
  comment '账户创建人',
  create_time             datetime     not null default now()
  comment '账户创建时间',
  last_op_username        varchar(20)  not null
  comment '最后操作人',
  last_op_time            datetime     not null default now()
  comment '最后操作时间'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '用户表';

drop table if exists users_authorities;
create table users_authorities (
  authority      varchar(18) not null primary key
  comment '权限',
  authority_name varchar(18) not null default ''
  comment '权限名称'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '权限表';

drop table if exists authorities;
create table authorities (
  username  varchar(20) not null
  comment '关联users表的username',
  authority varchar(18) not null
  comment '关联权限表的authority'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '用户权限表';

drop table if exists groups;
create table groups (
  id         int primary key auto_increment
  comment '主键id',
  group_name varchar(8) not null
  comment '组名'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '权限组表';

drop table if exists group_authorities;
create table group_authorities (
  group_id  int         not null
  comment '关联groups表id',
  authority varchar(18) not null
  comment '关联权限表的authority'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '权限组权限表';

drop table if exists group_members;
create table group_members (
  group_id int         not null
  comment '关联groups表id',
  username varchar(20) not null
  comment '关联users表username'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '权限组用户表';

drop table if exists persistent_logins;
create table persistent_logins (
  series    varchar(64) primary key,
  username  varchar(20) not null,
  token     varchar(64) not null,
  last_used timestamp   not null
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '记住登录用户表';

drop table if exists menus;
create table menus(
  id int primary key auto_increment comment '主键id',
  parent_id int not null default 0 comment '父id',
  has_sub_menu tinyint(1) not null default false comment '是否有子菜单',
  name varchar(20) not null unique comment '名称',
  permission varchar(20) not null default '' comment '权限',
  path varchar(128) not null default '' comment 'path',
  should_show boolean not null default true comment '是否应显示',
  icon varchar(30) not null default '' comment '图标类名',
  create_time datetime default now() comment '创建时间',
  op_time datetime default now() comment '最后操作时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = '菜单表';