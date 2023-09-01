-- liquibase formatted sql

--changeset jbordet:05

-- TEACHER 1: JUAN MARTIN

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-49, 1, 'juan.martin@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-49, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(51, '09:00:00', '18:00:00', 1, 1, 0, 0, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(1, 'CIUDAD_DE_BUENOS_AIRES', 'ARGENTINA', '4A', 'CIUDAD_DE_BUENOS_AIRES', 'Av. Cabildo', '4450',
 '2023-08-21 20:07:22.124154', 1, 'Juan Martin', 0, '2023-08-21 20:07:22.124154',
  'Rodriguez', -49, 'string', 1, 0, 0, '¡Bienvenidos a mi perfil de profesor de matemáticas en línea! Soy un apasionado de las matemáticas con una amplia experiencia en la enseñanza. Mi enfoque principal es hacer que las matemáticas sean accesibles y comprensibles para todos mis estudiantes, sin importar su nivel de habilidad.', 'Aprendamos juntos!', 51);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(1, 2500.00, 'ARS');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(1, 1, 0);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(1, 1);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(1, 2);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(1, 3);




-- TEACHER 2: PEDRO SANCHEZ

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-50, 1, 'pedro.sanchez@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-50, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(52, '12:00:00', '18:00:00', 1, 1, 1, 0, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(2, 'CIUDAD_DE_BUENOS_AIRES', 'ARGENTINA', '2B', 'CIUDAD_DE_BUENOS_AIRES', 'Libertad', '2023',
 '2023-08-21 20:07:22.124154', 1, 'Pedro', 0, '2023-08-21 20:07:22.124154',
  'Sanchez', -50, 'string', 1, 0, 0, '¡Saludos! Soy un profesor de matemáticas en línea con una pasión innegable por hacer que las matemáticas sean emocionantes y accesibles para todos. Mi misión es desmitificar las matemáticas y transformarlas en una experiencia de aprendizaje inspiradora y atractiva.', 'Aprendamos matematica y literatura', 52);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(2, 5000.00, 'ARS');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(2, 1, 0);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(2, 1);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(2, 2);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(2, 3);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(2, 4);





-- TEACHER 3: SERGIO CELULOSO

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-51, 1, 'sergio.celuloso@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-51, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(53, '13:00:00', '18:00:00', 1, 1, 1, 1, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(3, 'ROSARIO', 'ARGENTINA', '2B', 'SANTA_FE', 'Av. de los libres', '4513',
 '2023-08-21 20:07:22.124154', 1, 'Sergio', 0, '2023-08-21 20:07:22.124154',
  'Celuloso', -51, 'string', 1, 0, 0, 'Saludos, soy un dedicado profesor de biología con un ferviente amor por la ciencia y el aprendizaje. Mi objetivo es hacer que la biología cobre vida y sea comprensible para ti. Mis clases están diseñadas para explorar el fascinante mundo de la vida, desde la célula más pequeña hasta los ecosistemas más grandes, de una manera clara y apasionante.', 'Aprendamos juntos!', 53);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(3, 7000.00, 'ARS');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(3, 1, 0);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(3, 1, 1);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(3, 7);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(3, 8);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(3, 9);




-- TEACHER 4: BRANDON SANDERSON

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-52, 1, 'brandon.sanderson@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-52, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(54, '13:00:00', '18:00:00', 1, 1, 1, 1, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(4, 'LA_PLATA', 'ARGENTINA', '2B', 'BUENOS_AIRES', 'Luthadel', '2541',
 '2023-08-21 20:07:22.124154', 1, 'Brandon', 0, '2023-08-21 20:07:22.124154',
  'Sanderson', -52, 'string', 1, 0, 0, '¡Saludos! Soy un profesor de literatura con una pasión inquebrantable por las palabras y las historias que dan forma a nuestra cultura. Mi objetivo es llevarte a un viaje a través de la literatura, explorando clásicos y obras contemporáneas con un enfoque crítico y apreciativo. En mis clases, analizaremos la estructura, el simbolismo y el contexto cultural de las obras literarias para profundizar en su significado y relevancia.', 'Aprender literatura nunca fue tan fácil', 54);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(4, 7000.00, 'ARS');
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(4, 80, 'USD');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(4, 1, 0);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(4, 1, 1);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(4, 4);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(4, 5);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(4, 6);






