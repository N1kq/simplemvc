package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledLog {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledLog.class);
    @Scheduled(cron = "0 * * * * ?", zone = "Europe/Moscow")
    public void SLog() {
        logger.info("Scheduled task complete");
    }
}
