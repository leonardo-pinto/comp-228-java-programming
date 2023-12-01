insert into user_details (id, name, birth_date) values (10001, 'Leonardo', CURRENT_DATE());
insert into user_details (id, name, birth_date) values (10002, 'Joseph', CURRENT_DATE());
insert into user_details (id, name, birth_date) values (10003, 'Richards', CURRENT_DATE());

insert into post (id, description, user_id) values (20001, 'Study React', 10001);
insert into post (id, description, user_id) values (20002, 'Study Go', 10002);
insert into post (id, description, user_id) values (20003, 'Study Java', 10002);