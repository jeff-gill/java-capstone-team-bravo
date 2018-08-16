-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

insert into user_info (user_name, password, first_name, last_name, bio, ratings, is_sensei, email, phone, profile_image, interests) values ('lordgenius', '1234', 'Gordy', 'Smith', 'Lorem ipsum dolor amet mlkshk venmo semiotics kitsch, fixie cliche ramps biodiesel iceland farm-to-table pitchfork la croix pinterest XOXO. Mustache VHS taiyaki green juice, small batch pok pok blue bottle pug aesthetic tilde trust fund locavore vape.', 5, true, 'gordy@swordfighting.com', '614-555-3456', 'sensei', 'zombies');

insert into subjects (subject_id, subject_name) values (1, 'SWORD FIGHTING');

insert into class (subject_id, location, event_date, event_start_time, event_end_time, cost, available_slots, description) values ('1', 'Goodale Park', '10/25/2018', '10:00', '11:00', 25.0, 4, 'Learn to Slash Zombies');

insert into user_subjects (user_name, subject_id) values ('lordgenius', '1');

insert into user_class (class_id, user_name) values (1, 'lordgenius');

insert into user_info (user_name, password, first_name, last_name, bio, ratings, is_sensei, email, phone, profile_image) values('YoungSon', 1234, 'Jon', 'Snow', 'Lorem ipsum dolor amet migas retro shabby chic, hot chicken vice hell of health goth before they sold out irony. Retro pop-up raclette cornhole YOLO lomo. Gluten-free waistcoat la croix, skateboard organic pabst dreamcatcher pok pok adaptogen direct trade wayfarers art party', 4, false, 'jonSnow@got.com', '419-706-0235', 'gh');