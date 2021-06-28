package ru.geekbrains.notesapp.ui.list;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.domain.NotesRepository;

public class NotesListFragment extends Fragment {

    private NotesRepository notesRepository;
    private NotesAdapter notesAdapter;

    private int longClickedIndex;
    private Notes longClickedNote;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = new NotesRepository();

        notesAdapter = new NotesAdapter(this);

        notesAdapter.setOnNoteClicked(note -> {
            if (requireActivity() instanceof NotesAdapter.OnNoteClicked) {
                NotesAdapter.OnNoteClicked noteClicked = (NotesAdapter.OnNoteClicked) requireActivity();
                noteClicked.onNoteClicked(note);
            }
        });

        notesAdapter.setOnNoteLongClicked((note, index) -> {
            longClickedIndex = index;
            longClickedNote = note;
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.right_menu_toolbar);
        RecyclerView notesList = view.findViewById(R.id.notes_list_container);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.add_btn) {

                Notes addedNote = notesRepository.add("Новая заметка", R.string.new_note_content, R.string.new_note_creation_date, "https://cdn.pixabay.com/photo/2021/05/14/15/17/mountain-6253669__340.jpg");

                int index = notesAdapter.add(addedNote);

                notesAdapter.notifyItemInserted(index);

                notesList.scrollToPosition(index);

                return true;
            }

            if (item.getItemId() == R.id.clear_btn) {
                notesRepository.clear();

                notesAdapter.setData(Collections.emptyList());

                notesAdapter.notifyDataSetChanged();

                return true;
            }
            return false;
        });

        notesList.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Notes> notes = notesRepository.getNotes();

        notesAdapter.setData(notes);

        notesList.setAdapter(notesAdapter);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        requireActivity().getMenuInflater().inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.update_btn) {


            return true;
        }
        if (item.getItemId() == R.id.delete_btn) {

            notesRepository.remove(longClickedNote);

            notesAdapter.remove(longClickedNote);

            notesAdapter.notifyItemRemoved(longClickedIndex);

            return true;
        }
        return super.onContextItemSelected(item);
    }
}
