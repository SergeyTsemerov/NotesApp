package ru.geekbrains.notesapp.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public interface OnNoteClicked {
        void onNoteClicked(Notes note);
    }

    public interface OnNoteLongClicked {
        void onNoteLongClicked(Notes note, int index);
    }

    private final Fragment fragment;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    private OnNoteClicked onNoteClicked;

    public OnNoteLongClicked getOnNoteLongClicked() {
        return onNoteLongClicked;
    }

    public void setOnNoteLongClicked(OnNoteLongClicked onNoteLongClicked) {
        this.onNoteLongClicked = onNoteLongClicked;
    }

    private OnNoteLongClicked onNoteLongClicked;

    public OnNoteClicked getOnNoteClicked() {
        return onNoteClicked;
    }

    public void setOnNoteClicked(OnNoteClicked onNoteClicked) {
        this.onNoteClicked = onNoteClicked;
    }

    private final ArrayList<Notes> notes = new ArrayList<>();

    public void setData(List<Notes> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }

    public int add(Notes addedNote) {
        notes.add(addedNote);
        return notes.size() - 1;
    }

    public void remove(Notes longClickedNote) {
        notes.remove(longClickedNote);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {

        Notes note = notes.get(position);

        holder.noteName.setText(note.getNoteName());

        Glide.with(holder.image)
                .load(note.getUrl())
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteName;
        ImageView image;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(view -> {
                if (getOnNoteClicked() != null) {
                    getOnNoteClicked().onNoteClicked(notes.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(view -> {
                itemView.showContextMenu();

                if (getOnNoteLongClicked() != null) {
                    int index = getAdapterPosition();
                    getOnNoteLongClicked().onNoteLongClicked(notes.get(index), index);
                }
                return true;
            });

            noteName = itemView.findViewById(R.id.note_name);
            image = itemView.findViewById(R.id.image);
        }
    }
}
