package ru.geekbrains.notesapp.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.geekbrains.notesapp.R;
import ru.geekbrains.notesapp.RouterHolder;

public class MainActivity extends AppCompatActivity implements RouterHolder {

    private MainRouter router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        router = new MainRouter(getSupportFragmentManager());

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawer(GravityCompat.START);

            if (item.getItemId() == R.id.settings) {
                Toast.makeText(MainActivity.this, "'Settings' button pressed!", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (item.getItemId() == R.id.about) {
                router.showAbout();
                return true;
            }
            if (item.getItemId() == R.id.main_screen) {
                router.showMainScreen();
                return true;
            }
            return false;
        });
    }

    @Override
    public MainRouter getMainRouter() {
        return router;
    }
}