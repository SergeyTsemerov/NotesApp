package ru.geekbrains.notesapp.ui.update;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.RouterHolder;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.domain.NotesFirestoreRepository;
import ru.geekbrains.notesapp.domain.NotesRepositoryInterface;
import ru.geekbrains.notesapp.ui.MainRouter;

public class EditNoteFragment extends Fragment {

    private final NotesRepositoryInterface notesRepository = NotesFirestoreRepository.INSTANCE;

    public static final String TAG = "EditNoteFragment";
    public static final String UPDATE_RESULT = "UPDATE_RESULT";
    public static final String ARG_NOTE = "ARG_NOTE";

    public static EditNoteFragment newInstance(Notes notes) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, notes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;
        Notes notes = getArguments().getParcelable(ARG_NOTE);

        Toolbar toolbar = view.findViewById(R.id.toolbar_edit);

        EditText noteName = view.findViewById(R.id.note_name);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.edit_check) {

                Notes result = notesRepository.edit(notes, noteName.getText().toString());

                if (requireActivity() instanceof RouterHolder) {
                    MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();

                    Bundle bundle = new Bundle();

                    bundle.putParcelable(ARG_NOTE, result);

                    getParentFragmentManager().setFragmentResult(UPDATE_RESULT, bundle);

                    router.back();
                }

                return true;
            }
            return false;
        });

        noteName.setText(notes.getNoteName());
    }
}
