package ru.geekbrains.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.ui.details.NotesDetailsActivity;
import ru.geekbrains.notesapp.ui.details.NotesDetailsFragment;
import ru.geekbrains.notesapp.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNoteClicked(Notes note) {
        Toast.makeText(this, note.getCreationDate(), Toast.LENGTH_SHORT).show();

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_details_fragment, NotesDetailsFragment.newInstance(note))
                    .commit();

        } else {
            Intent intent = new Intent(this, NotesDetailsActivity.class);
            intent.putExtra(NotesDetailsActivity.ARG_NOTE, note);
            startActivity(intent);
        }
    }
}