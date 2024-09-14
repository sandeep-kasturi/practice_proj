package com.dailycodebuffer.client.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.dailycodebuffer.client.entity.User;
import com.dailycodebuffer.client.event.RegistrationCompleteEvent;
import com.dailycodebuffer.client.service.UserService;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create the verification token for the user with link 
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        
        //send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        //Ideally we have to implement sendVerificationEmail() method here
        log.info("Click the link to verify your account: {}",url);

        //sending url through SMTP mail without any attachments
        userService.sendSimpleEmail(user, url);
    
        //sending url through SMTP mail with attachments
        try {
            userService.sendEmailWithAttachment(user, url, "C:\\Users\\sandeep.kasturi\\Desktop\\b-end\\5.Spring Security.txt");
        } catch (MessagingException e) {
            System.out.println(e);
        }

    }
    
}