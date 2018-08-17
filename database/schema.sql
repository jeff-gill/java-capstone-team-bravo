-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;
drop table if exists user_info;
drop table if exists subjects;
drop table if exists user_subjects;
drop table if exists class;
drop table if exists user_class;
drop sequence if exists seq_class_id;
drop sequence if exists seq_subject_id;

CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

create table user_info ( 
    user_name varchar(35) not null UNIQUE,
    password varchar(20) not null,
    --salt varchar(255) NOT NULL,
    first_name varchar (35) not null,
    last_name varchar (35) not null,
    bio varchar (360) not null,
    ratings int, 
    is_sensei boolean not null,
    email varchar(35) not null,
    phone varchar(20),
    profile_image varchar(20),
    interests varchar (320), 

    constraint pk_user_name primary key (user_name)
);

create sequence seq_class_id;

create table subjects (
	class_id integer default nextval('seq_class_id'),
	subject_name varchar(35) not null,
	location varchar(50) not null,
	event_date date not null,
	event_start_time varchar(10) not null,
	event_end_time varchar(10) not null,
	cost float not null,
	available_slots integer not null,
	description varchar(360) not null,
	
	constraint pk_class_id primary key (class_id),
);

create table user_class (
	class_id integer not null,
	user_name varchar(35) not null,
	
	constraint fk_class_id foreign key (class_id) references class (class_id) on delete cascade,
	constraint fk_user_name foreign key (user_name) references user_info (user_name) on delete cascade
);

COMMIT;