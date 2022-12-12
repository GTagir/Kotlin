package com.example.notes.domain;

import java.util.UUID;

public class Note {

    private String id;
    private String title;
    private String message;

    public Note(String id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
