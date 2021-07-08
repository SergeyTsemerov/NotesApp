package ru.geekbrains.notesapp.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public interface NotesRepositoryInterface {

    void getNotes(Callback<List<Notes>> callback);

    void clear();

    void add(String noteName, String noteContent, String imageUrl, Callback<Notes> callback);

    void remove(Notes notes, Callback<Object> callback);

    void edit(@NonNull Notes notes, @Nullable String noteName, Callback<Notes> callback);
}
