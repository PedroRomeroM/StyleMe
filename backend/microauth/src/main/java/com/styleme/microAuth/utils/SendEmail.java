
package com.styleme.microAuth.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;




@Service
public class SendEmail {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateEngine templateEngine;


    public void sendEmail(TemplateEmail temp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(temp.getTo());
        message.setSubject(temp.getSubject());
        message.setText(temp.getBody());

        mailSender.send(message);
    }

    public void sendEmailWithHtmlTemplate(TemplateEmail temp) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
     
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            
            helper.setTo(temp.getTo());
            helper.setSubject(temp.getSubject());
            
           
            /* 
            ClassPathResource svgResource = new ClassPathResource("static/logo.svg");
            String svgContent = new String(Files.readAllBytes(svgResource.getFile().toPath()),
                    StandardCharsets.UTF_8);
            temp.getContext().setVariable("logo", svgContent);
            */

            String htmlContent = templateEngine.process(temp.getTemplateName(), temp.getContext());
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println("Erro email: " + e.getMessage());
        }
    }
    
}

