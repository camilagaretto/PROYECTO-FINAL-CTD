package com.equipo2.Appkademy;

import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.core.model.enums.*;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AppkademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppkademyApplication.class, args);
	}

}
