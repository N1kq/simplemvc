package ru.selever.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class TgBot extends TelegramLongPollingBot {

    Logger logger = LoggerFactory.getLogger(TgBot.class);
    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    public String getBotUsername(){
        return botname;
    }
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    @Override
    public void onUpdateReceived(Update update){
        logger.info(String.valueOf(update));
    }
}
