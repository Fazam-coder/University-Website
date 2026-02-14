create table roles (
    id serial primary key,
    role_name varchar(31) unique not null
);

create table users (
    id serial primary key,
    name varchar(127) unique not null,
    login varchar(63) unique not null,
    password varchar(255) not null,
    image_path varchar(255) default '',
    about_info text default '',
    role_id int not null default 4,
    foreign key (role_id) references roles(id) on update cascade
);

create table students (
    user_id int unique not null,
    group_name varchar(15) not null,
    foreign key (user_id) references users(id) on update cascade on delete cascade
);

create table lessons (
    id serial primary key,
    group_name varchar(15) not null,
    lesson varchar(31) not null,
    teacher_id int not null,
    foreign key (teacher_id) references users(id) on update cascade
);

create table scores (
    id bigserial primary key,
    student_id int not null,
    lesson_id int not null,
    score int not null check(score >= 0 and score <= 50),
    foreign key (student_id) references users(id) on update cascade on delete cascade,
    foreign key (lesson_id) references lessons(id) on update cascade on delete cascade
);

insert into roles (role_name) values ('admin');
insert into roles (role_name) values ('teacher');
insert into roles (role_name) values ('student');
insert into roles (role_name) values ('user');

insert into users (name, login, password, role_id)
values ('admin', 'admin@kpfu.ru', 'B5E47A5293E24B113636BC70B111CDAC', 1);