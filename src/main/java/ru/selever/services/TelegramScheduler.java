package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramScheduler {
    @Value("${spring.service.telegramscheduler.botname:'botname'}")
    public String botname;
    @Value("${spring.service.telegramscheduler.bottoken:'bottoken'}")
    public String bottoken;

    private static final Logger logger = LoggerFactory.getLogger(TelegramScheduler.class);
    @Scheduled(cron = "0 * * * * ?", zone = "Europe/Moscow")
    public void tgbot() {
        logger.info("Scheduled task complete");
    }

}
