package ru.geekbrains.notesapp.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.domain.NotesRepository;

public class NotesListFragment extends Fragment {

    public interface OnNoteClicked {
        void onNoteClicked(Notes note);
    }

    private NotesRepository notesRepository;

    private OnNoteClicked onNoteClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNoteClicked) {
            onNoteClicked = (OnNoteClicked) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        onNoteClicked = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = new NotesRepository();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout notesList = view.findViewById(R.id.notes_list_container);

        List<Notes> notes = notesRepository.getNotes();

        for (Notes note : notes) {

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, notesList, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNoteClicked != null) {
                        onNoteClicked.onNoteClicked(note);
                    }
                }
            });

            TextView noteName = itemView.findViewById(R.id.note_name);
            noteName.setText(note.getNoteName());

            notesList.addView(itemView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
