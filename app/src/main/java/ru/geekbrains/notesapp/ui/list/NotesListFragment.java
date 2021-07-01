package ru.geekbrains.notesapp.ui.list;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.RouterHolder;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.domain.NotesFirestoreRepository;
import ru.geekbrains.notesapp.domain.NotesRepositoryInterface;
import ru.geekbrains.notesapp.ui.MainRouter;
import ru.geekbrains.notesapp.ui.update.EditNoteFragment;

public class NotesListFragment extends Fragment {

    private NotesRepositoryInterface notesRepository;
    private NotesAdapter notesAdapter;

    private int longClickedIndex;
    private Notes longClickedNote;

    private boolean isLoading = false;

    ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notesRepository = NotesFirestoreRepository.INSTANCE;

        notesAdapter = new NotesAdapter(this);

        isLoading = true;

        notesRepository.getNotes(result -> {
            notesAdapter.setData(result);
            notesAdapter.notifyDataSetChanged();

            isLoading = false;

            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        });

        notesAdapter.setOnNoteClicked(note -> {

            boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

            if (requireActivity() instanceof RouterHolder) {
                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                if (isLandscape) {
                    router.showNoteDetail(note);
                } else {
                    router.showDetail(note);
                }
            }
        });

        notesAdapter.setOnNoteLongClicked((note, index) -> {
            longClickedIndex = index;
            longClickedNote = note;
        });

        getParentFragmentManager().setFragmentResultListener(EditNoteFragment.UPDATE_RESULT, this, (requestKey, result) -> {
            if (result.containsKey(EditNoteFragment.ARG_NOTE)) {
                Notes note = result.getParcelable(EditNoteFragment.ARG_NOTE);

                notesAdapter.edit(note);

                notesAdapter.notifyItemChanged(longClickedIndex);
            }
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

        progressBar = view.findViewById(R.id.progress_bar);

        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        }

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.add_btn) {

                notesRepository.add("Новая заметка", "Текст новой заметки", "https://cdn.pixabay.com/photo/2021/05/14/15/17/mountain-6253669__340.jpg", result -> {

                    int index = notesAdapter.add(result);

                    notesAdapter.notifyItemInserted(index);

                    notesList.scrollToPosition(index);
                });

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

            if (requireActivity() instanceof RouterHolder) {
                MainRouter router = ((RouterHolder) requireActivity()).getMainRouter();
                router.showEditNote(longClickedNote);
            }

            return true;
        }
        if (item.getItemId() == R.id.delete_btn) {

            notesRepository.remove(longClickedNote, result -> {

                notesAdapter.remove(longClickedNote);

                notesAdapter.notifyItemRemoved(longClickedIndex);
            });

            return true;
        }
        return super.onContextItemSelected(item);
    }
}
