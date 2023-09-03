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

INSERT INTO appkademy.permission VALUES(13, "SCHEDULED_APPOINTMENT_READ");
INSERT INTO appkademy.permission VALUES(14, "SCHEDULED_APPOINTMENT_CREATE");
INSERT INTO appkademy.permission VALUES(15, "SCHEDULED_APPOINTMENT_UPDATE");
INSERT INTO appkademy.permission VALUES(16, "SCHEDULED_APPOINTMENT_DELETE");

INSERT INTO appkademy.permission VALUES(17, "ROLE_READ");
INSERT INTO appkademy.permission VALUES(18, "ROLE_CREATE");
INSERT INTO appkademy.permission VALUES(19, "ROLE_UPDATE");
INSERT INTO appkademy.permission VALUES(20, "ROLE_DELETE");

INSERT INTO appkademy.permission VALUES(21, "TEACHING_SUBJECT_READ");
INSERT INTO appkademy.permission VALUES(22, "TEACHING_SUBJECT_CREATE");
INSERT INTO appkademy.permission VALUES(23, "TEACHING_SUBJECT_UPDATE");
INSERT INTO appkademy.permission VALUES(24, "TEACHING_SUBJECT_DELETE");

INSERT INTO appkademy.permission VALUES(25, "CHARACTERISTIC_READ");
INSERT INTO appkademy.permission VALUES(26, "CHARACTERISTIC_CREATE");
INSERT INTO appkademy.permission VALUES(27, "CHARACTERISTIC_UPDATE");
INSERT INTO appkademy.permission VALUES(28, "CHARACTERISTIC_DELETE");

INSERT INTO appkademy.permission VALUES(29, "FILE_UPLOAD");


--ROLES
INSERT INTO appkademy.`role` VALUES(1, "USER");
INSERT INTO appkademy.`role` VALUES(2, "ADMIN");
INSERT INTO appkademy.`role` VALUES(3, "SUPER_ADMIN");


--USER ROLE PERMISSIONS
INSERT INTO appkademy.role_permission VALUES(1, 2);
INSERT INTO appkademy.role_permission VALUES(1, 6);
INSERT INTO appkademy.role_permission VALUES(1, 10);
INSERT INTO appkademy.role_permission VALUES(1, 11);
INSERT INTO appkademy.role_permission VALUES(1, 14);
INSERT INTO appkademy.role_permission VALUES(1, 29);


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

INSERT INTO appkademy.role_permission VALUES(2, 21);
INSERT INTO appkademy.role_permission VALUES(2, 22);
INSERT INTO appkademy.role_permission VALUES(2, 23);
INSERT INTO appkademy.role_permission VALUES(2, 24);

INSERT INTO appkademy.role_permission VALUES(2, 25);
INSERT INTO appkademy.role_permission VALUES(2, 26);
INSERT INTO appkademy.role_permission VALUES(2, 27);
INSERT INTO appkademy.role_permission VALUES(2, 28);

INSERT INTO appkademy.role_permission VALUES(2, 29);



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

INSERT INTO appkademy.role_permission VALUES(3, 21);
INSERT INTO appkademy.role_permission VALUES(3, 22);
INSERT INTO appkademy.role_permission VALUES(3, 23);
INSERT INTO appkademy.role_permission VALUES(3, 24);

INSERT INTO appkademy.role_permission VALUES(3, 25);
INSERT INTO appkademy.role_permission VALUES(3, 26);
INSERT INTO appkademy.role_permission VALUES(3, 27);
INSERT INTO appkademy.role_permission VALUES(3, 28);

INSERT INTO appkademy.role_permission VALUES(3, 29);

