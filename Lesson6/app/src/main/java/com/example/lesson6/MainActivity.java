package com.example.lesson6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
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

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Title")
                                .setMessage("Вы уверенны, что хотите выйти?")
                                .setPositiveButton("Да!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(MainActivity.this, "exitDialog", Toast.LENGTH_SHORT).show();
                                        System.exit(0);
                                            }
                                })
                                .setNegativeButton("Нет!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .show();

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
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setMessage("Вы уверенны, что хотите выйти?")
                        .setCancelable(false)
                        .setPositiveButton("Да!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "exit", Toast.LENGTH_SHORT).show();
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Нет!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}