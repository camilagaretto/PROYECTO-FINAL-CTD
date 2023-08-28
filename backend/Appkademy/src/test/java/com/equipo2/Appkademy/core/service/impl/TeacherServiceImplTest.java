package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapperImpl;
import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.*;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.core.validation.service.TeacherValidationServiceImpl;
import com.equipo2.Appkademy.rest.dto.request.AddressRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.WeeklyWorkingScheduleCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeacherServiceImplTest {

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private TeacherRepository teacherRepository;

    @Spy
    private AppkademyMapperImpl mapper;

    @Mock
    private TeacherValidationServiceImpl teacherValidationService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSaveTeacher(){
        Long userId = 1L;
        String firstName = "Bilbo";
        String lastName = "Bolson";
        String email = "mountainthief@gmail.com";
        String streetName = "Av. Cabildo";
        String streetNumber = "222";

        String shortDescription = "Los atajos cortos traen retrasos largos, pero las posadas los alargan todavía más";
        String fullDescription = "No todo lo que es oro, reluce, ni toda la gente errante anda perdida; a las raíces" +
                " profundas no llega la escarcha, el viejo vigoroso no se marchita. De las cenizas subirá un fuego," +
                " y una luz asomará en las sombras; el descoronado será de nuevo rey, forjarán otra vez la espada rota";

        AddressRequestDto addressCreateRequestDto = new AddressRequestDto();
        addressCreateRequestDto.setCountry(Country.ARGENTINA.name());
        addressCreateRequestDto.setProvince(Province.BUENOS_AIRES.name());
        addressCreateRequestDto.setStreetName(streetName);
        addressCreateRequestDto.setStreetNumber(streetNumber);

        Map<Currency, BigDecimal> hourlyRates = new HashMap<>();
        hourlyRates.put(Currency.ARS, BigDecimal.valueOf(500));

        Map<Modality, Boolean> modalities = new HashMap<>();
        modalities.put(Modality.FACE_TO_FACE, true);

        List<TeachingProficiency> proficiencies = new ArrayList<>();
        TeachingProficiency proficiency = new TeachingProficiency();
        proficiency.setMasteryLevel(TeachingMasteryLevel.COLLEGE);
        proficiency.setSubject(new TeachingSubject("LITERATURE"));
        proficiencies.add(proficiency);

        WeeklyWorkingScheduleCreateRequestDto workingScheduleCreateRequestDto = new WeeklyWorkingScheduleCreateRequestDto();
        workingScheduleCreateRequestDto.setCheckIn(LocalTime.of(9, 0, 0));
        workingScheduleCreateRequestDto.setCheckOut(LocalTime.of(18, 0, 0));
        workingScheduleCreateRequestDto.setMonday(true);
        workingScheduleCreateRequestDto.setTuesday(true);

        String profilePictureUrl = "www.ereborpic.com/me_in_the_vault.jpg";

        TeacherCreateRequestDto createRequestDto = new TeacherCreateRequestDto();
        createRequestDto.setUserId(userId);
        createRequestDto.setFirstName(firstName);
        createRequestDto.setLastName(lastName);
        createRequestDto.setHourlyRates(hourlyRates);
        createRequestDto.setModalities(modalities);
        createRequestDto.setProficiencyIds(Arrays.asList(1L));
        createRequestDto.setWeeklyWorkingSchedule(workingScheduleCreateRequestDto);
        createRequestDto.setProfilePictureUrl(profilePictureUrl);
        createRequestDto.setAddress(addressCreateRequestDto);
        createRequestDto.setShortDescription(shortDescription);
        createRequestDto.setFullDescription(fullDescription);

        doNothing().when(teacherValidationService).assertUserDoesNotAlreadyExist(eq(userId));
        doNothing().when(teacherValidationService).assertHourlyRatesAreValid(anyMap());

        Teacher entity = Teacher.builder()
                .userId(createRequestDto.getUserId())
                .firstName(createRequestDto.getFirstName())
                .lastName(createRequestDto.getLastName())
                .hourlyRates(createRequestDto.getHourlyRates())
                .modalities(createRequestDto.getModalities())
                .proficiencies(proficiencies)
                .weeklyWorkingSchedule(mapper.weeklyWorkingScheduleCreateRequestDtoToWeeklyWorkginSchedule(createRequestDto.getWeeklyWorkingSchedule()))
                .providerCategoryId(1L)
                .profilePictureUrl(createRequestDto.getProfilePictureUrl())
                .shortDescription(createRequestDto.getShortDescription())
                .fullDescription(createRequestDto.getFullDescription())
                .address(mapper.addressRequestDtoToAddress(createRequestDto.getAddress()))
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .lastModifiedOn(LocalDateTime.now())
                .totalLikes(0L)
                .signupApprovedByAdmin(true)
                .build();

        User user = Mockito.mock(User.class);
        doReturn(Optional.of(user)).when(userRepository).findById(anyLong());
        doNothing().when(user).setType(any(UserType.class));
        doNothing().when(user).setUserTypeId(anyLong());
        doReturn(user).when(userRepository).save(user);

        doReturn(entity).when(teacherRepository).save(any(Teacher.class));

        TeacherResponseDto responseDto = teacherService.save(createRequestDto);

        assertNotNull(responseDto);
        assertEquals(firstName, responseDto.getFirstName());
        assertEquals(lastName, responseDto.getLastName());
        assertEquals(hourlyRates, responseDto.getHourlyRates());
        assertEquals(modalities, responseDto.getModalities());
        assertEquals(TeachingMasteryLevel.COLLEGE, responseDto.getProficiencies().get(0).getMasteryLevel());
        assertEquals("LITERATURE", responseDto.getProficiencies().get(0).getSubject().getName());
        assertEquals(workingScheduleCreateRequestDto.getCheckIn(), responseDto.getWeeklyWorkingSchedule().getCheckIn());
        assertEquals(workingScheduleCreateRequestDto.getCheckOut(), responseDto.getWeeklyWorkingSchedule().getCheckOut());
        assertEquals(1L, responseDto.getProviderCategoryId());
        assertEquals(profilePictureUrl, responseDto.getProfilePictureUrl());
        assertEquals(shortDescription, responseDto.getShortDescription());
        assertEquals(fullDescription, responseDto.getFullDescription());
        assertEquals(createRequestDto.getAddress().getCountry(), responseDto.getAddress().getCountry());
        assertEquals(createRequestDto.getAddress().getProvince(), responseDto.getAddress().getProvince());
        assertEquals(createRequestDto.getAddress().getStreetName(), responseDto.getAddress().getStreetName());
        assertEquals(createRequestDto.getAddress().getStreetNumber(), responseDto.getAddress().getStreetNumber());
        assertTrue(responseDto.isEnabled());
        assertEquals(0L, responseDto.getTotalLikes());
        assertFalse(responseDto.isSignupApprovedByAdmin());

        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    public void shouldGetTeacherById(){
        Long userId = 1L;
        String firstName = "Bilbo";
        String lastName = "Bolson";
        String email = "mountainthief@gmail.com";
        String streetName = "Av. Cabildo";
        String streetNumber = "222";

        String shortDescription = "Los atajos cortos traen retrasos largos, pero las posadas los alargan todavía más";
        String fullDescription = "No todo lo que es oro, reluce, ni toda la gente errante anda perdida; a las raíces" +
                " profundas no llega la escarcha, el viejo vigoroso no se marchita. De las cenizas subirá un fuego," +
                " y una luz asomará en las sombras; el descoronado será de nuevo rey, forjarán otra vez la espada rota";

        Map<Currency, BigDecimal> hourlyRates = new HashMap<>();
        hourlyRates.put(Currency.ARS, BigDecimal.valueOf(500));

        Map<Modality, Boolean> modalities = new HashMap<>();
        modalities.put(Modality.FACE_TO_FACE, true);

        List<TeachingProficiency> proficiencies = new ArrayList<>();
        TeachingProficiency proficiency = new TeachingProficiency();
        proficiency.setMasteryLevel(TeachingMasteryLevel.COLLEGE);
        proficiency.setSubject(new TeachingSubject("LITERATURE"));
        proficiencies.add(proficiency);

        WeeklyWorkingSchedule workingSchedule= new WeeklyWorkingSchedule();
        workingSchedule.setCheckIn(LocalTime.of(9, 0, 0));
        workingSchedule.setCheckOut(LocalTime.of(18, 0, 0));
        workingSchedule.setMonday(true);
        workingSchedule.setTuesday(true);


        Address address= new Address();
        address.setCountry(Country.ARGENTINA);
        address.setProvince(Province.BUENOS_AIRES);
        address.setStreetName(streetName);
        address.setStreetNumber(streetNumber);

        String profilePictureUrl = "www.ereborpic.com/me_in_the_vault.jpg";

        Teacher teacher = Teacher.builder()
                .userId(1L)
                .firstName(firstName)
                .lastName(lastName)
                .hourlyRates(hourlyRates)
                .modalities(modalities)
                .proficiencies(proficiencies)
                .weeklyWorkingSchedule(workingSchedule)
                .providerCategoryId(1L)
                .profilePictureUrl(profilePictureUrl)
                .shortDescription(shortDescription)
                .fullDescription(fullDescription)
                .address(address)
                .enabled(true)
                .createdOn(LocalDateTime.now())
                .lastModifiedOn(LocalDateTime.now())
                .totalLikes(0L)
                .signupApprovedByAdmin(true)
                .build();
        ReflectionTestUtils.setField(teacher, "id", 10L);

        doReturn(Optional.of(teacher)).when(teacherRepository).findById(10L);

        TeacherResponseDto responseDto = teacherService.getById(10L);

        assertNotNull(responseDto);
        assertEquals(firstName, responseDto.getFirstName());
        assertEquals(lastName, responseDto.getLastName());
        assertEquals(hourlyRates, responseDto.getHourlyRates());
        assertEquals(modalities, responseDto.getModalities());
        assertEquals(TeachingMasteryLevel.COLLEGE, responseDto.getProficiencies().get(0).getMasteryLevel());
        assertEquals("LITERATURE", responseDto.getProficiencies().get(0).getSubject().getName());
        assertEquals(workingSchedule.getCheckIn(), responseDto.getWeeklyWorkingSchedule().getCheckIn());
        assertEquals(workingSchedule.getCheckOut(), responseDto.getWeeklyWorkingSchedule().getCheckOut());
        assertEquals(1L, responseDto.getProviderCategoryId());
        assertEquals(profilePictureUrl, responseDto.getProfilePictureUrl());
        assertEquals(shortDescription, responseDto.getShortDescription());
        assertEquals(fullDescription, responseDto.getFullDescription());
        assertEquals(address.getCountry().name(), responseDto.getAddress().getCountry());
        assertEquals(address.getProvince().name(), responseDto.getAddress().getProvince());
        assertEquals(address.getStreetName(), responseDto.getAddress().getStreetName());
        assertEquals(address.getStreetNumber(), responseDto.getAddress().getStreetNumber());
        assertTrue(responseDto.isEnabled());
        assertEquals(0L, responseDto.getTotalLikes());
        assertFalse(responseDto.isSignupApprovedByAdmin());

        verify(teacherRepository, times(1)).findById(10L);
    }


}