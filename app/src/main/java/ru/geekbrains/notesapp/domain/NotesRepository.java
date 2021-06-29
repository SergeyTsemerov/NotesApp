package ru.geekbrains.notesapp.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.geekbrains.notesapp.R;

public class NotesRepository {

    private final ArrayList<Notes> result = new ArrayList<>();

    public NotesRepository() {

        result.add(new Notes("id1", "Заметка №1", R.string.first_note_content, R.string.first_note_creation_date, "https://cdn.pixabay.com/photo/2020/07/03/10/28/waterfall-5365926__340.jpg"));
        result.add(new Notes("id2", "Заметка №2", R.string.second_note_content, R.string.second_note_creation_date, "https://cdn.pixabay.com/photo/2016/04/27/20/13/wind-power-1357419__340.jpg"));
        result.add(new Notes("id3", "Заметка №3", R.string.third_note_content, R.string.third_note_creation_date, "https://cdn.pixabay.com/photo/2021/05/14/15/17/mountain-6253669__340.jpg"));
        result.add(new Notes("id4", "Заметка №4", R.string.fourth_note_content, R.string.fourth_note_creation_date, "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989__340.jpg"));
        result.add(new Notes("id5", "Заметка №5", R.string.fifth_note_content, R.string.fifth_note_creation_date, "https://cdn.pixabay.com/photo/2016/09/19/07/01/lake-1679708__340.jpg"));
    }

    public List<Notes> getNotes() {
        return result;
    }

    public void clear() {
        result.clear();
    }

    public Notes add(String noteName, int noteContent, int creationDate, String imageUrl) {
        Notes note = new Notes(UUID.randomUUID().toString(), noteName, noteContent, creationDate, imageUrl);
        result.add(note);
        return note;
    }

    public void remove(Notes note) {
        result.remove(note);
    }

    public Notes update(@NonNull Notes notes, @Nullable String noteName) {

        for (int i = 0; i < result.size(); i++) {

            Notes item = result.get(i);

            if (item.getId().equals(notes.getId())) {

                String noteNameSet = item.getNoteName();

                if (noteName != null) {
                    noteNameSet = noteName;
                }

                Notes newNote = new Notes(notes.getId(), noteNameSet, notes.getNoteContent(), notes.getCreationDate(), notes.getUrl());

                result.remove(i);
                result.add(i, newNote);

                return newNote;
            }
        }

        return notes;
    }
}
