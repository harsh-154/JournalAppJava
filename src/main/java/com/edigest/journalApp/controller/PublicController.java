package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    private UserService userService;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "fine";
    }

    @PostMapping
    public void createUser(@RequestBody Users users){
        userService.saveNewUser(users);
    }

}
