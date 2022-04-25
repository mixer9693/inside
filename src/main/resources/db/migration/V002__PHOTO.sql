create table photos (id int4 not null, creation_date_time timestamp, name varchar(255), path varchar(255), user_id int4, primary key (id));
create sequence photo_id_seq start 1 increment 1;
alter table if exists photos add constraint user_photo_fk foreign key (user_id) references users;