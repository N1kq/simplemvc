package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//TODO: Не совсем понимаю для чего нам вообще нужен Scheduler, если бот собирает все onUpdateRecieved.
@Service
public class TelegramScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TelegramScheduler.class);
    @Scheduled(cron = "0 * * * * ?", zone = "Europe/Moscow")
    public void tgbot() {
        //logger.info("Scheduled task complete");
    }

}
