package ru.geekbrains.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.domain.Notes;
import ru.geekbrains.notesapp.ui.details.NotesDetailsActivity;
import ru.geekbrains.notesapp.ui.details.NotesDetailsFragment;
import ru.geekbrains.notesapp.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNoteClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);

                if (item.getItemId() == R.id.settings) {
                    Toast.makeText(MainActivity.this, "'Settings' button pressed!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (item.getItemId() == R.id.about) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.notes_list_fragment, new AboutButtonFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onNoteClicked(Notes note) {
        Toast.makeText(this, note.getCreationDate(), Toast.LENGTH_SHORT).show();

        boolean isLandscape = getResources().getBoolean(R.bool.isLandscape);

        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_details_fragment, NotesDetailsFragment.newInstance(note))
                    .commit();

        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_list_fragment, NotesDetailsFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        }
    }
}