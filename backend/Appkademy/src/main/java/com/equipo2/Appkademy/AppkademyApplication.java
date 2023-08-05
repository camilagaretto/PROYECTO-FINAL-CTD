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


	//TODO el runner abajo es temporal hasta que esté listo el endpoint POST
	@Bean
	CommandLineRunner runner(TeacherRepository teacherRepository) {
		return args -> {
			teacherRepository.deleteAll();

			Address address = new Address();
			address.setCountry(Country.ARGENTINA);
			address.setProvince(Province.CIUDAD_DE_BUENOS_AIRES);
			address.setCity(City.CIUDAD_DE_BUENOS_AIRES);
			address.setStreetName("Av Cabildo");
			address.setStreetNumber("1234");
			address.setFloorApt("4a");

			Map<Currency, BigDecimal> hourlyRates = new HashMap<>();
			hourlyRates.put(Currency.ARS, BigDecimal.valueOf(140.30));
			hourlyRates.put(Currency.USD, BigDecimal.valueOf(10));

			Map<Modality, Boolean> modalities = new HashMap<>();
			modalities.put(Modality.FACE_TO_FACE, Boolean.TRUE);
			modalities.put(Modality.REMOTE, Boolean.TRUE);

			WeeklyWorkingSchedule weeklyWorkingSchedule = new WeeklyWorkingSchedule();
			weeklyWorkingSchedule.setMonday(true);
			weeklyWorkingSchedule.setWednesday(true);
			weeklyWorkingSchedule.setCheckIn(LocalTime.now());
			weeklyWorkingSchedule.setCheckOut(LocalTime.now());

			ScheduledAppointment scheduledAppointment = new ScheduledAppointment();
			scheduledAppointment.setCustomerId(1L);
			scheduledAppointment.setProviderId(1L);
			scheduledAppointment.setStartsOn(LocalDateTime.now());
			scheduledAppointment.setEndsOn(LocalDateTime.now());

			ScheduledAppointment scheduledAppointment2 = new ScheduledAppointment();
			scheduledAppointment2.setCustomerId(1L);
			scheduledAppointment2.setProviderId(1L);
			scheduledAppointment2.setStartsOn(LocalDateTime.now().plusMonths(1));
			scheduledAppointment2.setEndsOn(LocalDateTime.now().plusMonths(1));

			List<ScheduledAppointment> scheduledAppointmentList = new ArrayList<>();
			scheduledAppointmentList.add(scheduledAppointment);
			scheduledAppointmentList.add(scheduledAppointment2);

			List<TeachingProficiency> proficiencies = new ArrayList<>();
			proficiencies.add(new TeachingProficiency(TeachingMasteryLevel.COLLEGE, TeachingSubject.MATH));

			Teacher teacher1 = new Teacher();
			teacher1.setEnabled(true);
			teacher1.setCreatedOn(LocalDateTime.now());
			teacher1.setLastModifiedOn(LocalDateTime.now());
			teacher1.setProviderCategoryId(1L);
			teacher1.setTotalLikes(10L);
			teacher1.setProfilePictureUrl("asd.com");
			teacher1.setIdentityVerified(true);
			teacher1.setFirstName("Julian");
			teacher1.setLastName("Bordet");
			teacher1.setShortDescription("the description loca");
			teacher1.setFullDescription("la otra desc");
			teacher1.setAddress(address);
			teacher1.setHourlyRates(hourlyRates);
			teacher1.setModalities(modalities);
			teacher1.setWeeklyWorkingSchedule(weeklyWorkingSchedule);
			teacher1.setScheduledAppointments(scheduledAppointmentList);
			teacher1.setProficiencies(proficiencies);

			teacherRepository.save(teacher1);
		};
	}


	}
