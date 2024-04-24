package ru.selever.controller;


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
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    public MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //Get all
    @GetMapping
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    //Get by id
    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Long messageId){
        return messageRepository.findById(messageId).orElseThrow(RuntimeException::new);
    }

    //Create message
    @PostMapping
    public ResponseEntity createMessage(@RequestBody Message message) throws URISyntaxException{
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.created(new URI("/messages/"+savedMessage.getMessageId())).body(savedMessage);
    }

    //Update message
    @PutMapping("/{id}")
    public ResponseEntity updateMessage(@PathVariable Long messageId, @RequestBody Message message){
        Message currentMessage = messageRepository.findById(messageId).orElseThrow(RuntimeException::new);
        currentMessage.setMessage(message.getMessage());
        currentMessage.setEditdate(message.getEditdate());
        currentMessage.setRecdate(message.getRecdate());
        currentMessage.setSenderId(message.getSenderId());
        currentMessage = messageRepository.save(message);

        return ResponseEntity.ok(currentMessage);
    }

    //Delete message
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long messageId){
        messageRepository.deleteById(messageId);
        return ResponseEntity.ok().build();
    }
//    //POST
//    @PostMapping("/add")
//    public Message addMessage(@RequestBody Message message){
//        return messageRepository.save(message);
//    }
//
//    //GET ALL
//    @GetMapping("/showall")
//    public  Iterable<Message> showMessages(){
//        return messageRepository.findAll();
//    }
//
//    //GET BY ID
//    @GetMapping("/{messageId}")
//    public Optional<Message> messageById(@PathVariable("messageId") Long messageId){
//        return messageRepository.findById(messageId);
//    }
//    //UPDATE
//    @PutMapping("/update")
//    public Message updMessage(@RequestBody Message message){
//        return messageRepository.save(message);
//    }
//
//    //DELETE
//    @DeleteMapping("/{messageId}")
//    public void delMessage(@PathVariable ("messageId") Long messageId){
//        messageRepository.deleteById(messageId);
//    }
}
