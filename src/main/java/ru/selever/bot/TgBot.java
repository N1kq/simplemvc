package ru.selever.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TgBot extends TelegramLongPollingBot {
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    Logger logger = LoggerFactory.getLogger(TgBot.class);
    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    @Override
    public String getBotUsername(){
        return botname;
    }
    @Override
    public void onUpdateReceived(Update update){
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();

        logger.info(user.getId() + " wrote " + msg.getText());
        sendText(id, msg.getText());
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                        .chatId(who.toString()) //Who are we sending a message to
                        .text(what).build();    //Message content
        try{
            execute(sm);
            logger.info("Bot wrote " + what + " to " + who);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
