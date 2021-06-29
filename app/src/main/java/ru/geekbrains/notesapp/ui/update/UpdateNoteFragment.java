package ru.geekbrains.notesapp.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;

public class UpdateNoteFragment extends Fragment {

    private static final String TAG = "UpdateNoteFragment";

    private static final String ARG_NOTE = "ARG_NOTE";

    public static UpdateNoteFragment newInstance(Notes notes) {
        UpdateNoteFragment fragment = new UpdateNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        Notes notes = getArguments().getParcelable(ARG_NOTE);

        EditText noteName = view.findViewById(R.id.note_name);
        noteName.setText(notes.getNoteName());
    }
}
