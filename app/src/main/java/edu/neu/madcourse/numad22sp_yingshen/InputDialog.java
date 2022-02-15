package edu.neu.madcourse.numad22sp_yingshen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class InputDialog extends AppCompatDialogFragment {

    private EditText editWebsiteName;
    private EditText editWebsiteAddress;
    private InputDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        builder.setView(view)
                .setTitle("Add a link")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editWebsiteName.getText().toString();
                        String address = editWebsiteAddress.getText().toString();
                        listener.applyInput(name, address);

                    }
                });
        editWebsiteName = view.findViewById(R.id.edit_website_name);
        editWebsiteAddress = view.findViewById(R.id.edit_website_address);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (InputDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement InputDialogListener");
        }

    }

    public interface InputDialogListener {
        void applyInput(String name, String address);
    }

}
