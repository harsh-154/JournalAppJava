package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        Users users=userService.findByUsername(username);
        journalEntry.setTime(LocalDateTime.now());
        JournalEntry saved=journalEntryRepository.save(journalEntry);
//      now suppose if an error occured before line 26 executing then the entry will not be saved in users
//      so we will handle this using Transaction (handled by PlatformTransactionManager(interface),MongoTransactionManager
//      (class which extends this PlatformTransactionManager)) and using this @Transactional and @EnableTransactionManagement
//      in JournalApplication file. And for adding Transaction mongodb have to user replication so we will do it using
//      MongoDb atlas
        users.getJournalEntries().add(saved);
        userService.saveEntry(users);

    }
    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getEntryById(String id) {

        return journalEntryRepository.findById(id);
    }

    public void deleteEntryById(String id, String username) {
        Users users=userService.findByUsername(username);
        users.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(users);
        journalEntryRepository.deleteById(id);
    }
}
