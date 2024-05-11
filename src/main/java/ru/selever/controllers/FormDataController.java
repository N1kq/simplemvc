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

@RestController
@RequestMapping("/data")
public class FormDataController {
    private static final Logger logger = LoggerFactory.getLogger(FormDataController.class);

    @Autowired
    private final DataRepository dataRepository;

    @Autowired
    public FormDataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    //POST data
    @PostMapping
    public ResponseEntity createData(@RequestBody Data data) throws URISyntaxException{
        logger.debug("Пришел Post-запрос на создание записи");
        Data savedData = dataRepository.save(data);
        return ResponseEntity.created(new URI("/data/"+ savedData.getMessageId())).body(savedData);
    }
}
