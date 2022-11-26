package com.example.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.notes_container, new Fragment_notes())
                .commit();}
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