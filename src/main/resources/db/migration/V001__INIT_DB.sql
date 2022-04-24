create table messages (id int4 not null, message varchar(255), user_id int4, primary key (id));
create table users (id int4 not null, name varchar(255), password varchar(255), primary key (id));
create sequence hibernate_sequence start 1 increment 1;
alter table if exists messages add constraint message_user_fk foreign key (user_id) references users;