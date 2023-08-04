-- liquibase formatted sql

-- changeset liquibase:1 runAlways:true
use springbootproject;

drop table authorities;

drop table members;

create table members (
        id int not null AUTO_INCREMENT,
        username varchar(50) not null,
        password char(68) not null,
        email varchar(68) not null,
        enabled tinyint not null,
        primary key (id)
);

insert into members
values (1,'pedro','{bcrypt}$2y$10$zkAxlVrud.lznJQVz2jQz.QhTfKedVfW.blHFP60a.Tp4Lb6p4rZy','pedro@gmail.com',1);

create table authorities (
  id int not null,
  authority varchar(50) not null,
  unique key userAuth_uni (id, authority),
  foreign key (id) references members(id)
);

insert into authorities 
values (1,'ROLE_ADMIN');
