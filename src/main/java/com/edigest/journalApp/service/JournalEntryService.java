package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
    public void saveEntry(JournalEntry journalEntry, String username) {
        Users users=userService.findByUsername(username);
        journalEntry.setTime(LocalDateTime.now());
        users.getJournalEntries().add(journalEntryRepository.save(journalEntry));
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
