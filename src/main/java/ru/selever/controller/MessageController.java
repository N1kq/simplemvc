package ru.selever.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.selever.models.Message;
import ru.selever.repository.MessageRepository;

import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    //POST
    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message){
        return messageRepository.save(message);
    }

    //GET ALL
    @GetMapping("/showall")
    public  Iterable<Message> showMessages(){
        return messageRepository.findAll();
    }

    //GET BY ID
    @GetMapping("/{messageId}")
    public Optional<Message> messageById(@PathVariable("messageId") int messageId){
        return messageRepository.findById(messageId);
    }
    //UPDATE
    @PutMapping("/update")
    public Message updMessage(@RequestBody Message message){
        return messageRepository.save(message);
    }

    //DELETE
    @DeleteMapping("/{messageId}")
    public void delMessage(@PathVariable ("messageId") int messageId){
        messageRepository.deleteById(messageId);
    }
}
