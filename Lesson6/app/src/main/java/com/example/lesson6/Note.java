package com.example.lesson6;

import android.annotation.SuppressLint;

import java.time.LocalDateTime;
import java.util.Random;

public class Note {
    private static final Random random = new Random();
    private static Note[] notes;

    private String title;
    private String description;
    private LocalDateTime creationDate;

    public void setTitle(String title){this.title = title;}
    public void setDescription(String description){this.description = description;}
    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
    public static Note[] getNotes(){return notes;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public LocalDateTime getCreationDate(){return creationDate;}

    static {
        notes = new Note[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.getNote(i);
        }
    }

    public Note(String title, String description, LocalDateTime creationDate) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
    }

    @SuppressLint("Defaultlocale")
    public static Note getNote(int index) {
        String title = String.format("Заметка %d", index);
        String description = String.format("Описание заметки %d", index);
        LocalDateTime creationDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            creationDate = LocalDateTime.now().plusDays(random.nextInt(5));
        }
        return new Note(title,description,creationDate);
    }

}
/*
    LocalDateTime creationDate = LocalDateTime.now().plusDays(random.nextInt(5));*/
