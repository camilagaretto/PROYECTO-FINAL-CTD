package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender_user;

    @Override
    public void sendEmailNotification(String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject("Bienvenido a Appkademy!");

            MimeMessageHelper helper= new MimeMessageHelper(message, true);

            // TODO: obtener email del usuario.. en student tenemos solo el ID del usuario.. faltan los datos del usuario
            helper.setTo("rcvmartin@yahoo.com.ar");
            //helper.setTo("bordet.julian@gmail.com");
            helper.setFrom(sender_user); // "appkademy38@gmail.com");

            // TODO: completar el body
            //helper.setText("Hola " + entity.getFirstName() + " " +  entity.getLastName() + ", bienvenido!! ");
            helper.setText("Hola bienvenido!! ");
            javaMailSender.send(message);
        }
        catch (MessagingException e) {
            throw new RuntimeException(e); // TODO: manejar este error.. loguearlo, etc
        }
        catch (Exception ex) {
            // throw new RuntimeException(ex); // TODO: manejar este error.. loguearlo, etc
        }
    }
}
