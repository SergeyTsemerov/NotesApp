package ru.geekbrains.notesapp.domain;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notesapp.R;

public class NotesRepository {

    public List<Notes> getNotes() {

        ArrayList<Notes> result = new ArrayList<>();

        result.add(new Notes("id1" ,R.string.first_note, R.string.first_note_content, R.string.first_note_creation_date, "https://cdn.pixabay.com/photo/2020/07/03/10/28/waterfall-5365926__340.jpg"));
        result.add(new Notes("id2" ,R.string.second_note, R.string.second_note_content, R.string.second_note_creation_date, "https://cdn.pixabay.com/photo/2016/04/27/20/13/wind-power-1357419__340.jpg"));
        result.add(new Notes("id3" ,R.string.third_note, R.string.third_note_content, R.string.third_note_creation_date, "https://cdn.pixabay.com/photo/2021/05/14/15/17/mountain-6253669__340.jpg"));
        result.add(new Notes("id4" ,R.string.fourth_note, R.string.fourth_note_content, R.string.fourth_note_creation_date, "https://cdn.pixabay.com/photo/2013/07/18/20/26/sea-164989__340.jpg"));
        result.add(new Notes("id5" ,R.string.fifth_note, R.string.fifth_note_content, R.string.fifth_note_creation_date, "https://cdn.pixabay.com/photo/2016/09/19/07/01/lake-1679708__340.jpg"));

        return result;
    }
}
