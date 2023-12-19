package com.project.softunisunset.task;

import com.project.softunisunset.models.entity.User;
import com.project.softunisunset.service.EmailService;
import com.project.softunisunset.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private EmailService emailService;
    private UserService userService;

    public ScheduledTasks(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

   @Scheduled(cron = "0 0 20 * * ?") // Run every day at 8:00 PM
   //@Scheduled(fixedRate = 60000) // Run every 1 minute (in milliseconds)
    public void giveReminder() {
        try {

            List<User> users = userService.getAllUsers();


            for (User user : users) {
                String recipientEmail = user.getEmail();
                String subject = "Reminder";
                String body = "Hello " + user.getFirstName() + ",\n\nThis is a reminder email.";

                emailService.sendEmail(recipientEmail, subject, body);
            }

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }
}
