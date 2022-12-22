package com.example.notes.domain;

import android.os.Looper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

public class InMemoryNotesRepository implements NotesRepository {
    private ArrayList<Note> data = new ArrayList<>();

    private Executor executor = Executors.newSingleThreadExecutor();

    private android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());

    public InMemoryNotesRepository() {
        data.add(new Note(UUID.randomUUID().toString(), "Title 1", "Message 1"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 2", "Message 2"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 3", "Message 3"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 4", "Message 4"));
        data.add(new Note(UUID.randomUUID().toString(), "Title 5", "Message 5"));

    }

    @Override
    public void getAll(Callback<List<Note>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
/*                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(data);
                    }
                });
            }
        });
    }

    @Override
    public void addNote(String title, String message, Callback<Note> callback) {

    }

    @Override
    public void removeNote(Note note, Callback<Void> callback) {
    }

    @Override
    public void upDateNote(Note note, String title, String message, Callback<Note> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {

                Note newNote = new Note(note.getId(), title, message);

                int index = data.indexOf(note);

                data.set(index, newNote);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(newNote);
                    }
                });
            }
        });

    }

}
