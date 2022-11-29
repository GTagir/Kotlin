package com.example.lesson6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_note extends Fragment {

    static final String ARG_INDEX = "index";

    static final String SELECTED_NOTE = "note";

    private Note note;


    private DatePicker mDatePicker;


    public Fragment_note(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_note, menu);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.toolbar2);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Bundle arguments = getArguments();

        Button buttonCalend = view.findViewById(R.id.btn2);
        buttonCalend.setOnClickListener(view1 -> {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.cont_note, new CalendarFragment())
                    .addToBackStack(toString())
                    .commit();
        });


        Button buttonBack = view.findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        if (arguments != null){
            //int index = arguments.getInt(ARG_INDEX);
            note = arguments.getParcelable(SELECTED_NOTE);

            TextView tvTitle = view.findViewById(R.id.tvTitle);
           // tvTitle.setText(Note.getNotes()[index].getTitle());
            tvTitle.setText(note.getTitle());
            tvTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setTitle(charSequence.toString());
                    //    Note.getNotes()[index].setTitle(charSequence.toString());
                }
                @Override
                public void afterTextChanged(Editable editable) { }
            });

            TextView tvDescription = view.findViewById(R.id.tvDescription);
            tvDescription.setText(note.getDescription());
        }
    }


    public static Fragment_note newInstance(Note note){
        Fragment_note fragment = new Fragment_note();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

}