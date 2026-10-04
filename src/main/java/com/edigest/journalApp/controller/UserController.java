package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.UserRepository;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
//in memory db
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users myEntry) {
        try {
//            myEntry.setTime(LocalDateTime.now());
            userService.saveNewUser(myEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("id/{myId}")
//    public ResponseEntity<Users> getEntryById(@PathVariable String myId) {
//        Optional<Users> users= userService.getEntryById(myId);
//        if(users.isPresent()){
//            return new ResponseEntity<>(users.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<Void> deleteEntryById(@PathVariable String myId) {
//        if (userService.getEntryById(myId).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        userService.deleteEntryById(myId);
//        return ResponseEntity.noContent().build();
//    }





//    @PutMapping
//    public ResponseEntity<Users> updateEntryByUsername(
//            @RequestBody Users updatedEntry) {
//            Users userInDb=userService.findByUsername(updatedEntry.getUsername());
//            if(userInDb!=null){
//                userInDb.setUsername(updatedEntry.getUsername());
//                userInDb.setPassword(updatedEntry.getPassword());
//                userService.saveEntry(userInDb);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Users> updateEntryByUsername(
            @RequestBody Users updatedEntry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
            Users userInDb=userService.findByUsername(name);
            if(userInDb!=null){
                userInDb.setUsername(updatedEntry.getUsername());
                userInDb.setPassword(updatedEntry.getPassword());
                userService.saveEntry(userInDb);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
