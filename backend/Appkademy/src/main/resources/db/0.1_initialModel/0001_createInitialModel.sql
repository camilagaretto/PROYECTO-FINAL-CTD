-- liquibase formatted sql

--changeset jbordet:01

DROP DATABASE IF EXISTS appkademy;
CREATE DATABASE appkademy;

USE appkademy;

CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.DATABASECHANGELOGLOCK definition

CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO appkademy.DATABASECHANGELOGLOCK VALUES(1,0,null,null);


-- appkademy.`_user` definition

CREATE TABLE `_user` (
  `user_id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.native definition

CREATE TABLE `native` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.permission definition

CREATE TABLE `permission` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.`role` definition

CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.student definition

CREATE TABLE `student` (
  `id` bigint NOT NULL,
  `city` enum('CIUDAD_DE_BUENOS_AIRES','LA_PLATA','ROSARIO') DEFAULT NULL,
  `country` enum('ARGENTINA') DEFAULT NULL,
  `floor_apt` varchar(255) DEFAULT NULL,
  `province` enum('BUENOS_AIRES','CIUDAD_DE_BUENOS_AIRES','SANTA_FE') DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `first_name` varchar(50) NOT NULL,
  `identity_verified` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified_on` datetime(6) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfe0i52si7ybu0wjedj6motiim` (`email`),
  UNIQUE KEY `UK_bkix9btnoi1n917ll7bplkvg5` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.weekly_working_schedule definition

CREATE TABLE `weekly_working_schedule` (
  `id` bigint NOT NULL,
  `check_in` time(6) NOT NULL,
  `check_out` time(6) NOT NULL,
  `friday` tinyint(1) DEFAULT '0',
  `monday` tinyint(1) DEFAULT '0',
  `saturday` tinyint(1) DEFAULT '0',
  `sunday` tinyint(1) DEFAULT '0',
  `thursday` tinyint(1) DEFAULT '0',
  `tuesday` tinyint(1) DEFAULT '0',
  `wednesday` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.`_user_role` definition

CREATE TABLE `_user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT `FK4u3g3ti6kh3fxp5k6x6k897je` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKsa99se25erlgqmbdawypj2fiw` FOREIGN KEY (`user_id`) REFERENCES `_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.liked_provider_id definition

CREATE TABLE `liked_provider_id` (
  `natural_person_customer_id` bigint NOT NULL,
  `provider_id` bigint DEFAULT NULL,
  KEY `FK9in7urmfjrlfr6kwlfo973tv1` (`natural_person_customer_id`),
  CONSTRAINT `FK9in7urmfjrlfr6kwlfo973tv1` FOREIGN KEY (`natural_person_customer_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.role_permission definition

CREATE TABLE `role_permission` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`),
  KEY `FKa6jx8n8xkesmjmv6jqug6bg68` (`role_id`),
  CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.characteristic definition

CREATE TABLE `characteristic` (
  `id` bigint NOT NULL,
  `icon` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.teacher definition

CREATE TABLE `teacher` (
  `id` bigint NOT NULL,
  `city` enum('CIUDAD_DE_BUENOS_AIRES','LA_PLATA','ROSARIO') DEFAULT NULL,
  `country` enum('ARGENTINA') DEFAULT NULL,
  `floor_apt` varchar(255) DEFAULT NULL,
  `province` enum('BUENOS_AIRES','CIUDAD_DE_BUENOS_AIRES','SANTA_FE') DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `first_name` varchar(50) NOT NULL,
  `identity_verified` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified_on` datetime(6) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `user_id` bigint NOT NULL,
  `profile_picture_url` varchar(350) DEFAULT NULL,
  `provider_category_id` bigint NOT NULL,
  `signup_approved_by_admin` bit(1) DEFAULT NULL,
  `total_likes` bigint NOT NULL DEFAULT '0',
  `full_description` text,
  `short_description` varchar(100) DEFAULT NULL,
  `weekly_working_schedule_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3kv6k1e64a9gylvkn3gnghc2q` (`email`),
  UNIQUE KEY `UK_i5wqs2ds2vpmfpbcdxi9m2jvr` (`user_id`),
  UNIQUE KEY `UK_af55cd0o3jbe9tvqo031m396y` (`weekly_working_schedule_id`),
  CONSTRAINT `FKap7qu4nl161li1myey6tkoew` FOREIGN KEY (`weekly_working_schedule_id`) REFERENCES `weekly_working_schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.teacher_characteristics definition

CREATE TABLE `teacher_characteristics` (
  `teacher_id` bigint NOT NULL,
  `characteristics_id` bigint NOT NULL,
  KEY `FKrgecpt2gwmth7vgg0wrl22ysj` (`characteristics_id`),
  KEY `FKl3nqi7fn6dn08tnxgwr87rivl` (`teacher_id`),
  CONSTRAINT `FKl3nqi7fn6dn08tnxgwr87rivl` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKrgecpt2gwmth7vgg0wrl22ysj` FOREIGN KEY (`characteristics_id`) REFERENCES `characteristic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.teacher_hourly_rate definition

CREATE TABLE `teacher_hourly_rate` (
  `teacher_id` bigint NOT NULL,
  `hourly_rates` decimal(38,2) DEFAULT NULL,
  `currency` varchar(255) NOT NULL,
  PRIMARY KEY (`teacher_id`,`currency`),
  CONSTRAINT `FKgbp8w7m5cyr7ypu6bhf6e7qst` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.teacher_modality definition

CREATE TABLE `teacher_modality` (
  `teacher_id` bigint NOT NULL,
  `value` bit(1) DEFAULT NULL,
  `modality` tinyint NOT NULL,
  PRIMARY KEY (`teacher_id`,`modality`),
  CONSTRAINT `FK5omendcsfjrspp2peyc4xrjj` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `teacher_modality_chk_1` CHECK ((`modality` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.teacher_signup_request definition

CREATE TABLE `teacher_signup_request` (
  `id` bigint NOT NULL,
  `request_created_on` datetime(6) DEFAULT NULL,
  `request_has_been_reviewed` bit(1) DEFAULT NULL,
  `review_descision` enum('APPROVED','REJECTED','UNDER_REVIEW') DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ljmvjhd90hfadhfvijoxrocia` (`teacher_id`),
  CONSTRAINT `FKsxcue4hyk3hbwc0urvjqjd30q` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.teacher_termination_request definition

CREATE TABLE `teacher_termination_request` (
  `id` bigint NOT NULL,
  `request_created_on` datetime(6) DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6lti83dlq5n7a7ilsapaxo6cp` (`teacher_id`),
  CONSTRAINT `FKlkpyr0rj1w30wgv4m8o6jl3d3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.teaching_subject definition

CREATE TABLE `teaching_subject` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- appkademy.teaching_proficiency definition

CREATE TABLE `teaching_proficiency` (
  `id` bigint NOT NULL,
  `mastery_level` enum('COLLEGE','HIGHSCHOOL','MIDDLE_SCHOOL') NOT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqbilr4wlajtjwv6jkdqg6vqgm` (`subject_id`),
  CONSTRAINT `FKqbilr4wlajtjwv6jkdqg6vqgm` FOREIGN KEY (`subject_id`) REFERENCES `teaching_subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.teacher_proficiencies definition

CREATE TABLE `teacher_proficiencies` (
  `teacher_id` bigint NOT NULL,
  `proficiencies_id` bigint NOT NULL,
  KEY `FKfqwyxj92se2pbejq28i8d3aml` (`proficiencies_id`),
  KEY `FK3q28c6fjghkn1t61ln8m4n0kq` (`teacher_id`),
  CONSTRAINT `FK3q28c6fjghkn1t61ln8m4n0kq` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKfqwyxj92se2pbejq28i8d3aml` FOREIGN KEY (`proficiencies_id`) REFERENCES `teaching_proficiency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- appkademy.scheduled_appointment definition

CREATE TABLE `scheduled_appointment` (
  `id` bigint NOT NULL,
  `ends_on` datetime(6) NOT NULL,
  `starts_on` datetime(6) NOT NULL,
  `student_id` bigint DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2x1kys6sipuhe690oy4kxtevd` (`teacher_id`),
  KEY `FKexeshthp3jwdh6fjgqh4f5vf2` (`student_id`),
  CONSTRAINT `FK2x1kys6sipuhe690oy4kxtevd` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKexeshthp3jwdh6fjgqh4f5vf2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO appkademy.native VALUES(0);
