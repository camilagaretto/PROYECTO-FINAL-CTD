package com.equipo2.Appkademy.core.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student extends NaturalPersonCustomer {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false, insertable = false, updatable = false)
    private List<ScheduledAppointment> scheduledAppointments;

    protected Student(Builder builder){
        setScheduledAppointments(builder.scheduledAppointments);
        setIdentityVerified(builder.identityVerified);
        setEnabled(builder.enabled);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        //setAddress(builder.address);
        setCreatedOn(builder.createdOn);
        setLastModifiedOn(builder.lastModifiedOn);
        setUserId(builder.userId);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Long userId;
        private List<ScheduledAppointment> scheduledAppointments;
        private List<Long> likedProviderIds;
        private String firstName;
        private String lastName;
        private boolean enabled;
        private Address address;
        private boolean identityVerified;

        private LocalDateTime createdOn;
        private LocalDateTime lastModifiedOn;



        public Student.Builder scheduledAppointments(List<ScheduledAppointment> _scheduledAppointments){
            scheduledAppointments = _scheduledAppointments;
            return this;
        }

        public Student.Builder userId(Long _userId){
            userId = _userId;
            return this;
        }


        public Student.Builder identityVerified(boolean _identityVerified){
            identityVerified = _identityVerified;
            return this;
        }

        public Student.Builder enabled(boolean _enabled){
            enabled = _enabled;
            return this;
        }

        public Student.Builder createdOn(LocalDateTime _createdOn){
            createdOn = _createdOn;
            return this;
        }

        public Student.Builder lastModifiedOn(LocalDateTime _lastModifiedOn){
            lastModifiedOn = _lastModifiedOn;
            return this;
        }

        public Student.Builder firstName(String _firstName){
            firstName = _firstName;
            return this;
        }

        public Student.Builder lastName(String _lastName){
            lastName = _lastName;
            return this;
        }


        public Student.Builder address(Address _address){
            address = _address;
            return this;
        }

        public Student build(){
            return new Student(this);
        }

    }

}
