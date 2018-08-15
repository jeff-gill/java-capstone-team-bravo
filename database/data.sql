-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

insert into user_info (user_name, password, first_name, last_name, bio, ratings, is_sensei, profile_image) values ('lordgenius', '1234', 'Gordy', 'Smith', 'Lorem ipsum dolor amet mlkshk venmo semiotics kitsch, fixie cliche ramps biodiesel iceland farm-to-table pitchfork la croix pinterest XOXO. Mustache VHS taiyaki green juice, small batch pok pok blue bottle pug aesthetic tilde trust fund locavore vape.', 5, true, 'dkfhjg4');

insert into subjects (subject_name) values ('SWORD FIGHTING');

insert into class (subject_name, location, event_date, event_start_time, event_end_time, cost, available_slots, description) values ('SWORD FIGHTING', 'Goodale Park', '10/25/2018', '10:00', '11:00', 25.0, 4, 'Learn to Slash Zombies');

insert into user_subjects (user_name, subject_name) values ('lordgenius', 'SWORD FIGHTING');

insert into user_class (class_id, user_name) values (1, 'lordgenius');