-- liquibase formatted sql

--changeset jbordet:03

INSERT INTO appkademy.`_user`(user_id, email, password, type, user_type_id) VALUES(50000, "adminUser@gmail.com", "$2a$10$JjZDaTnDLCnN9NpoSmdJMelyfx7a12hnt0O6Vb9ieQ.znYMpbXNM2", "ADMIN", 50000);
INSERT INTO appkademy.`_user`(user_id, email, password, type, user_type_id) VALUES(60000, "superAdminUser@gmail.com", "$2a$10$JjZDaTnDLCnN9NpoSmdJMelyfx7a12hnt0O6Vb9ieQ.znYMpbXNM2", "ADMIN", 60000);

INSERT INTO appkademy.`_user_role` VALUES(50000, 2);
INSERT INTO appkademy.`_user_role` VALUES(60000, 3);