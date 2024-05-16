package ru.selever.services;

import jakarta.persistence.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.selever.models.User;
import ru.selever.repository.UserRepository;

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
        guest.setChatId(update.getMessage().getChatId().toString());
        guest.setName(null);
        guest.seteMail(null);
        guest.setPhoneNumber(null);
        guest.setSurname(null);
        guest.setRecdate(ts);
        guest.setEditdate(ts);
        try {
            userRepository.save(guest);
        }catch (Exception e){
            logger.info("Такой гость уже есть в бд. Гость не сохранён");
            return;
        }
        logger.debug("Пользователь сохранён");
    }
}
