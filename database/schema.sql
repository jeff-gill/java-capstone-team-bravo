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
    profile_image varchar(35) not null,
    is_sensei boolean not null,

    constraint pk_user_name primary key (user_name)
);

create table subjects (
	subject_name varchar(35) not null unique,
	
	constraint pk_subject_name primary key (subject_name)
);

create table user_subjects (
	user_name varchar(35) not null,
	subject_name varchar(35) not null,
	
	constraint fk_user_name foreign key (user_name) references user_info (user_name),
	constraint fk_subject_name foreign key (subject_name) references subjects (subject_name)
);

create sequence seq_class_id;

create table class (
	class_id integer default nextval('seq_class_id'),
	subject_name varchar(35) not null,
	location varchar(50) not null,
	event_date date not null,
	event_time time not null,
	available_slots integer not null,
	description varchar(360) not null,
	
	constraint pk_class_id primary key (class_id),
	constraint fk_subject_name foreign key (subject_name) references subjects (subject_name)
);

create table user_class (
	class_id integer not null,
	user_name varchar(35) not null,
	
	constraint fk_class_id foreign key (class_id) references class (class_id),
	constraint fk_user_name foreign key (user_name) references user_info (user_name)
);

COMMIT;