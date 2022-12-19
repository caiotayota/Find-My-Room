package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.User;
import com.caiotayota.findmyroom.repositories.UserRepository;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final Configuration fmConfiguration; // Freemarker config

    @Autowired
    public EmailService(JavaMailSender javaMailSender, UserRepository userRepository, Configuration fmConfiguration) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.fmConfiguration = fmConfiguration;
    }

    public String sendEmail(String receiver, String subject, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(receiver);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);
            return "Email sent to " + receiver;
        } catch (Exception e) {
            return "Error: Email was not sent";
        }
    }

    public String requestVerificationCode(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow();
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeSentAt(new Date());

        return userRepository.save(user).getVerificationCode();
    }

    protected String generateVerificationCode() {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date());
    }

    public void sendEmailTemplate(String receiver, String subject, Map<String, Object> properties) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setText(getTemplateContent(properties), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getTemplateContent(Map < String, Object >model)     {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate("email-verification-code.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    
}
