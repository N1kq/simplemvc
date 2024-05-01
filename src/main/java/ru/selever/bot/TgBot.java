package ru.selever.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class TgBot extends TelegramLongPollingBot {

    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    @Override
    public void onUpdateReceived(Update update){

    }

    public String getBotUsername(){
        return botname;
    };
}
