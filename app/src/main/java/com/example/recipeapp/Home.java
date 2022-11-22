package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Home extends AppCompatActivity {
    private Button profile,feedback;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                   Intent navHome = new Intent(Home.this,Home.class);
                   Home.this.startActivity(navHome);
                }
                if (id == R.id.navigation_profile) {
                    Intent navProfile = new Intent(Home.this, ProfileActivity.class);
                    Home.this.startActivity(navProfile);
                    return true;
                }
                if (id == R.id.navigation_feedback) {
                    Intent navFeedback = new Intent(Home.this, FeedbackActivity.class);
                    Home.this.startActivity(navFeedback);
                    return true;
                }
                return false;
            }
        });

    }


}