package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher extends NaturalPersonProvider {

    @ElementCollection
    @MapKeyColumn(name = "currency", nullable = false)
    @CollectionTable(name = "teacher_hourly_rate", joinColumns = @JoinColumn(name = "teacher_id"))
    private Map<Currency, BigDecimal> hourlyRates;

    //TODO: persist modality enum as string? currently is persisted as ordinal
    @ElementCollection
    @MapKeyColumn(name = "modality")
    @CollectionTable(name = "teacher_modality", joinColumns = @JoinColumn(name = "teacher_id"))
    @Column(name = "value")
    private Map<Modality, Boolean> modalities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", nullable = false)
    private List<TeachingProficiency> proficiencies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weekly_working_schedule_id", nullable = false)
    private WeeklyWorkingSchedule weeklyWorkingSchedule;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false)
    private List<ScheduledAppointment> scheduledAppointments;

    protected Teacher(Builder builder){
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
        setCreatedOn(builder.createdOn);
        setLastModifiedOn(builder.lastModifiedOn);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setShortDescription(builder.shortDescription);
        setFullDescription(builder.fullDescription);
        setAddress(builder.address);
        setTotalLikes(builder.totalLikes);
        setEmail(builder.email);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
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

        private String email;

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

        public Builder email(String _email){
            email = _email;
            return this;
        }

        public Teacher build(){
            return new Teacher(this);
        }
    }

}