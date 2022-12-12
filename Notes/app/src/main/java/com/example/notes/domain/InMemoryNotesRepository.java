package com.example.notes.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InMemoryNotesRepository implements NotesRepository{
    private ArrayList<Note> data = new ArrayList<>();


    public InMemoryNotesRepository(){
        data.add(new Note(UUID.randomUUID().toString(), "Title 1", "Message 1"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 2", "Message 2"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 3", "Message 3"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 4", "Message 4"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 5", "Message 5"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 6", "Message 6"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 7", "Message 7"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 8", "Message 8"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 9", "Message 9"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 10", "Message 10"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 11", "Message 11"));

    }

    @Override
    public List<Note> getAll() {
        return data;
    }
}
