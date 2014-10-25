
insert into systemuser (username, userpassword, email)
    values ('super',
        '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        'admin@my.com');

insert into systemusergroup (groupname, username) values ('admins', 'super');