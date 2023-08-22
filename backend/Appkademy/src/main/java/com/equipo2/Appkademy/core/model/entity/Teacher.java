package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher extends NaturalPersonProvider {

    @Column(name = "short_description", nullable = true, length = 100)
    private String shortDescription;

    @Column(name = "full_description", nullable = true, columnDefinition = "TEXT")
    private String fullDescription;

    @ElementCollection
    @MapKeyColumn(name = "currency", nullable = false)
    @CollectionTable(name = "teacher_hourly_rate", joinColumns = @JoinColumn(name = "teacher_id"))
    private Map<Currency, BigDecimal> hourlyRates;

    @ElementCollection
    @MapKeyColumn(name = "modality")
    @CollectionTable(name = "teacher_modality", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "value")
    private Map<Modality, Boolean> modalities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private List<TeachingProficiency> proficiencies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekly_working_schedule_id", nullable = false)
    private WeeklyWorkingSchedule weeklyWorkingSchedule;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false, insertable = false, updatable = false)
    private List<ScheduledAppointment> scheduledAppointments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = true)
    private List<Characteristic> characteristics;

    protected Teacher(Builder builder){
        setUserId(builder.userId);
        setHourlyRates(builder.hourlyRates);
        setModalities(builder.modalities);
        setProficiencies(builder.proficiencies);
        setWeeklyWorkingSchedule(builder.weeklyWorkingSchedule);
        setScheduledAppointments(builder.scheduledAppointments);
        setProviderCategoryId(builder.providerCategoryId);
        setTotalLikes(builder.totalLikes);
        setProfilePictureUrl(builder.profilePictureUrl);
        setIdentityVerified(builder.identityVerified);
        setEnabled(builder.enabled);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setShortDescription(builder.shortDescription);
        setFullDescription(builder.fullDescription);
        setAddress(builder.address);
        setTotalLikes(builder.totalLikes);
        setCreatedOn(builder.createdOn);
        setLastModifiedOn(builder.lastModifiedOn);
        setCharacteristics(builder.characteristics);
    }


    public void addScheduledAppointment(ScheduledAppointment scheduledAppointment){
        if(Objects.isNull(scheduledAppointments)){
            scheduledAppointments = new ArrayList<>();
        }
        scheduledAppointments.add(scheduledAppointment);
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Long userId;
        private Map<Currency, BigDecimal> hourlyRates;
        private Map<Modality, Boolean> modalities;
        private List<TeachingProficiency> proficiencies;
        private WeeklyWorkingSchedule weeklyWorkingSchedule;
        private List<ScheduledAppointment> scheduledAppointments;
        private Long providerCategoryId;
        private Long totalLikes;
        private String profilePictureUrl;
        private boolean identityVerified;
        private boolean enabled;
        private LocalDateTime createdOn;
        private LocalDateTime lastModifiedOn;
        private String firstName;
        private String lastName;
        private String shortDescription;
        private String fullDescription;
        private Address address;

        private List<Characteristic> characteristics;

        private boolean signupApprovedByAdmin;

        public Builder userId(Long _userId){
            userId = _userId;
            return this;
        }

        public Builder hourlyRates(Map<Currency, BigDecimal> _hourlyRates){
            hourlyRates = _hourlyRates;
            return this;
        }

        public Builder modalities(Map<Modality, Boolean> _modalities){
            modalities = _modalities;
            return this;
        }

        public Builder proficiencies(List<TeachingProficiency> _proficiencies){
            proficiencies = _proficiencies;
            return this;
        }

        public Builder weeklyWorkingSchedule(WeeklyWorkingSchedule _weeklyWorkingSchedule){
            weeklyWorkingSchedule = _weeklyWorkingSchedule;
            return this;
        }

        public Builder scheduledAppointments(List<ScheduledAppointment> _scheduledAppointments){
            scheduledAppointments = _scheduledAppointments;
            return this;
        }

        public Builder providerCategoryId(Long _providerCategoryId){
            providerCategoryId = _providerCategoryId;
            return this;
        }

        public Builder totalLikes(Long _totalLikes){
            totalLikes = _totalLikes;
            return this;
        }

        public Builder profilePictureUrl(String _profilePictureUrl){
            profilePictureUrl = _profilePictureUrl;
            return this;
        }

        public Builder identityVerified(boolean _identityVerified){
            identityVerified = _identityVerified;
            return this;
        }

        public Builder enabled(boolean _enabled){
            enabled = _enabled;
            return this;
        }

        public Builder createdOn(LocalDateTime _createdOn){
            createdOn = _createdOn;
            return this;
        }

        public Builder lastModifiedOn(LocalDateTime _lastModifiedOn){
            lastModifiedOn = _lastModifiedOn;
            return this;
        }

        public Builder firstName(String _firstName){
            firstName = _firstName;
            return this;
        }

        public Builder lastName(String _lastName){
            lastName = _lastName;
            return this;
        }

        public Builder shortDescription(String _shortDescription){
            shortDescription = _shortDescription;
            return this;
        }

        public Builder fullDescription(String _fullDescription){
            fullDescription = _fullDescription;
            return this;
        }

        public Builder address(Address _address){
            address = _address;
            return this;
        }

        public Builder signupApprovedByAdmin(boolean _signupApprovedByAdmin){
            signupApprovedByAdmin = _signupApprovedByAdmin;
            return this;
        }

        public Builder characteristics(List<Characteristic> _characteristics){
            characteristics = _characteristics;
            return this;
        }

        public Teacher build(){
            return new Teacher(this);
        }
    }

}
