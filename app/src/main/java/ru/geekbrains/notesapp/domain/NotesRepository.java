package ru.geekbrains.notesapp.domain;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository implements NotesRepositoryInterface {

    public static final NotesRepositoryInterface INSTANCE = new NotesRepository();

    private final ArrayList<Notes> result = new ArrayList<>();

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final Handler handler = new Handler(Looper.getMainLooper());

    public NotesRepository() {

        result.add(new Notes("id1", "Заметка №1", "Текст заметки №1", "https://cdn.pixabay.com/photo/2020/07/03/10/28/waterfall-5365926__340.jpg"));
        result.add(new Notes("id2", "Заметка №2", "Текст заметки №2", "https://cdn.pixabay.com/photo/2016/04/27/20/13/wind-power-1357419__340.jpg"));
        result.add(new Notes("id3", "Заметка №3", "Текст заметки №3", "https://cdn.pixabay.com/photo/2021/05/14/15/17/mountain-6253669__340.jpg"));
        result.add(new Notes("id4", "Заметка №4", "Текст заметки №4", "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989__340.jpg"));
        result.add(new Notes("id5", "Заметка №5", "Текст заметки №5", "https://cdn.pixabay.com/photo/2016/09/19/07/01/lake-1679708__340.jpg"));
    }

    public void getNotes(Callback<List<Notes>> callback) {
        executor.execute(() -> {
            try {
                Thread.sleep(1500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(() -> callback.onSuccess(result));
        });
    }

    public void clear() {
        result.clear();
    }

    public void add(String noteName, String noteContent, String imageUrl, Callback<Notes> callback) {
        Notes note = new Notes(UUID.randomUUID().toString(), noteName, noteContent, imageUrl);
        result.add(note);
        callback.onSuccess(note);
    }

    public void remove(Notes note, Callback<Object> callback) {
        result.remove(note);
        callback.onSuccess(note);
    }

    @Override
    public void edit(@NonNull Notes notes, @Nullable String noteName, Callback<Notes> callback) {
        for (int i = 0; i < result.size(); i++) {
            Notes item = result.get(i);
            if (item.getId().equals(notes.getId())) {
                String noteNameSet = item.getNoteName();
                if (noteName != null) {
                    noteNameSet = noteName;
                }
                Notes newNote = new Notes(notes.getId(), noteNameSet, notes.getNoteContent(), notes.getUrl());
                result.remove(i);
                result.add(i, newNote);
                callback.onSuccess(newNote);
            }
        }
        callback.onSuccess(notes);
    }
}
