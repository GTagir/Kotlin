package com.example.lesson6;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String message = getString(R.string.Message_exit);
        String button1String = getString(R.string.button_yes);
        String button2String = getString(R.string.button_no);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "Возможно вы правы",
                                    Toast.LENGTH_LONG).show();}
        });
         builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "Возможно вы правы", Toast.LENGTH_LONG)
                            .show();
                }
            });
        builder.setCancelable(true);

        return builder.create();
    }
}
