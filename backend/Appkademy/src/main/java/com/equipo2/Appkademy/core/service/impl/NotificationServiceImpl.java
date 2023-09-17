package com.equipo2.Appkademy.core.service.impl;

import com.equipo2.Appkademy.core.model.repository.StudentRepository;
import com.equipo2.Appkademy.core.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender_user;

    @Autowired
    private StudentRepository studentRepository;

    private String htmlTemplateWelcome = "<html>\n" +
            "  <head>\n" +
            "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
            "  </head>\n" +
            "  <body>\n" +
            "    <h1>¡Te damos la bienvenida!</h1>\n" +
            "    <p>Por favor, haz clic en el siguiente botón para iniciar sesión:</p>\n" +
            "    <a href=\"URL_DE_INICIO_DE_SESION\" class=\"button\">Iniciar Sesión</a>\n" +
            "    <p>Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos.</p>\n" +
            "  </body>\n" +
            "  </html>";


    @Override
    public void sendEmailNotification(String fullName, String email) {
        Thread thread = new Thread(() -> {
            // Asynchronous task
            System.out.println("Async send email task started");
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                message.setSubject("Bienvenido a Appkademy!");
                MimeMessageHelper helper= new MimeMessageHelper(message, true);
                helper.setTo(email);
                helper.setFrom(sender_user); // "appkademy38@gmail.com");
                helper.setText("Hola " + fullName + "!", htmlTemplateWelcome);
                javaMailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e); // TODO: manejar este error.. loguearlo, etc
            } catch (Exception ex) {
                // throw new RuntimeException(ex); // TODO: manejar este error.. loguearlo, etc
            }
            System.out.println("Send email async task completed");
        });
        thread.start();
    }

    @Override
    public void sendEmailNotificationSuccessfullAppointment(LocalDateTime startsOn, LocalDateTime endsOn, String studentFullName, String email) {
        Thread thread = new Thread(() -> {
            // Asynchronous task
            System.out.println("Async send email task started");
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                message.setSubject("Nueva reserva realizada");
                MimeMessageHelper helper= new MimeMessageHelper(message, true);
                helper.setTo(email);
                helper.setFrom(sender_user); // "appkademy38@gmail.com");
                helper.setText("Hola " + studentFullName + "!." + "Te enviamos la información sobre tu reserva: Comienza: " + startsOn + ". Finaliza: " + endsOn + ".");
                javaMailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e); // TODO: manejar este error.. loguearlo, etc
            } catch (Exception ex) {
                // throw new RuntimeException(ex); // TODO: manejar este error.. loguearlo, etc
            }
            System.out.println("Send email async task completed");
        });
        thread.start();
    }
}
