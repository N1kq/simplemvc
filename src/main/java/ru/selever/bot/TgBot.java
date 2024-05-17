package ru.selever.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.selever.services.MessageService;
import ru.selever.services.UserService;

@Component
public class TgBot extends TelegramLongPollingBot {
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    Logger logger = LoggerFactory.getLogger(TgBot.class);
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Override
    public String getBotUsername(){
        return botname;
    }
    @Override
    public void onUpdateReceived(Update update){
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        switch(msg.getText()){
            case "/start":
                userService.createUser(update);
                break;
            case "/register":

                break;
            case "/contacts": //Вывод контактов

                break;
            default:
                sendText(id, "Выберите команду из списка:");
                sendText(id,"/help");
                break;
        }
        messageService.createMessage(update);
        logger.info("Обновление обработано успешно");
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
