-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS app_user;
drop table if exists user_info;
drop table if exists subjects;
drop table if exists user_subjects;
drop table if exists messaging;
drop table if exists reviews;
drop sequence if exists seq_class_id;
drop sequence if exists seq_message_id;
drop sequence if exists seq_review_id;

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
    first_name varchar (35) not null,
    last_name varchar (35) not null,
    bio varchar (360) not null,
    ratings int, 
    is_sensei boolean not null,
    email varchar(35) not null,
    phone varchar(20),
    profile_image varchar(45),
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
	
	constraint pk_class_id primary key (class_id)
);

create table user_subjects (
	class_id integer not null,
	user_name varchar(35) not null,
	
	constraint fk_class_id foreign key (class_id) references subjects (class_id) on delete cascade,
	constraint fk_user_name foreign key (user_name) references user_info (user_name) on delete cascade
);

create sequence seq_message_id;

create table messaging (
	message_id integer default nextval('seq_message_id'),
	message_to varchar(35) not null,
	message_from varchar(35) not null,
	message_subject varchar(55) not null,
	message_body varchar (360) not null,
	message_date_sent date not null,
	
	constraint pk_message_id primary key (message_id)
);

create sequence seq_review_id;

create table reviews (
        review_id integer default nextval('seq_review_id'),
        reviewee varchar(32) not null,
        reviewer varchar(32) not null,
        panda_rating int not null,
        review varchar (360),
        
        constraint pk_review_id primary key (review_id),
        constraint fk_reviewee foreign key (reviewee) references user_info (user_name) on delete cascade
);

COMMIT;