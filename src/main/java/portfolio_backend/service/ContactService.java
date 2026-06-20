package portfolio_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import portfolio_backend.entity.ContactMessage;

@Service
public class ContactService {

    @Autowired
    private JavaMailSender mailSender;

    public void saveMessage(ContactMessage message) {

        System.out.println("CONTACT REQUEST RECEIVED");

        sendEmail(message);
    }

    public void sendEmail(ContactMessage message) {

        try {

            System.out.println("Attempting to send email...");

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo("prnv222@gmail.com");

            mail.setSubject(
                    "Portfolio Contact - "
                            + message.getSubject());

            mail.setText(
                    "Name: " + message.getName()
                            + "\nEmail: " + message.getEmail()
                            + "\nSubject: " + message.getSubject()
                            + "\n\nMessage:\n"
                            + message.getMessage());

            System.out.println("TO = prnv222@gmail.com");
            System.out.println("SUBJECT = " + mail.getSubject());
            System.out.println("MAIL SENDER STARTING");

            mailSender.send(mail);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (Exception e) {

            System.out.println("MAIL FAILED");
            e.printStackTrace();
        }
    }
}