package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping()
    public List<JournalEntry> findAll() {
        return journalEntryService.getAll();
    }
    @GetMapping("/id/{myId}")
    public JournalEntry findById(@PathVariable ObjectId myId) {
        return journalEntryService.getById(myId).orElse(null);
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());

        journalEntryService.saveEntry(journalEntry);
        return journalEntry;

    }
    @PutMapping("/id/{myId}")
    public JournalEntry updateEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry journalEntry) {
        JournalEntry old = journalEntryService.getById(myId).orElse(null);
        if (old != null) {
            old.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()
                    ? journalEntry.getTitle()
                    : old.getTitle());

            old.setContent(journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()
                    ? journalEntry.getContent()
                    : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }


    @DeleteMapping("/id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }
}
