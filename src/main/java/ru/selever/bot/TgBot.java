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
                userService.saveUser(user);
                break;
            case "/mailing":
                user.setStatus("MAILING");
                userService.saveUser(user);
                break;
            case "/verify":
                if(!user.getVerified()) {
                    user.setStatus("VERIFY");
                    userService.saveUser(user);
                } else {
                    sendText(id,"Вы уже потверждены пользователем системы.");
                }
            case "/contacts":
                sendText(id,"С нами можно связаться ...");
                break;
            case "/role_create":
                user.setStatus("ROLE_CREATE");
                userService.saveUser(user);
                break;
            case "/role_delete":
                user.setStatus("ROLE_DELETE");
                userService.saveUser(user);
                break;
            case "/role_update":
                user.setStatus("ROLE_UPDATE");
                userService.saveUser(user);
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
            case "ROLE_CREATE":
                processRoleCreate(msg.getText());
            case "ROLE_DELETE":
                processRoleDelete(msg.getText());
            case "ROLE_UPDATE":
                processRoleUpdate(msg.getText());
            case "ROLE_GIVE":
                processRoleGive(user, msg.getText());
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
        userService.saveUser(user);
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
        for (User value : userList) {
            sendText(value.getUserTgId(), message.getMessage());
        }
        rolename = null;
        messageId = null;
        message = null;
        role = null;
        user.setStatus(null);
        userService.saveUser(user);
    }

    public void processVerify(User user){
       userService.getVerCode(user);
       emailService.sendVerificationEmail(user, "localhost:8080");
       user.setStatus(null);
       userService.saveUser(user);
    }

//Методы для работы с ролями
    private void processRoleCreate(String msg){
        Role role1 = null;
        if(msg.equals("/role_create")){
            sendText(user.getUserTgId(),"Пожалуйста, укажите название роли:");
            return;
        }
        if(role1.getName()==null){
            role1.setName(msg);
            sendText(user.getUserTgId(),"Пожалуйста, укажите описание роли:");
            return;
        }
        if(role1.getDesc()==null){
            role1.setDesc(msg);
        }
        user.setStatus(null);
        userService.saveUser(user);
        roleService.saveRole(role1);
        sendText(user.getUserTgId(),"Создана новая роль "+ role1.getName());
    }
    private void processRoleUpdate(String msg){
        if(msg.equals("/role_update")){
            sendText(user.getUserTgId(),"Пожалуйста, укажите роль, которую вы хотите изменить:");
            return;
        }
        Role role = roleService.getByName(msg);
        if(role == null){
            sendText(user.getUserTgId(),"Такой роли не существует. Воспользуйтесь командой /role_create для создания новой роли");
            user.setStatus(null);
            userService.saveUser(user);
            return;
        }
        role.setName(null);
        role.setDesc(null);
        if(role.getName()==null){
            role.setName(msg);
            sendText(user.getUserTgId(),"Пожалуйста, укажите описание роли:");
            return;
        }
        if(role.getDesc()==null){
            role.setDesc(msg);
        }
        user.setStatus(null);
        userService.saveUser(user);
        roleService.saveRole(role);
        sendText(user.getUserTgId(),"Обновлена роль "+ role.getName());
    }
    private void processRoleDelete(String msg){
        if(msg.equals("/role_delete")){
            sendText(user.getUserTgId(),"Пожалуйста, укажите название роли, которую хотите удалить:");
            return;
        }
        if (msg.contains("Админ")){
            sendText(user.getUserTgId(), "Нельзя удалить роль.");
            user.setStatus(null);
            userService.saveUser(user);
            return;
        }
        if(roleService.getByName(msg)!=null){
            roleService.deleteRole(roleService.getByName(msg));
            sendText(user.getUserTgId(),"Роль была удалена.");
        }else {
            sendText(user.getUserTgId(),"Роль не найдена.");
        }
        user.setStatus(null);
        userService.saveUser(user);
    }
    private void processRoleGive(User user, String text) {

    }
}
