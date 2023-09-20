-- liquibase formatted sql

--changeset jbordet:06

-- user
--password -> 123456789
INSERT INTO appkademy.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(100, 1500, 'javier.estudiante@gmail.com', '$2a$10$6UW9YqUE1GyS0QoriZusuexq7psJKwk9dWRDfzZ2O4ieFcue9G1Ka', 'STUDENT');

INSERT INTO appkademy.`_user_role`
(user_id, role_id)
VALUES(100, 1);

INSERT INTO appkademy.`student`
(id, city, country, province, street_name, street_number, created_on, enabled, first_name, identity_verified, last_modified_on, last_name, user_id)
VALUES(1500, 'CIUDAD_DE_BUENOS_AIRES', 'ARGENTINA',  'CIUDAD_DE_BUENOS_AIRES', 'MARCELO T DE ALVEAR', '430', NOW(), true, 'Javier', true , NOW(), 'LeEstudiante', 100);



INSERT INTO appkademy.`scheduled_appointment`
(id, ends_on, starts_on, student_id, teacher_id)
VALUES(1, '2023-09-22 17:30:00.000000', '2023-09-22 17:00:00.000000', 1500, 1);

INSERT INTO appkademy.`scheduled_appointment`
(id, ends_on, starts_on, student_id, teacher_id)
VALUES(2, '2023-09-22 16:00:00.000000', '2023-09-22 16:30:00.000000', 1500, 2);

INSERT INTO appkademy.`scheduled_appointment`
(id, ends_on, starts_on, student_id, teacher_id)
VALUES(3, '2023-09-21 16:30:00.000000', '2023-09-21 17:00:00.000000', 1500, 1);