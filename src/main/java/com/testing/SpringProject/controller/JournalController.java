package com.testing.SpringProject.controller;

import com.testing.SpringProject.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("journal")
public class JournalController {

    public Map<Long , JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){

        return  new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){

        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
}

