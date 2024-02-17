insert into roles(name)
values ('admin'),
       ('user');

insert into users(login, password, role)
values ('admin', 'admin', 1),
       ('user1', 'user1', 2);