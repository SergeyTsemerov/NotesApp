package ru.geekbrains.notesapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

public class Notes implements Parcelable {

    private final String id;

    private String noteName;

    private final String noteContent;

    private final String url;

    protected Notes(Parcel in) {
        id = in.readString();
        noteName = in.readString();
        noteContent = in.readString();
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

    public String getNoteContent() {
        return noteContent;
    }

    public String getUrl() {
        return url;
    }

    public Notes(String id, String noteName, String noteContent, String url) {
        this.id = id;
        this.noteName = noteName;
        this.noteContent = noteContent;
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
        parcel.writeString(noteContent);
        parcel.writeString(url);
    }
}
