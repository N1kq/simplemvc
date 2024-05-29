package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.selever.models.User;

@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String email;
    Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email); //вставить свой e-mail
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }

    public void sendVerificationEmail(User user, String siteURL) throws MailException {
        String verifyURL = siteURL + "/verify?code=" + user.getVerCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(user.geteMail());
        message.setText(verifyURL);
        message.setSubject("Verification");
        mailSender.send(message);
    }
}
