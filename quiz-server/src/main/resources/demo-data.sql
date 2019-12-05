INSERT INTO Role (id, roleName, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO Role (id, roleName, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: password
INSERT INTO User (id, firstname, lastname, password, username) VALUES (1, 'Aleksey', 'Borsch', '$2a$10$S4Sx/RJ6gat0Kx4dSR7ZfecgSTV8lEdiiWvMoDzdg0euOx8suJ8Ia', 'borsch');
INSERT INTO User (id, firstname, lastname, password, username) VALUES (2, 'Admin', 'Admin', '$2a$10$Kva9rQBkzmCWPFAxRYZfi.c/4znxU7cE2lYyQcPsGi480tz7.YzcC', 'admin');
INSERT INTO User (id, firstname, lastname, password, username) VALUES (3, 'Ivan', 'Ivanov', '$2a$10$4PDv6mwsPqL5UmGPY1S5MeIQzibiTaJr4N1kl7V48.6RHt8NBFO2a', 'ivanov');

INSERT INTO User_Role(userId, roleId) VALUES(1,1);
INSERT INTO User_Role(userId, roleId) VALUES(2,1);
INSERT INTO User_Role(userId, roleId) VALUES(2,2);
INSERT INTO User_Role(userId, roleId) VALUES(3,1);

commit;
