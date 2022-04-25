create table messages (id int4 not null, message varchar(255), username varchar(255), primary key (id));
create table users (id int4 not null, name varchar(255), password varchar(255), primary key (id));
create sequence message_id_seq start 1 increment 1;
create sequence user_id_seq start 1 increment 1;
