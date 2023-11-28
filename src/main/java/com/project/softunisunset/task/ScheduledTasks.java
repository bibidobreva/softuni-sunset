package com.project.softunisunset.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(cron = "0 0 20 * * ?") // Run every day at 8:00 PM
    public void giveReminder() {
        // logic for task here
    }
}
