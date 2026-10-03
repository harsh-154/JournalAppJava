package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/User")
//in memory db
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> getAll() {
        return userService.getAllEntries();
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users myEntry) {
        try {
//            myEntry.setTime(LocalDateTime.now());
            userService.saveEntry(myEntry);
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
    @PutMapping("/{username}")
    public ResponseEntity<Users> updateEntryByUsername(
            @RequestBody Users updatedEntry, @PathVariable String userName) {
            Users userInDb=userService.findByUsername(userName);
            if(userInDb!=null){
                userInDb.setUsername(updatedEntry.getUsername());
                userInDb.setPassword(updatedEntry.getPassword());
                userService.saveEntry(userInDb);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
