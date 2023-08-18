-- liquibase formatted sql

--changeset jbordet:02


--PERMISSIONS
INSERT INTO appkademy.permission VALUES(1, "USER_READ");
INSERT INTO appkademy.permission VALUES(2, "USER_CREATE");
INSERT INTO appkademy.permission VALUES(3, "USER_UPDATE");
INSERT INTO appkademy.permission VALUES(4, "USER_DELETE");

INSERT INTO appkademy.permission VALUES(5, "TEACHER_READ");
INSERT INTO appkademy.permission VALUES(6, "TEACHER_CREATE");
INSERT INTO appkademy.permission VALUES(7, "TEACHER_UPDATE");
INSERT INTO appkademy.permission VALUES(8, "TEACHER_DELETE");

INSERT INTO appkademy.permission VALUES(9, "STUDENT_READ");
INSERT INTO appkademy.permission VALUES(10, "STUDENT_CREATE");
INSERT INTO appkademy.permission VALUES(11, "STUDENT_UPDATE");
INSERT INTO appkademy.permission VALUES(12, "STUDENT_DELETE");

INSERT INTO appkademy.permission VALUES(13, "SCHEDULED_APPOINTENT_READ");
INSERT INTO appkademy.permission VALUES(14, "SCHEDULED_APPOINTENT_CREATE");
INSERT INTO appkademy.permission VALUES(15, "SCHEDULED_APPOINTENT_UPDATE");
INSERT INTO appkademy.permission VALUES(16, "SCHEDULED_APPOINTENT_DELETE");

INSERT INTO appkademy.permission VALUES(17, "ROLE_READ");
INSERT INTO appkademy.permission VALUES(18, "ROLE_CREATE");
INSERT INTO appkademy.permission VALUES(19, "ROLE_UPDATE");
INSERT INTO appkademy.permission VALUES(20, "ROLE_DELETE");


--ROLES
INSERT INTO appkademy.`role` VALUES(1, "USER");
INSERT INTO appkademy.`role` VALUES(2, "ADMIN");
INSERT INTO appkademy.`role` VALUES(3, "SUPER_ADMIN");


--USER ROLE PERMISSIONS
INSERT INTO appkademy.role_permission VALUES(1, 2);
INSERT INTO appkademy.role_permission VALUES(1, 6);
INSERT INTO appkademy.role_permission VALUES(1, 10);
INSERT INTO appkademy.role_permission VALUES(1, 14);


--ADMIN ROLE PERMISSIONS
INSERT INTO appkademy.role_permission VALUES(2, 1);
INSERT INTO appkademy.role_permission VALUES(2, 2);
INSERT INTO appkademy.role_permission VALUES(2, 3);
INSERT INTO appkademy.role_permission VALUES(2, 4);

INSERT INTO appkademy.role_permission VALUES(2, 5);
INSERT INTO appkademy.role_permission VALUES(2, 6);
INSERT INTO appkademy.role_permission VALUES(2, 7);
INSERT INTO appkademy.role_permission VALUES(2, 8);

INSERT INTO appkademy.role_permission VALUES(2, 9);
INSERT INTO appkademy.role_permission VALUES(2, 10);
INSERT INTO appkademy.role_permission VALUES(2, 11);
INSERT INTO appkademy.role_permission VALUES(2, 12);

INSERT INTO appkademy.role_permission VALUES(2, 13);
INSERT INTO appkademy.role_permission VALUES(2, 14);
INSERT INTO appkademy.role_permission VALUES(2, 15);
INSERT INTO appkademy.role_permission VALUES(2, 16);


--SUPER ADMIN ROLE PERMISSIONS
INSERT INTO appkademy.role_permission VALUES(3, 1);
INSERT INTO appkademy.role_permission VALUES(3, 2);
INSERT INTO appkademy.role_permission VALUES(3, 3);
INSERT INTO appkademy.role_permission VALUES(3, 4);

INSERT INTO appkademy.role_permission VALUES(3, 5);
INSERT INTO appkademy.role_permission VALUES(3, 6);
INSERT INTO appkademy.role_permission VALUES(3, 7);
INSERT INTO appkademy.role_permission VALUES(3, 8);

INSERT INTO appkademy.role_permission VALUES(3, 9);
INSERT INTO appkademy.role_permission VALUES(3, 10);
INSERT INTO appkademy.role_permission VALUES(3, 11);
INSERT INTO appkademy.role_permission VALUES(3, 12);

INSERT INTO appkademy.role_permission VALUES(3, 13);
INSERT INTO appkademy.role_permission VALUES(3, 14);
INSERT INTO appkademy.role_permission VALUES(3, 15);
INSERT INTO appkademy.role_permission VALUES(3, 16);

INSERT INTO appkademy.role_permission VALUES(3, 17);
INSERT INTO appkademy.role_permission VALUES(3, 18);
INSERT INTO appkademy.role_permission VALUES(3, 19);
INSERT INTO appkademy.role_permission VALUES(3, 20);
