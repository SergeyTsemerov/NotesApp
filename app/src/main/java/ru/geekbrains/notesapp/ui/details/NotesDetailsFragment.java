package ru.geekbrains.notesapp.ui.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;

public class NotesDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NotesDetailsFragment newInstance(Notes notes) {
        NotesDetailsFragment fragment = new NotesDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView noteName = view.findViewById(R.id.note_name);
        EditText noteContent = view.findViewById(R.id.note_content);

        assert getArguments() != null;
        Notes notes = getArguments().getParcelable(ARG_NOTE);

        noteName.setText(notes.getNoteName());
        noteContent.setText(notes.getNoteContent());
    }
}