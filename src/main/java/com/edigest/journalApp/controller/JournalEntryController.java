package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal1")
//in memory db
public class JournalEntryController {
    private final Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());

    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;

    }
    @GetMapping("id/{myId}")
    public JournalEntry getEntryById(@PathVariable String myId){
        return journalEntries.get(myId);

    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable String myId){
        return journalEntries.remove(myId);

    }

    @PutMapping("id/{myId}")
    public boolean updateEntryById(@PathVariable String myId,@RequestBody JournalEntry updatedEntry){
        journalEntries.put(myId,updatedEntry);
        return true;
    }


}
