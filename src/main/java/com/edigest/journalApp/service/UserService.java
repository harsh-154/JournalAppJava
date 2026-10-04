package com.edigest.journalApp.service;

//import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
//import com.edigest.journalApp.repository.JournalEntryRepository;
import com.edigest.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public Users saveEntry(Users users) {
        return userRepository.save(users);
    }
    public Users saveNewUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Arrays.asList("USER"));
        return userRepository.save(users);
    }

    public List<Users> getAllEntries() {
        return userRepository.findAll();
    }

    public Optional<Users> getEntryById(String id) {
        return userRepository.findById(id);
    }
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public void deleteEntryById(String id) {
//        userRepository.deleteById(id);
//    }
}
