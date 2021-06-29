package ru.geekbrains.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Notes implements Parcelable {

    private final String id;

    private String noteName;

    @StringRes
    private final int noteContent;

    @StringRes
    private final int creationDate;

    private final String url;

    protected Notes(Parcel in) {
        id = in.readString();
        noteName = in.readString();
        noteContent = in.readInt();
        creationDate = in.readInt();
        url = in.readString();
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

    public String getId() {
        return id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
    public int getNoteContent() {
        return noteContent;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public String getUrl() {
        return url;
    }

    public Notes(String id, String noteName, int noteContent, int creationDate, String url) {
        this.id = id;
        this.noteName = noteName;
        this.noteContent = noteContent;
        this.creationDate = creationDate;
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(noteName);
        parcel.writeInt(noteContent);
        parcel.writeInt(creationDate);
        parcel.writeString(url);
    }
}
