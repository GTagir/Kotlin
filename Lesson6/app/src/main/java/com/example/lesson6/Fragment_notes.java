package com.example.lesson6;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_notes extends Fragment {

    static final String SELECTED_INDEX = "index";
    int selectedIndex = 0;

    public Fragment_notes() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SELECTED_INDEX, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null){
            selectedIndex = savedInstanceState.getInt(SELECTED_INDEX, 0);
        }

        initNotes(view.findViewById(R.id.data_container));

        if (isLandscape()){
            showLandNoteDetails(selectedIndex);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }


    private void initNotes(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < Note.getNotes().length; i++) {

            TextView tv = new TextView(getContext());
            tv.setText(Note.getNotes()[i].getTitle());
            tv.setTextSize(24);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                showNoteDetails(index);
            });
        }
    }

    private void showNoteDetails(int index){
        selectedIndex = index;
        if (isLandscape()){
            showLandNoteDetails(index);
        } else {
            showPartNoteDetails(index);
        }
    }

    private void showPartNoteDetails(int index) {
        Fragment_note fragmentNote = Fragment_note.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.notes_container, fragmentNote);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showLandNoteDetails(int index) {
        Fragment_note fragmentNote = Fragment_note.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.note_container, fragmentNote);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}