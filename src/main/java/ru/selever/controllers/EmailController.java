package ru.selever.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.selever.models.User;
import ru.selever.services.UserService;

@RestController
public class EmailController {
    UserService userService;
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        User user = userService.getByVerCode(code);
        userService.verifyUser(user);
        return "Verification succeded";
    }
}
