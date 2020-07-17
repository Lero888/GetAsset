package com.swe401.getasset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navView;
    DatabaseHelper assetDb;
    session_management session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetDb = new DatabaseHelper(this);
        session = new session_management(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String username = user.get(session_management.KEY_NAME);
        final String email = user.get(session_management.KEY_EMAIL);
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
        View headerView = navView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.Username);
        navUsername.setText(username);
        TextView navEmail = headerView.findViewById(R.id.Email);
        navEmail.setText(email);

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
            case R.id.logout:
                session.logoutUser();
                break;

        }

        // close nav after selection
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}