-- Create members table and add one default user

use springbootproject;

create table members (
        username varchar(50) not null,
        password char(68) not null,
        email varchar(68) not null,
        enabled tinyint not null,
        primary key(username)
);

-- pedro - default
insert into members
values ('pedro','{bcrypt}$2y$10$zkAxlVrud.lznJQVz2jQz.QhTfKedVfW.blHFP60a.Tp4Lb6p4rZy','pedro@gmail.com',1);

-- Create authorities tables
create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  unique key userAuth_uni (username, authority),
  foreign key (username) references members(username)
);

insert into authorities 
values ('pedro','ROLE_ADMIN');
