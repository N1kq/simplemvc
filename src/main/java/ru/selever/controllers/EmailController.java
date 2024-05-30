package ru.selever.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.selever.models.User;
import ru.selever.services.UserService;

@RestController
public class EmailController {
    Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    UserService userService;
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        User user = userService.getByVerCode(code);
        userService.verifyUser(user);
        logger.info("Верификация прошла успешно");
        return "Verification succeded";
    }
}