-- TEACHER 5: FUNES MEMORIOSO

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-53, 1, 'funes.memorioso@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-53, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(55, '11:00:00', '18:00:00', 1, 0, 1, 1, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(5, 'CIUDAD_DE_BUENOS_AIRES', 'ARGENTINA', '1B', 'CIUDAD_DE_BUENOS_AIRES', 'Riobamba', '1521',
 '2023-08-21 20:07:22.124154', 1, 'Funes', 0, '2023-08-21 20:07:22.124154',
  'Memorioso', -53, 'string', 1, 0, 0, 'Saludos, soy un apasionado profesor de historia con un entusiasmo contagioso por el pasado y su impacto en nuestro presente. Mi objetivo es llevar la historia a la vida de una manera fascinante y significativa. A través de mis clases, te invito a explorar épocas pasadas, eventos cruciales y figuras históricas que han moldeado nuestro mundo actual. ', 'Los que no aprenden historia estan condenados a repetirla ;)', 55);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(5, 7000.00, 'ARS');
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(5, 80, 'USD');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(5, 1, 0);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(5, 1, 1);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(5, 10);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(5, 11);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(5, 12);





-- TEACHER 6: Maria York

-- user
--password -> 123456789
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user`
(user_id, user_type_id, email, password, `type`)
VALUES(-54, 1, 'maria.york@gmail.com', '$2a$10$zMRjnPrkPti1UK85ARnUZ.qxzqVbfz6E0rMrES9ERvj99tgcCXw0K', 'TEACHER');

-- user role

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.`_user_role`
(user_id, role_id)
VALUES(-54, 1);

-- weekly working schedule
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.weekly_working_schedule
(id, check_in, check_out, friday, monday, saturday, sunday, thursday, tuesday, wednesday)
VALUES(56, '11:00:00', '18:00:00', 1, 0, 1, 1, 1, 0, 1);


-- teacher

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher
(id, city, country, floor_apt, province, street_name, street_number, created_on, enabled,
    first_name, identity_verified, last_modified_on, last_name, user_id, profile_picture_url,
    provider_category_id, signup_approved_by_admin, total_likes, full_description, short_description,
    weekly_working_schedule_id)
VALUES(6, 'CIUDAD_DE_BUENOS_AIRES', 'ARGENTINA', '1B', 'CIUDAD_DE_BUENOS_AIRES', 'Riobamba', '1521',
 '2023-08-21 20:07:22.124154', 1, 'Maria', 0, '2023-08-21 20:07:22.124154',
  'York', -54, 'string', 1, 0, 0, '¡Hola! Soy un apasionado profesor de inglés con un enfoque dinámico y amigable para ayudarte a alcanzar tus metas en el aprendizaje de este idioma. Con años de experiencia en la enseñanza, he perfeccionado métodos efectivos que hacen que el inglés sea accesible y atractivo. Mis clases son interactivas y personalizadas, diseñadas para mejorar tus habilidades de conversación, comprensión auditiva, lectura y escritura.', 'Sin inglés no hay hello world', 56);

--hourly rate
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(6, 7000.00, 'ARS');
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_hourly_rate
(teacher_id, hourly_rates, currency)
VALUES(6, 80, 'USD');

-- teacher modality
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(6, 1, 0);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_modality
(teacher_id, value, modality)
VALUES(6, 1, 1);

-- teacher proficiencies
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(6, 13);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(6, 14);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teacher_proficiencies
(teacher_id, proficiencies_id)
VALUES(6, 15);
