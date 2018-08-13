-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

-- INSERT statements go here

COMMIT;

drop table user_info;

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

select * from user_info
where user_name = 'lordgenius'

insert into user_info (user_name, password, first_name, last_name, bio, ratings, is_sensei) values ('lordgenius', '1234', 'Gordy', 'Smith', 'I am a genius and a lord', 5, true);