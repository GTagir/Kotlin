package com.example.notes.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notes.R;
import com.example.notes.di.Dependencies;
import com.example.notes.domain.Note;

import java.util.List;

public class NotesListFragment extends Fragment {
    public NotesListFragment(){
        super(R.layout.fragment_notes_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout container = view.findViewById(R.id.container);

        List<Note> notes = Dependencies.NOTES_REPOSITORY.getAll();

        for (Note note: notes){
            View itemView = getLayoutInflater().inflate(R.layout.item_note, container, false);
            itemView.findViewById(R.id.card_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            TextView title = itemView.findViewById(R.id.title);
            TextView message = itemView.findViewById(R.id.message);

            title.setText(note.getTitle());
            message.setText(note.getMessage());

            container.addView(itemView);
        }
    }
}
