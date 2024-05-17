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

import ru.selever.models.Dialogs;

import ru.selever.models.User;
import ru.selever.services.MessageService;
import ru.selever.services.UserService;

import java.sql.Timestamp;

import static ru.selever.models.Dialogs.REGISTER;


@Component
public class TgBot extends TelegramLongPollingBot {
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    User user;
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
        //var user = msg.getFrom();
        var id = msg.getFrom().getId();

        switch(msg.getText()){
            case "/start":
                userService.createUser(update);
                user.status=null;
                break;
            case "/register":
                user = userService.getByTgId(update.getMessage().getFrom().getId());
                user.status= REGISTER;
                sendText(user.getUserTgId(),"Пожалуйста, укажите своё имя:");
                process(update, user);
                break;
            case "/contacts":

                break;
        }
        switch(user.status){
            case null:
                break;
            case REGISTER:
                process(update,user);
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

    public void process(Update update, User user){

        if(user.status == null){
            return;
        }
        switch (user.status) {
            case REGISTER -> processRegister(user, update.getMessage().getText());
            //Добавить диалоги сюда, если нужно
        }
    }
    private void processRegister(User user, String msg){
        if(msg.equals("/register")){
            return;
        }
        if(user.getName()==null){
            user.setName(msg);
            sendText(user.getUserTgId(),"Пожалуйста, укажите свою фамилию:");
            return;
        }
        if(user.getSurname()==null){
            user.setSurname(msg);
            sendText(user.getUserTgId(),"Пожалуйста, укажите свой номер телефона:");
            return;
        }
        if(user.getPhoneNumber()==null){
            user.setPhoneNumber(msg);
            sendText(user.getUserTgId(),"Пожалуйста, укажите свой e-mail:");
            return;
        }
        if(user.geteMail()==null){
            user.seteMail(msg);
        }
        user.status=null;
        user.setEditdate(ts);
        userService.registerUser(user);
        sendText(user.getUserTgId(),"Регистрация завершена успешно");
    }
}
