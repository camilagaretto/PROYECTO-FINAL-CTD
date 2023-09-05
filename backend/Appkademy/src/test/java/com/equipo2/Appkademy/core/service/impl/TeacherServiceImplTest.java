package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.mapper.AppkademyMapperImpl;
import com.equipo2.Appkademy.core.model.entity.*;
import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.*;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingProficiencyRepository;
import com.equipo2.Appkademy.core.security.model.User;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.core.validation.service.TeacherValidationServiceImpl;
import com.equipo2.Appkademy.rest.dto.filter.TeacherFilterDto;
import com.equipo2.Appkademy.rest.dto.request.AddressRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.TeacherUpdateRequestDto;
import com.equipo2.Appkademy.rest.dto.request.WeeklyWorkingScheduleCreateRequestDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherResponseDto;
import com.equipo2.Appkademy.rest.dto.response.TeacherSearchResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Mock
    private TeachingProficiencyRepository teachingProficiencyRepository;

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

    @Test
    public void shouldSearchTeacherByCity(){
        TeacherFilterDto filterDto = new TeacherFilterDto();
        filterDto.setPageSize(10);
        filterDto.setPageNumber(1);
        filterDto.setTeacherIds(Arrays.asList(1L));

        Teacher teacher = Mockito.mock(Teacher.class);
        doReturn(1L).when(teacher).getId();

        Page<Teacher> teacherPage = new PageImpl<>(Arrays.asList(teacher), PageRequest.of(0, 10), Arrays.asList(teacher).size());

        doReturn(teacherPage).when(teacherRepository).findAll(any(Specification.class), any(Pageable.class));

        TeacherSearchResponseDto searchResponseDto = teacherService.search(filterDto);

        assertNotNull(searchResponseDto);
        assertEquals(1L, searchResponseDto.getSearchResults().get(0).getId());

        verify(teacherRepository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    public void shouldDeleteTeacher(){
        Long id = 1L;
        Teacher teacher = Mockito.mock(Teacher.class);

        doReturn(Optional.of(teacher)).when(teacherRepository).findById(id);
        doNothing().when(teacherRepository).deleteById(id);

        teacherService.delete(id);

        verify(teacherRepository, times(1)).findById(id);
        verify(teacherRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdateTeacher(){
        Long id = 10L;

        Long userId = 1L;
        String firstName = "Bilbo";
        String lastName = "Bolson";
        String email = "mountainthief@gmail.com";
        String streetName = "Av. Cabildo";
        String streetNumber = "222";

        List<TeachingProficiency> proficiencies = new ArrayList<>();
        TeachingProficiency proficiency = new TeachingProficiency();
        proficiency.setMasteryLevel(TeachingMasteryLevel.COLLEGE);
        proficiency.setSubject(new TeachingSubject("LITERATURE"));
        proficiencies.add(proficiency);

        String updatedFirstName = "updatedName";
        String updatedLastName = "updatedLastName";

        Map<Currency, BigDecimal> updatedHourlyRates = new HashMap<>();
        updatedHourlyRates.put(Currency.ARS, BigDecimal.valueOf(500));

        Map<Modality, Boolean> updatedModalities = new HashMap<>();
        updatedModalities.put(Modality.FACE_TO_FACE, true);

        WeeklyWorkingScheduleCreateRequestDto updatedWorkingScheduleCreateRequestDto = new WeeklyWorkingScheduleCreateRequestDto();
        updatedWorkingScheduleCreateRequestDto.setCheckIn(LocalTime.of(9, 0, 0));
        updatedWorkingScheduleCreateRequestDto.setCheckOut(LocalTime.of(18, 0, 0));
        updatedWorkingScheduleCreateRequestDto.setMonday(true);
        updatedWorkingScheduleCreateRequestDto.setTuesday(true);

        String updatedProfilePictureUrl = "www.ereborpic.com/me_in_the_vault.jpg";

        AddressRequestDto addressRequestDto = new AddressRequestDto();
        addressRequestDto.setCountry(Country.ARGENTINA.name());
        addressRequestDto.setProvince(Province.BUENOS_AIRES.name());
        addressRequestDto.setStreetName(streetName);
        addressRequestDto.setStreetNumber(streetNumber);

        String updatedShortDescription = "Los atajos cortos traen retrasos largos, pero las posadas los alargan todavía más";
        String updatedFullDescription = "No todo lo que es oro, reluce, ni toda la gente errante anda perdida; a las raíces" +
                " profundas no llega la escarcha, el viejo vigoroso no se marchita. De las cenizas subirá un fuego," +
                " y una luz asomará en las sombras; el descoronado será de nuevo rey, forjarán otra vez la espada rota";

        Teacher teacher = new Teacher();
        ReflectionTestUtils.setField(teacher, "id", id);

        doReturn(Optional.of(teacher)).when(teacherRepository).findById(id);
        doNothing().when(teacherValidationService).assertHourlyRatesAreValid(anyMap());

        List<TeachingProficiency> teachingProficiencies = new ArrayList<>();
        doReturn(teachingProficiencies).when(teachingProficiencyRepository).findAllById(anyList());

        doNothing().when(teacherValidationService).assertAllTeachingProficienciesExist(anyList(), anyList());

        TeacherUpdateRequestDto updateRequestDto = new TeacherUpdateRequestDto();
        updateRequestDto.setFirstName(updatedFirstName);
        updateRequestDto.setLastName(updatedLastName);
        updateRequestDto.setHourlyRates(updatedHourlyRates);
        updateRequestDto.setModalities(updatedModalities);
        updateRequestDto.setProficiencyIds(Arrays.asList(1L));
        updateRequestDto.setWeeklyWorkingSchedule(updatedWorkingScheduleCreateRequestDto);
        updateRequestDto.setProfilePictureUrl(updatedProfilePictureUrl);
        updateRequestDto.setAddress(addressRequestDto);
        updateRequestDto.setShortDescription(updatedShortDescription);
        updateRequestDto.setFullDescription(updatedFullDescription);
        updateRequestDto.setEnabled(true);

        doReturn(teacher).when(teacherRepository).save(any(Teacher.class));

        TeacherResponseDto responseDto = teacherService.update(id, updateRequestDto);

        ArgumentCaptor<Teacher> captor = ArgumentCaptor.forClass(Teacher.class);
        verify(teacherRepository, times(1)).save(captor.capture());

        Teacher capturedValue = captor.getValue();

        assertNotNull(capturedValue);
        assertEquals(updatedFirstName, capturedValue.getFirstName());
        assertEquals(updatedLastName, capturedValue.getLastName());
        assertTrue(capturedValue.getHourlyRates().containsKey(Currency.ARS));
        assertEquals(BigDecimal.valueOf(500), capturedValue.getHourlyRates().get(Currency.ARS));
        assertTrue(capturedValue.getModalities().containsKey(Modality.FACE_TO_FACE));
        assertEquals(true, capturedValue.getModalities().get(Modality.FACE_TO_FACE));
        //assertEquals(1L, capturedValue.getProficiencies().size());
        //assertEquals(1L, capturedValue.getProficiencies().get(0).getId());
        assertEquals(updatedWorkingScheduleCreateRequestDto.getCheckIn(), capturedValue.getWeeklyWorkingSchedule().getCheckIn());
        assertEquals(updatedWorkingScheduleCreateRequestDto.getCheckOut(), capturedValue.getWeeklyWorkingSchedule().getCheckOut());
        assertEquals(updatedWorkingScheduleCreateRequestDto.isMonday(), capturedValue.getWeeklyWorkingSchedule().isMonday());
        assertEquals(updatedWorkingScheduleCreateRequestDto.isTuesday(), capturedValue.getWeeklyWorkingSchedule().isTuesday());
        assertEquals(updateRequestDto.getProfilePictureUrl(), capturedValue.getProfilePictureUrl());
        assertEquals(updateRequestDto.getAddress().getCountry(), capturedValue.getAddress().getCountry().name());
        assertEquals(updateRequestDto.getAddress().getProvince(), capturedValue.getAddress().getProvince().name());
        assertEquals(updateRequestDto.getAddress().getStreetName(), capturedValue.getAddress().getStreetName());
        assertEquals(updateRequestDto.getAddress().getStreetNumber(), capturedValue.getAddress().getStreetNumber());
        assertEquals(updateRequestDto.getShortDescription(), capturedValue.getShortDescription());
        assertEquals(updateRequestDto.getFullDescription(), capturedValue.getFullDescription());
        assertTrue(capturedValue.isEnabled());
    }




}