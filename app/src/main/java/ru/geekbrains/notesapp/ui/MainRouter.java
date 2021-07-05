package ru.geekbrains.notesapp.ui;

import androidx.fragment.app.FragmentManager;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.ui.about.AboutButtonFragment;
import ru.geekbrains.notesapp.ui.details.NotesDetailsFragment;
import ru.geekbrains.notesapp.ui.list.NotesListFragment;
import ru.geekbrains.notesapp.ui.update.EditNoteFragment;

public class MainRouter {

    private final FragmentManager fragmentManager;

    public MainRouter(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showAbout() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list_fragment, new AboutButtonFragment())
                .addToBackStack(null)
                .commit();
    }

    public void showMainScreen() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list_fragment, new NotesListFragment())
                .commit();
    }

    public void showNoteDetail(Notes note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_details_fragment, NotesDetailsFragment.newInstance(note))
                .commit();
    }

    public void showDetail(Notes note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list_fragment, NotesDetailsFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

    public void showEditNote(Notes note) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.notes_list_fragment, EditNoteFragment.newInstance(note), EditNoteFragment.TAG)
                .addToBackStack(EditNoteFragment.TAG)
                .commit();
    }

    public void back() {
        fragmentManager.popBackStack();
    }
}
