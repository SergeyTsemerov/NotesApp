package ru.geekbrains.notesapp.domain;

public interface Callback<T> {

    void onSuccess(T result);
}
