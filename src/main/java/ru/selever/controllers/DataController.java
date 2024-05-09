package ru.selever.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.selever.models.Data;
import ru.selever.repository.DataRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private final DataRepository dataRepository;

    @Autowired
    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    //POST data
    @PostMapping
    public ResponseEntity createData(@RequestBody Data data) throws URISyntaxException{
        logger.debug("Пришел Post-запрос на создание записи");
        Data savedData = dataRepository.save(data);
        return ResponseEntity.created(new URI("/data/"+ savedData.getMessageId())).body(savedData);
    }

    //GET ALL record
    @GetMapping
    public List<Data> getData(){
        logger.info("Пришел Get-запрос на все записи таблицы");
        return dataRepository.findAll();
    }

    //PUT Update Data
    @PutMapping("/{id}")
    public ResponseEntity updateData(@PathVariable Long messageId, @RequestBody Data data){
        logger.debug("Пришел Put-запрос на изменениие записи");
        Data currentData = dataRepository.findById(messageId).orElseThrow(RuntimeException::new);
        currentData.setMessage(data.getMessage());
        currentData.setEditdate(data.getEditdate());
        currentData.setName(data.getName());
        currentData.seteMail(data.geteMail());
        currentData.setPhoneNumber(data.getPhoneNumber());
        return ResponseEntity.ok(currentData);

    }
    //DELETE data
    @DeleteMapping("/{id}")
    public ResponseEntity deleteData(@PathVariable Long messageId){
        logger.debug("Пришел Delete-запрос на удаление записи");
        dataRepository.deleteById(messageId);
        return ResponseEntity.ok().build();
    }
}
