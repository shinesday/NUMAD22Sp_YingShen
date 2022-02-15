package edu.neu.madcourse.numad22sp_yingshen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LinkCollector extends AppCompatActivity implements InputDialog.InputDialogListener {

    TextView websiteName;
    TextView websiteAddress;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void  openDialog() {
        InputDialog newDialog = new InputDialog();
        newDialog.show(getSupportFragmentManager(), "input dialog");

    }

    @Override
    public void applyInput(String name, String address) {
        websiteName.setText(name);
        websiteAddress.setText(address);
    }

}

