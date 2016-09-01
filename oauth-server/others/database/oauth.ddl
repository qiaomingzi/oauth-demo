--
--  Oauth sql  -- MYSQL
--
DROP TABLE IF EXISTS `t_open_application`;
CREATE TABLE `t_open_application` (
  `APP_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APP_TYPE` tinyint(1) DEFAULT NULL,
  `APP_NAME` varchar(255) DEFAULT NULL,
  `APP_KEY` varchar(50) DEFAULT NULL,
  `APP_SECRET` varchar(255) DEFAULT NULL,
  `REDIRECT_URI` varchar(255) DEFAULT NULL,
  `HOME_PAGE` varchar(255) DEFAULT NULL,
  `DOMAIN` varchar(255) DEFAULT NULL,
  `LITTLE_LOGO` varchar(255) DEFAULT NULL,
  `BIG_LOGO` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `UPDATE_USER_ID` bigint(22) DEFAULT NULL,
  `CREATE_USER_ID` bigint(22) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `STATUS_TIME` date DEFAULT NULL,
  `STATUS` varchar(3) DEFAULT NULL COMMENT '001|正常 003|删除',

  resource_ids VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  trusted tinyint(1) default '0',
  autoapprove VARCHAR (255) default 'false'
  PRIMARY KEY (`APP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

Drop table  if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information TEXT,
  create_time timestamp default now(),
  archived tinyint(1) default '0',
  trusted tinyint(1) default '0',
  autoapprove VARCHAR (255) default 'false'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_access_token;
create table oauth_access_token (
  create_time timestamp default now(),
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_refresh_token;
create table oauth_refresh_token (
  create_time timestamp default now(),
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


Drop table  if exists oauth_code;
create table oauth_code (
  create_time timestamp default now(),
  code VARCHAR(255),
  authentication BLOB
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Add indexes
create index token_id_index on oauth_access_token (token_id);
create index authentication_id_index on oauth_access_token (authentication_id);
create index user_name_index on oauth_access_token (user_name);
create index client_id_index on oauth_access_token (client_id);
create index refresh_token_index on oauth_access_token (refresh_token);

create index token_id_index on oauth_refresh_token (token_id);

create index code_index on oauth_code (code);


