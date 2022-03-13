package ru.geekbrains.notesapp.domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotesFirestoreRepository implements NotesRepositoryInterface {

    public static final NotesRepositoryInterface INSTANCE = new NotesFirestoreRepository();

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private final static String NOTES = "notes";
    private final static String IMAGE = "image";
    private final static String NOTE_CONTENT = "noteContent";
    private final static String NOTE_NAME = "noteName";

    @Override
    public void getNotes(Callback<List<Notes>> callback) {

        firebaseFirestore.collection(NOTES)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Notes> result = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot == null) {
                            return;
                        }
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            String noteName = (String) document.get(NOTE_NAME);
                            String noteContent = (String) document.get(NOTE_CONTENT);
                            String image = (String) document.get(IMAGE);
                            result.add(new Notes(document.getId(), noteName, noteContent, image));
                        }
                        callback.onSuccess(result);
                    } else
                        task.getException();
                });

    }

    @Override
    public void add(String noteName, String noteContent, String imageUrl, Callback<Notes> callback) {
        HashMap<String, Object> data = new HashMap<>();

        data.put(NOTE_NAME, noteName);
        data.put(NOTE_CONTENT, noteContent);
        data.put(IMAGE, imageUrl);

        firebaseFirestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Notes notes = new Notes(task.getResult().getId(), noteName, noteContent, imageUrl);
                        callback.onSuccess(notes);
                    }
                });
    }

    @Override
    public void remove(Notes notes, Callback<Object> callback) {
        firebaseFirestore.collection(NOTES)
                .document(notes.getId())
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(notes);
                    }
                });
    }

    @Override
    public void edit(@NonNull Notes notes, @Nullable String noteName, Callback<Notes> callback) {
        firebaseFirestore.collection(NOTES)
                .document(notes.getId())
                .update(NOTE_NAME, noteName)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess(notes);
                    }
                });
    }
}
