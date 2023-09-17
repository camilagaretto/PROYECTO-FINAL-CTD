package com.equipo2.Appkademy.core.service;

import java.time.LocalDateTime;

public interface NotificationService {

    void sendEmailNotification(String fullName, String email);

    void sendEmailNotificationSuccessfullAppointment(LocalDateTime startsOn, LocalDateTime endsOn, String studentFullName, String email, String teacherFullName, String teacherEmail);
}
