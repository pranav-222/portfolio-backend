package portfolio_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import portfolio_backend.entity.ContactMessage;
import portfolio_backend.repository.ContactRepository;

import java.time.LocalDateTime;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    public void saveMessage(ContactMessage message) {

        message.setCreatedAt(LocalDateTime.now());

        repository.save(message);

        sendEmail(message);
    }

    @Async
    public void sendEmail(ContactMessage message) {

        try {

            SimpleMailMessage mail =
                    new SimpleMailMessage();

            mail.setTo("prnv222@gmail.com");

            mail.setSubject(
                    "Portfolio Contact - "
                            + message.getSubject());

            mail.setText(
                    "Name: " + message.getName()
                            + "\nEmail: " + message.getEmail()
                            + "\n\nMessage:\n"
                            + message.getMessage());

            mailSender.send(mail);

        } catch (Exception e) {

            System.out.println("Mail failed: "
                    + e.getMessage());
        }
    }
}