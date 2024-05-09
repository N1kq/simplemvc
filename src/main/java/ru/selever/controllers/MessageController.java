package ru.selever.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.selever.models.Message;
import ru.selever.repository.MessageRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    public MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //Get all
    @GetMapping
    public List<Message> getMessages() {
        logger.debug("Пришел Get-запрос на все сообщения");
        logger.info("Пришел Get-запрос на все сообщения");
        return messageRepository.findAll();
    }

    //Get by id
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long messageId){
        logger.debug("Пришел Get-запрос сообщения по id");
        logger.info("Пришел Get-запрос сообщения по id");
        return messageRepository.findById(messageId).orElseThrow(RuntimeException::new);
    }
    //TODO: Разобраться с warning ResponseEntity
    //Create message
    @PostMapping
    public ResponseEntity createMessage(@RequestBody Message message) throws URISyntaxException{
        logger.debug("Пришел Post-запрос на создание сообщения");
        logger.info("Пришел Post-запрос на создание сообщения");
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.created(new URI("/messages/"+savedMessage.getMessageId())).body(savedMessage);
    }

    //Update message
    @PutMapping("/{id}")
    public ResponseEntity updateMessage(@PathVariable Long messageId, @RequestBody Message message){
        logger.debug("Пришел Put-запрос на изменениие сообщения");
        logger.info("Пришел Put-запрос на изменениие сообщения");
        Message currentMessage = messageRepository.findById(messageId).orElseThrow(RuntimeException::new);
        currentMessage.setMessage(message.getMessage());
        currentMessage.setEditdate(message.getEditdate());
        currentMessage.setRecdate(message.getRecdate());
        currentMessage.setUserId(message.getUserId());
        currentMessage = messageRepository.save(message);

        return ResponseEntity.ok(currentMessage);
    }

    //Delete message
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long messageId){
        logger.debug("Пришел Delete-запрос на удаление сообщения");
        logger.info("Пришел Delete-запрос на удаление сообщения");
        messageRepository.deleteById(messageId);
        return ResponseEntity.ok().build();
    }

}
