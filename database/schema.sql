-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

drop table if exists user_info;

create table user_info ( 
    user_name varchar(35) not null,
    password varchar(20) not null,
    first_name varchar (35) not null,
    last_name varchar (35) not null,
    bio varchar (360) not null,
    ratings int, 
    is_sensei boolean not null,

    constraint pk_user_name primary key (user_name)
);

COMMIT;