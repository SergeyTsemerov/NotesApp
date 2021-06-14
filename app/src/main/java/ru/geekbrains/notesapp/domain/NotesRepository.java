package ru.geekbrains.notesapp.domain;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notesapp.R;

public class NotesRepository {

    public List<Notes> getNotes() {

        ArrayList<Notes> result = new ArrayList<>();

        result.add(new Notes(R.string.first_note, R.string.first_note_content, R.string.first_note_creation_date));
        result.add(new Notes(R.string.second_note, R.string.second_note_content, R.string.second_note_creation_date));
        result.add(new Notes(R.string.third_note, R.string.third_note_content, R.string.third_note_creation_date));
        result.add(new Notes(R.string.fourth_note, R.string.fourth_note_content, R.string.fourth_note_creation_date));
        result.add(new Notes(R.string.fifth_note, R.string.fifth_note_content, R.string.fifth_note_creation_date));

        return result;
    }
}
