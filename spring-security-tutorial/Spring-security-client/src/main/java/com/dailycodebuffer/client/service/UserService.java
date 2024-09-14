package com.dailycodebuffer.client.service;

import java.util.Optional;

import com.dailycodebuffer.client.entity.User;
import com.dailycodebuffer.client.entity.VerificationToken;
import com.dailycodebuffer.client.model.Usermodel;

import jakarta.mail.MessagingException;

public interface UserService {

    User registerUser(Usermodel usermodel);

    public void sendSimpleEmail(User user, String url);

    public void sendEmailWithAttachment(User user, String url, String attachment) throws MessagingException;

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
    
}