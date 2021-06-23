package ru.geekbrains.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Notes implements Parcelable {

    @StringRes
    private final int noteName;

    @StringRes
    private final int noteContent;

    @StringRes
    private final int creationDate;

    public Notes(int noteName, int noteContent, int creationDate) {
        this.noteName = noteName;
        this.noteContent = noteContent;
        this.creationDate = creationDate;
    }

    protected Notes(Parcel in) {
        noteName = in.readInt();
        noteContent = in.readInt();
        creationDate = in.readInt();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public int getNoteName() {
        return noteName;
    }

    public int getNoteContent() {
        return noteContent;
    }

    public int getCreationDate() {
        return creationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(noteName);
        parcel.writeInt(noteContent);
        parcel.writeInt(creationDate);
    }
}
