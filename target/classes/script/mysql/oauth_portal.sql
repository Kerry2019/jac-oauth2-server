drop database  if exists `oauth_portal`;
CREATE DATABASE `oauth_portal` charset utf8;
use `oauth_portal`;

DROP TABLE IF EXISTS oauth_client;
CREATE TABLE oauth_client (
    client_id VARCHAR(100) NOT NULL,
    client_secret VARCHAR(100) NOT NULL,
    authorized_grant_types VARCHAR(100),
    web_server_redirect_uri VARCHAR(100),
    access_token_validity INT,
    refresh_token_validity INT,
    client_description VARCHAR(1000),
    creation_date DATETIME
)  ENGINE=INNODB ROW_FORMAT=DYNAMIC;

insert into oauth_client(client_id,client_secret,authorized_grant_types,web_server_redirect_uri,access_token_validity,refresh_token_validity,client_description,creation_date)
values('baidu_client','baidu_secret','password,implicit,authorization_code','https://www.baidu.com',36000,72000,'百度',now());

insert into oauth_client(client_id,client_secret,authorized_grant_types,web_server_redirect_uri,access_token_validity,refresh_token_validity,client_description,creation_date)
values('definesys_client','definesys_secret','password,implicit,authorization_code','https://definesys.cn',36000,72000,'得帆',now());



DROP TABLE IF EXISTS oauth_config;
CREATE TABLE oauth_config (
    login_page VARCHAR(100) NOT NULL
)  ENGINE=INNODB ROW_FORMAT=DYNAMIC;

insert into oauth_config(login_page)values ('loginBlack');