package com.dailycodebuffer.client.service;

import java.io.File;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.dailycodebuffer.client.entity.PasswordResetToken;
import com.dailycodebuffer.client.entity.User;
import com.dailycodebuffer.client.entity.VerificationToken;
import com.dailycodebuffer.client.model.Usermodel;
import com.dailycodebuffer.client.repository.PasswordResetTokenRepository;
import com.dailycodebuffer.client.repository.UserRepository;
import com.dailycodebuffer.client.repository.VerificationTokenRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User registerUser(Usermodel usermodel) {
        User user = new User();
        user.setEmail(usermodel.getEmail());
        user.setFirstName(usermodel.getFirstName());
        user.setLastName(usermodel.getLastName());
        user.setPassword(passwordEncoder.encode(usermodel.getPassword()));
        user.setRole(usermodel.getRole());

        userRepository.save(user);
        return user;

    }

    @Override
    public void sendSimpleEmail(User user, String url) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("invaliduser409@gmail.com");
        message.setTo(user.getEmail());
        message.setText("Click the link to verify your account: {}" + url);
        message.setSubject("Verification Link Action");

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    @Override
    public void sendEmailWithAttachment(User user, String url, String attachment) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("invaliduser409@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText("Click the link to verify your account: {}" + url);
        mimeMessageHelper.setSubject("Verification Link Action");

        FileSystemResource fileSystem
                = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);

        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");

    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if(verificationToken == null)
        {
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        
        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()<=0)
        {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }
        
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public User findUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken == null)
        {
            return "invalid";
        }
        // User user = passwordResetToken.getUser();
        Calendar cal = Calendar.getInstance();
        
        if(passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime()<=0)
        {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

       
}