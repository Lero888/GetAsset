package com.swe401.getasset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navView;
    DatabaseHelper assetDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetDb = new DatabaseHelper(this);

        // nav
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // detect selection of the user
        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            // set default first view
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new fragment_status()).commit();
            navView.setCheckedItem(R.id.nav_status);
        }
    }

    // if back is pressed
    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_item_reserve:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_item_reservation()).commit();
                navView.setCheckedItem(R.id.nav_item_reserve);

                break;
            case R.id.nav_status:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_status()).commit();
                navView.setCheckedItem(R.id.nav_status);
                break;
            case R.id.nav_room_reserve:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new fragment_classroom_reservation()).commit();
                navView.setCheckedItem(R.id.nav_room_reserve);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share to WhatsApp", Toast.LENGTH_SHORT).show();
                break;

        }

        // close nav after selection
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}