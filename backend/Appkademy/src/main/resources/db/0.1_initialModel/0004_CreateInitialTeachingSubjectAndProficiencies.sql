-- liquibase formatted sql

--changeset jbordet:04

-- TEACHING SUBJECTS
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_subject VALUES(1, "MATH");
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_subject VALUES(2, "LITERATURE");
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_subject VALUES(3, "BIOLOGY");
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_subject VALUES(4, "HISTORY");
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_subject VALUES(5, "ENGLISH");

-- TEACHING PROFICIENCIES
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(1, "COLLEGE", 1);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(2, "HIGHSCHOOL", 1);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(3, "MIDDLE_SCHOOL", 1);

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(4, "COLLEGE", 2);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(5, "HIGHSCHOOL", 2);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(6, "MIDDLE_SCHOOL", 2);

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(7, "COLLEGE", 3);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(8, "HIGHSCHOOL", 3);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(9, "MIDDLE_SCHOOL", 3);

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(10, "COLLEGE", 4);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(11, "HIGHSCHOOL", 4);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(12, "MIDDLE_SCHOOL", 4);

INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(13, "COLLEGE", 5);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(14, "HIGHSCHOOL", 5);
INSERT INTO 0723TDPRON1C01LAED0222PT_GRUPO2.teaching_proficiency(id, mastery_level, subject_id) VALUES(15, "MIDDLE_SCHOOL", 5);


