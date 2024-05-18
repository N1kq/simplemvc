package ru.selever.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.selever.models.Message;
import ru.selever.models.User;
import ru.selever.repository.MessageRepository;
import ru.selever.repository.UserRepository;

import java.sql.Timestamp;

@Service
public class MessageService {
    Timestamp ts = new Timestamp(System.currentTimeMillis());

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository){this.messageRepository=messageRepository;
        this.userRepository = userRepository;
    }
    public void createMessage(Update update){
        Message m = new Message();
        User sender = userRepository.findByUserTgId(update.getMessage().getFrom().getId());
        m.setEditdate(ts);
        m.setRecdate(ts);
        m.setMessage(update.getMessage().getText());
        m.setSenderId(sender.getUserId());
        m.setSenderChatId(update.getMessage().getChatId());
        messageRepository.save(m);
        logger.info("Создана запись в таблице message_data");
    }

    public Message getByMessageId(Long messageId){
        return messageRepository.findByMessageId(messageId);
    }
}
