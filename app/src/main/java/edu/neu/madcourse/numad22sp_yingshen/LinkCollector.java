package edu.neu.madcourse.numad22sp_yingshen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LinkCollector extends AppCompatActivity {

    private TextView website;
    private TextView address;
    private Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);

        website = findViewById(R.id.website_name);
        address = findViewById(R.id.website_address);
        addItem = findViewById(R.id.addButton);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void  openDialog() {
        InputDialog newDialog = new InputDialog();
        newDialog.show(getSupportFragmentManager(), "");

    }
}