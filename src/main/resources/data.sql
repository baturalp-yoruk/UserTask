insert into user (password,role_name,user_name) values('123456', 'developer', 'sedaylmz@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'team_lead', 'umit@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'businessAnalyst', 'ipek@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'humanResources', 'meltem@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'softwareArchitect', 'burcu@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'developer', 'baturalp@gmail.com');
insert into user (password,role_name,user_name) values('123456', 'humanResources', 'batu@gmail.com');

insert into process(deleted, end_date, process_name, start_date, status, user_entity_id) values(false, '2020-07-01', 'canliya_alma_sureci' ,'2020-02-01', 'IN_PROGRESS', 5);
insert into process(deleted, end_date, process_name, start_date, status, user_entity_id) values(false, '2020-08-01', 'ise_alim' ,'2020-03-01', 'TO_DO', 3);
insert into process(deleted, end_date, process_name, start_date, status, user_entity_id) values(false, '2020-09-01', 'canliya_alma_sureci' ,'2020-04-01', 'DONE', 2);
insert into process(deleted, end_date, process_name, start_date, status, user_entity_id) values(false, '2020-10-01', 'canliya_alma_sureci' ,'2020-05-01', 'REJECT', 4);

insert into task (deleted, description, end_date,start_date, status, task_name, process_entity_id, user_entity_id) values(true, 'stajyerlere task verme' ,'2020-04-07', '2020-04-01', 'DONE', 'odev_gonderme' ,1,1);
insert into task (deleted, description, end_date,start_date, status, task_name, process_entity_id, user_entity_id) values(false, 'stajyerlere task verme' ,'2020-05-07', '2020-05-01', 'TO_DO', 'odev_gonderme' ,2,2);
insert into task (deleted, description, end_date,start_date, status, task_name, process_entity_id, user_entity_id) values(false, 'stajyerlere task verme' ,'2020-06-07', '2020-06-01', 'IN_PROGRESS', 'odev_gonderme' ,3,3);
insert into task (deleted, description, end_date,start_date, status, task_name, process_entity_id, user_entity_id) values(false, 'stajyerlere task verme' ,'2020-07-07', '2020-07-01', 'REJECT', 'odev_gonderme' ,4,4);
insert into task (deleted, description, end_date,start_date, status, task_name, process_entity_id, user_entity_id) values(false, 'stajyerlere task verme' ,'2020-08-07', '2020-08-01', 'DONE', 'odev_gonderme' ,1,5);

insert into metric(actual_end_date, metric_type, original_end_date, start_date, task_entity_id, user_entity_id) values( '2020-05-07', 'BUGFIX',  '2020-05-10', '2020-03-01', 2, 1);
insert into metric(actual_end_date, metric_type, original_end_date, start_date, task_entity_id, user_entity_id) values( '2020-06-07', 'BUGFIX',  '2020-05-10', '2020-03-01', 3, 6);

insert into team(team_name, description) values ('backend', 'gg backend team');
insert into team(team_name, description) values ('frontend', 'gg frontend team');

insert into board(board_name, description) values ('plan', 'team plan board');
insert into board(board_name, description) values ('empty', 'for other things');

insert into column_entity(column_title) values ('TODO');
insert into column_entity(column_title) values ('DONE');