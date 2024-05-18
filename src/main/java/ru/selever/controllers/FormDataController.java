package ru.selever.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.selever.models.FormData;
import ru.selever.repository.FormDataRepository;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class FormDataController {
    private static final Logger logger = LoggerFactory.getLogger(FormDataController.class);

    @Autowired
    private final FormDataRepository formDataRepository;

    @Autowired
    public FormDataController(FormDataRepository formDataRepository) {
        this.formDataRepository = formDataRepository;
    }

    //POST data
    @PostMapping
    public ResponseEntity createData(@RequestBody FormData formData) throws URISyntaxException{
        logger.debug("Пришел Post-запрос на создание записи");
        FormData savedFormData = formDataRepository.save(formData);
        return ResponseEntity.created(new URI("/data/"+ savedFormData.getMessageId())).body(savedFormData);
    }
}
