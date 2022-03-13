package ru.geekbrains.notesapp.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.RouterHolder;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private MainRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        router = new MainRouter(getSupportFragmentManager());
    }

    @Override
    public MainRouter getMainRouter() {
        return router;
    }
}