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

import ru.selever.models.*;
import ru.selever.services.EmailService;
import ru.selever.services.MessageService;
import ru.selever.services.RoleService;
import ru.selever.services.UserService;

import java.sql.Timestamp;
import java.util.List;


@Component
public class TgBot extends TelegramLongPollingBot {
    @Value("${spring.service.telegramscheduler.botname}")
    private String botname;

    public TgBot(@Value("${spring.service.telegramscheduler.bottoken}") String botToken){
        super(botToken);
    }
    Timestamp ts = new Timestamp(System.currentTimeMillis());
    User user;
    Role role;
    String rolename;
    Message message;
    Long messageId;
    Logger logger = LoggerFactory.getLogger(TgBot.class);
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    RoleService roleService;
    @Override
    public String getBotUsername(){
        return botname;
    }

    @Override
    public void onUpdateReceived(Update update){
        var msg = update.getMessage();
        var id = msg.getFrom().getId();
        user = userService.getByTgId(update.getMessage().getFrom().getId());
        switch(msg.getText()){
            case "/start":
                userService.createUser(update);
                break;
            case "/register": //TODO: ПЕРЕДЕЛАТЬ ПО КРАСИВОМУ
                user.setStatus("REGISTER"); //TODO: поменять на string и добавить в бд
                break;
            case "/mailing":
                user.setStatus("MAILING");
                break;
            case "/verify":
                if(user.getVerified()!=true) {
                    user.setStatus("VERIFY");
                } else {
                    sendText(id,"Вы уже потверждены пользователем системы.");
                }
            case "/contacts":
                break;
        }

        switch (user.getStatus()) {
            case null:
                break;
            case "REGISTER":
                processRegister(user, msg.getText());
                break;
            case "MAILING":
                processMailing(update, user);
                break;
            case "VERIFY":
                processVerify(user);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getStatus());
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



    private void processRegister(User user, String msg){
        if(msg.equals("/register")){
            sendText(user.getUserTgId(),"Пожалуйста, укажите своё имя:");
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
        user.setStatus(null);
        user.setEditdate(ts);
        user.setRole(3L);
        userService.registerUser(user);
        sendText(user.getUserTgId(),"Регистрация завершена успешно. Пройдите верификацию с помощью командыы /verify.");
    }

    public void processMailing(Update update, User user){
        if(update.getMessage().getText().equals("/mailing")){
            sendText(update.getMessage().getFrom().getId(),"Пожалуйста, укажите пользователем с какой" +
                    " ролью вы хотите переслать сообщение:");
            return;
        }
        if(rolename == null) {
            rolename = update.getMessage().getText();
            sendText(update.getMessage().getFrom().getId(),"Пожалуйста, укажите ID сообщения для отправки:");
            return;
        }
        if(messageId == null){
            messageId = Long.parseLong(update.getMessage().getText(),10);
        }
        message = messageService.getByMessageId(messageId);
        message.status= MessageStatus.MAILING;
        role = roleService.getByName(rolename);
        List<User> userList = userService.getByRoleId(role.getRoleId());

        //РАССЫЛКА
        for(int i = 0; i < userList.size(); i++){
            sendText(userList.get(i).getUserTgId(),message.getMessage());
        }
        rolename = null;
        messageId = null;
        message = null;
        role = null;
        user.setStatus(null);

    }

    public void processVerify(User user){
       userService.getVerCode(user);
       emailService.sendVerificationEmail(user, "localhost:8080");
       user.setStatus(null);
    }
}
