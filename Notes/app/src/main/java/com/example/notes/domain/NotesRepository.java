package com.example.notes.domain;

import java.util.List;

public interface NotesRepository {
    List<Note> getAll();
}
