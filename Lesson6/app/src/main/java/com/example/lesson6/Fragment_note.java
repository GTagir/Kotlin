package com.example.lesson6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class Fragment_note extends Fragment {

    static final String ARG_INDEX = "index";

    private DatePicker mDatePicker;


    public Fragment_note(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();

        Button buttonCalend = view.findViewById(R.id.btn2);
        buttonCalend.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_container, new CalendarFragment())
                    .commit();
        });


        Button buttonBack = view.findViewById(R.id.btnBack);
        buttonBack.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        if (arguments != null){
            int index = arguments.getInt(ARG_INDEX);

            TextView tvTitle = view.findViewById(R.id.tvTitle);
            tvTitle.setText(Note.getNotes()[index].getTitle());
            tvTitle.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Note.getNotes()[index].setTitle(charSequence.toString());
                }
                @Override
                public void afterTextChanged(Editable editable) { }
            });

            TextView tvDescription = view.findViewById(R.id.tvDescription);
            tvDescription.setText(Note.getNotes()[index].getDescription());
        }
    }


    public static Fragment_note newInstance(int index){
        Fragment_note fragment = new Fragment_note();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

}