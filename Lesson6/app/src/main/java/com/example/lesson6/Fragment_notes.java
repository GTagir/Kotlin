package com.example.lesson6;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_notes extends Fragment {

    static final String SELECTED_INDEX = "index";
    static final String SELECTED_NOTE = "none";
    private int selectedIndex = 0;

    private Note note;

    View dataContainer;

    public Fragment_notes() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //outState.putParcelable(SELECTED_NOTE, note);
        if (note == null){
            note = Note.getNotes().get(0);
        }
        outState.putParcelable(SELECTED_NOTE, note);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        if (savedInstanceState != null){
           // selectedIndex = savedInstanceState.getInt(SELECTED_INDEX, 0);
            note = savedInstanceState.getParcelable(SELECTED_NOTE);
        }
        dataContainer = view.findViewById(R.id.data_container);
        initNotes(view.findViewById(R.id.data_container));

        if (isLandscape()){
            showLandNoteDetails(note);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public void initNotes(){
        initNotes(dataContainer);
    }

    private void initNotes(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        for (int i = 0; i < Note.getNotes().size(); i++) {

            TextView tv = new TextView(getContext());
            tv.setText(Note.getNotes().get(i).getTitle());
            tv.setTextSize(24);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                showNoteDetails(Note.getNotes().get(index));
            });
        }
    }

    private void showNoteDetails(Note note) {
        this.note = note;
        if (isLandscape()) {
            showLandNoteDetails(note);
        } else {
            showPortNoteDetails(note);
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_info){
            Toast.makeText(requireContext(), "action", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPortNoteDetails(Note note) {
        Fragment_note noteFragment = Fragment_note.newInstance(note);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.notes_container, noteFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showLandNoteDetails(Note note) {
        Fragment_note noteFragment = Fragment_note.newInstance(note);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.note_container, noteFragment); // замена  фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}