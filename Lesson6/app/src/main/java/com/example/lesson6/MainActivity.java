package com.example.lesson6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_info:
                        Toast.makeText(MainActivity.this, "info", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_share:
                        Toast.makeText(MainActivity.this, "share", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_exit:
                        Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;}
        });

        if (savedInstanceState == null) {getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_container, new Fragment_notes())
                .commit();}
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_info:
                Toast.makeText(this, "info", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_exit:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*    Button buttonCalendar = view.findViewById(R.id.btn2);
        buttonCalendar.setOnClickListener(view1 -> {
        String visibilityStr = R.layout.fragment_note.Fragment_note.findViewById(R.id.datePicker);

        R.layout.fragment_note.Fragment_note.DatePicker.visibility="visible";;

        if (visibilityStr.equals("0"))
            DatePicker.setVisibility(View.INVISIBLE);
        else
            DatePicker.setVisibility(View.VISIBLE);
    });*/
}