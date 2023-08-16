package com.equipo2.Appkademy.core.model.entity;

import com.equipo2.Appkademy.core.model.enums.Currency;
import com.equipo2.Appkademy.core.model.enums.Modality;
import com.equipo2.Appkademy.core.model.enums.ReviewDecision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher_signup_request")
public class TeacherSignupRequest extends BaseSqlEntity<Long> {

    @Column(name = "request_created_on")
    private LocalDateTime requestCreatedOn;

    @Column(name = "request_has_been_reviewed")
    private boolean requestHasBeenReviewed;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_descision")
    private ReviewDecision reviewDecision;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", unique = true)
    private Teacher teacher;

    //deberían ser atributos del teacher createRequestDto
    //private MultipartFile profilePicture;
    //private List<MultipartFile> secondaryPictures;
    //private List<MultipartFile> identityValidationDocuents;


    protected TeacherSignupRequest(TeacherSignupRequest.Builder builder){
        teacher = new Teacher();
        teacher.setHourlyRates(builder.hourlyRates);
        teacher.setModalities(builder.modalities);
        teacher.setProficiencies(builder.proficiencies);
        teacher.setWeeklyWorkingSchedule(builder.weeklyWorkingSchedule);
        teacher.setScheduledAppointments(builder.scheduledAppointments);
        teacher.setProviderCategoryId(builder.providerCategoryId);
        teacher.setTotalLikes(builder.totalLikes);
        teacher.setProfilePictureUrl(builder.profilePictureUrl);
        teacher.setIdentityVerified(builder.identityVerified);
        teacher.setEnabled(builder.enabled);
        teacher.setFirstName(builder.firstName);
        teacher.setLastName(builder.lastName);
        teacher.setShortDescription(builder.shortDescription);
        teacher.setFullDescription(builder.fullDescription);
        teacher.setAddress(builder.address);
        teacher.setTotalLikes(builder.totalLikes);
        teacher.setEmail(builder.email);
        teacher.setCreatedOn(builder.createdOn);
        teacher.setLastModifiedOn(builder.lastModifiedOn);
        setRequestCreatedOn(LocalDateTime.now());
        setRequestHasBeenReviewed(builder.requestHasBeenReviewed);
        setReviewDecision(builder.reviewDecision);
    }

    public static TeacherSignupRequest.Builder builder(){
        return new TeacherSignupRequest.Builder();
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

        private LocalDateTime requestCreatedOn;
        private boolean requestHasBeenReviewed;
        private ReviewDecision reviewDecision;

        public TeacherSignupRequest.Builder hourlyRates(Map<Currency, BigDecimal> _hourlyRates){
            hourlyRates = _hourlyRates;
            return this;
        }

        public TeacherSignupRequest.Builder modalities(Map<Modality, Boolean> _modalities){
            modalities = _modalities;
            return this;
        }

        public TeacherSignupRequest.Builder proficiencies(List<TeachingProficiency> _proficiencies){
            proficiencies = _proficiencies;
            return this;
        }

        public TeacherSignupRequest.Builder weeklyWorkingSchedule(WeeklyWorkingSchedule _weeklyWorkingSchedule){
            weeklyWorkingSchedule = _weeklyWorkingSchedule;
            return this;
        }

        public TeacherSignupRequest.Builder scheduledAppointments(List<ScheduledAppointment> _scheduledAppointments){
            scheduledAppointments = _scheduledAppointments;
            return this;
        }

        public TeacherSignupRequest.Builder providerCategoryId(Long _providerCategoryId){
            providerCategoryId = _providerCategoryId;
            return this;
        }

        public TeacherSignupRequest.Builder totalLikes(Long _totalLikes){
            totalLikes = _totalLikes;
            return this;
        }

        public TeacherSignupRequest.Builder profilePictureUrl(String _profilePictureUrl){
            profilePictureUrl = _profilePictureUrl;
            return this;
        }

        public TeacherSignupRequest.Builder identityVerified(boolean _identityVerified){
            identityVerified = _identityVerified;
            return this;
        }

        public TeacherSignupRequest.Builder enabled(boolean _enabled){
            enabled = _enabled;
            return this;
        }

        public TeacherSignupRequest.Builder createdOn(LocalDateTime _createdOn){
            createdOn = _createdOn;
            return this;
        }

        public TeacherSignupRequest.Builder lastModifiedOn(LocalDateTime _lastModifiedOn){
            lastModifiedOn = _lastModifiedOn;
            return this;
        }

        public TeacherSignupRequest.Builder firstName(String _firstName){
            firstName = _firstName;
            return this;
        }

        public TeacherSignupRequest.Builder lastName(String _lastName){
            lastName = _lastName;
            return this;
        }

        public TeacherSignupRequest.Builder shortDescription(String _shortDescription){
            shortDescription = _shortDescription;
            return this;
        }

        public TeacherSignupRequest.Builder fullDescription(String _fullDescription){
            fullDescription = _fullDescription;
            return this;
        }

        public TeacherSignupRequest.Builder address(Address _address){
            address = _address;
            return this;
        }

        public TeacherSignupRequest.Builder email(String _email){
            email = _email;
            return this;
        }

        public TeacherSignupRequest.Builder requestCreatedOn(LocalDateTime _requestCreatedOn){
            requestCreatedOn = _requestCreatedOn;
            return this;
        }

        public TeacherSignupRequest.Builder requestHasBeenReviewed(boolean _requestHasBeenReviewed){
            requestHasBeenReviewed = _requestHasBeenReviewed;
            return this;
        }

        public TeacherSignupRequest.Builder reviewDecision(ReviewDecision _reviewDecision){
            reviewDecision = _reviewDecision;
            return this;
        }

        public TeacherSignupRequest build(){
            return new TeacherSignupRequest(this);
        }
    }

}
