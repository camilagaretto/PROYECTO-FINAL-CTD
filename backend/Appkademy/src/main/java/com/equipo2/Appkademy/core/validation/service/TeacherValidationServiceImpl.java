package com.equipo2.Appkademy.core.validation.service;

import com.equipo2.Appkademy.core.model.entity.Characteristic;
import com.equipo2.Appkademy.core.model.entity.TeachingProficiency;
import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.model.repository.TeacherRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingProficiencyRepository;
import com.equipo2.Appkademy.core.model.repository.TeachingSubjectRepository;
import com.equipo2.Appkademy.core.security.model.repository.CharacteristicRespository;
import com.equipo2.Appkademy.core.security.model.repository.UserRepository;
import com.equipo2.Appkademy.rest.error.BusinessException;
import com.equipo2.Appkademy.rest.error.ErrorCodes;
import com.equipo2.Appkademy.rest.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
public class TeacherValidationServiceImpl {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeachingProficiencyRepository teachingProficiencyRepository;

    @Autowired
    private TeachingSubjectRepository teachingSubjectRepository;

    @Autowired
    private CharacteristicRespository characteristicRespository;

    public void assertHourlyRatesAreValid(Map<Currency, BigDecimal> hourlyRates) {
        boolean rateWithNegativeValueExists = hourlyRates.entrySet().stream()
                .anyMatch(hourlyRate -> 0 >= hourlyRate.getValue().compareTo(BigDecimal.valueOf(0)));

        if(rateWithNegativeValueExists){
            throw new BusinessException(ErrorCodes.HOURLY_RATES_VALUES_CANNOT_BE_NEGATIVE_OR_ZERO);
        }
    }

    public void assertUserDoesNotAlreadyExist(Long userId) {
        if(teacherRepository.findByUserId(userId).isPresent() || studentRepository.findByUserId(userId).isPresent()){
            throw new BusinessException(ErrorCodes.USER_ID_IS_ALREADY_ATTACHED_TO_ANOTHER_ENTITY);
        }
    }

    public void assertAllTeachingProficienciesExist(List<TeachingProficiency> proficiencies, List<Long> dtoProficiencyIds){
        if(proficiencies.size() != dtoProficiencyIds.size()){
            throw new NotFoundException(ErrorCodes.AT_LEAST_ONE_PROFICIENCY_DOESNT_EXIST);
        }
    }

    public List<Characteristic> assertCharacteristicsExists(List<Long> createRequestCharacteristicIds) {
        List<Characteristic> characteristicEntities = characteristicRespository.findAllById(createRequestCharacteristicIds);

        if(characteristicEntities.size() != createRequestCharacteristicIds.size()){
            throw new NotFoundException(ErrorCodes.AT_LEAST_ONE_CHARACTERISTIC_IN_CREATE_REQUEST_DTO_DOESNT_EXISTS);
        }

        return characteristicEntities;
    }
}
