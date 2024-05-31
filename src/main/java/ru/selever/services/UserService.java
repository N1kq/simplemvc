package ru.selever.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.selever.models.User;
import ru.selever.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    Timestamp ts = new Timestamp(System.currentTimeMillis());
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private String characters = "abcdefghijklmnopqrstvwxyz1234567890";
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
        guest.setVerified(false);
        guest.setVerCode(null);
        guest.setStatus(null);
        try {
            userRepository.save(guest);
            logger.debug("Пользователь сохранён");
        }catch (Exception e){
            logger.info("Такой гость уже есть в бд. Гость не сохранён");
            return;
        }

    }

    public void saveUser(User user){
        userRepository.save(user);
    }
    public User getByTgId(Long TgId){
        return userRepository.findByUserTgId(TgId);
    }

    public User getByVerCode(String code){return userRepository.findByVerCode(code);}

    public List<User> getByRoleId(Long roleId){return userRepository.findByRole(roleId);}


    public void getVerCode(User user){
        Random rng = new Random();
        user.setVerCode(generateString(rng,characters,64));
        userRepository.save(user);
    }
    public void verifyUser(User user){
        user.setVerified(true);
        userRepository.save(user);
    }

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
