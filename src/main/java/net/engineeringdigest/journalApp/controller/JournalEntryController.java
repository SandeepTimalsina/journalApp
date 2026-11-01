package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    public Map<Long, JournalEntry> journalEntries = new HashMap<>();


    @GetMapping
    public List<JournalEntry> getAll() {
        return  new ArrayList<JournalEntry>(journalEntries.values());
    }
    @PostMapping
    public void createEntry(@RequestBody JournalEntry journalEntry){

        journalEntries.put(journalEntry.getId(), journalEntry);
    }
    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }
    @DeleteMapping("id/{myId}")
    public void deleteJournalEntryById(@PathVariable Long myId){
        journalEntries.remove(myId);
    }
    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
        return journalEntries.put(myId, journalEntry);
    }
}
