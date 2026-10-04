package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMongo {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
        Users users=userService.findByUsername(username);
        List<JournalEntry> all=users.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@PathVariable String username,@RequestBody JournalEntry myEntry) {
        try {
            Users users=userService.findByUsername(username);

            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> getEntryById(@PathVariable String myId) {
//        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myId);
//        if(journalEntry.isPresent()){
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<Void> deleteEntryById(@PathVariable String myId,@PathVariable String username) {
        if (journalEntryService.getEntryById(myId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        journalEntryService.deleteEntryById(myId,username);
        return ResponseEntity.noContent().build();
    }
//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<Void> deleteEntryById(@PathVariable String myId) {
//        if (journalEntryService.getEntryById(myId).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        journalEntryService.deleteEntryById(myId);
//        return ResponseEntity.noContent().build();
//    }

//    @PutMapping("id/{myId}")
//    public ResponseEntity<JournalEntry> updateEntryById(
//            @PathVariable String myId,
//            @RequestBody JournalEntry updatedEntry) {
//        return journalEntryService.getEntryById(myId)
//                .map(existingEntry -> {
//                    existingEntry.setTitle(updatedEntry.getTitle());
//                    existingEntry.setContent(updatedEntry.getContent());
//                    return ResponseEntity.ok(journalEntryService.saveEntry(existingEntry, username));
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<JournalEntry> updateEntryById(
            @PathVariable String myId,
            @PathVariable String username,
            @RequestBody JournalEntry updatedEntry) {
        return journalEntryService.getEntryById(myId)
                .map(existingEntry -> {
                    existingEntry.setTitle(updatedEntry.getTitle());
                    existingEntry.setContent(updatedEntry.getContent());
                    journalEntryService.saveEntry(existingEntry, username);
                    return ResponseEntity.ok(existingEntry);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
