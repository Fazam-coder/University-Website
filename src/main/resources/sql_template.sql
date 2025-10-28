create table roles (
    id serial primary key,
    role_name varchar(31) unique not null
)

create table users (
    id serial primary key,
    name varchar(127) unique not null,
    login varchar(63) unique not null,
    password varchar(255) not null,
    image_path varchar(255) default '',
    about_info text default '',
    role_id int not null default 4,
    foreign key (role_id) references roles(id) on update cascade
)

create table application_types (
    id serial primary key,
    type varchar(63) unique not null,
    pattern text default ''
)

insert into roles (role_name) values ('admin')
insert into roles (role_name) values ('teacher')
insert into roles (role_name) values ('student')
insert into roles (role_name) values ('user')

insert into users (name, login, password, role_id)
values ('admin', 'admin@kpfu.ru', 'B5E47A5293E24B113636BC70B111CDAC', 1)