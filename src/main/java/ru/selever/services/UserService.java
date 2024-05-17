package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.selever.models.User;
import ru.selever.repository.UserRepository;

import java.lang.reflect.Method;
import java.sql.Timestamp;

@Service
public class UserService {

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(Update update){
        User guest = new User();

        guest.setUserTgId(update.getMessage().getFrom().getId());
        guest.setUserTgname(update.getMessage().getFrom().getUserName());
        if(userRepository.findById(1L).isPresent()){
            guest.setRole(2L);
        } else
        {
            guest.setRole(1L);
        }
        guest.setChatId(update.getMessage().getChatId());
        guest.setName(null);
        guest.seteMail(null);
        guest.setPhoneNumber(null);
        guest.setSurname(null);
        guest.setRecdate(ts);
        guest.setEditdate(ts);
        try {
            userRepository.save(guest);
            logger.debug("Пользователь сохранён");
        }catch (Exception e){
            logger.info("Такой гость уже есть в бд. Гость не сохранён");
            return;
        }

    }


    //Это самое плохое решение, которое можно придумать, но самое быстрое
    //sendText тут недоступен, конечно можно всё запихнуть в одну функцию, но это не сработало
    //TODO: Если будеть нормальная идея, то поменять

//    public void registerUserName(Update update){
//        String msg = update.getMessage().getText();
//        User user = userRepository.findByUserTgId(update.getMessage().getFrom().getId());
//        user.setEditdate(ts);
//        if(user.getName()==null){
//            user.setName(msg);
//            userRepository.save(user);
//        }
//    }
//    public void registerUserSurname(Update update){
//        String msg = update.getMessage().getText();
//        User user = userRepository.findByUserTgId(update.getMessage().getFrom().getId());
//        user.setEditdate(ts);
//        if(user.getSurname()==null){
//            user.setSurname(msg);
//            userRepository.save(user);
//        }
//    }
//    public void registerUserPhone(Update update){
//        String msg = update.getMessage().getText();
//        User user = userRepository.findByUserTgId(update.getMessage().getFrom().getId());
//        user.setEditdate(ts);
//        if(user.getPhoneNumber()==null){
//            user.setPhoneNumber(msg);
//            userRepository.save(user);
//        }
//    }
//    public void registerUserEmail(Update update){
//        String msg = update.getMessage().getText();
//        User user = userRepository.findByUserTgId(update.getMessage().getFrom().getId());
//        user.setEditdate(ts);
//        if(user.geteMail()==null){
//            user.seteMail(msg);
//            userRepository.save(user);
//        }
//    }


    public void registerUser(User user){
        userRepository.save(user);
    }
    public User getByTgId(Long TgId){
        return userRepository.findByUserTgId(TgId);
    }
}
