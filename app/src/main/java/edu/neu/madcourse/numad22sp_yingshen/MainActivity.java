package edu.neu.madcourse.numad22sp_yingshen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Ying Shen\nshen.ying@northeastern.edu",
                        Toast.LENGTH_LONG).show();
            }
        });

        button2 = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClickyClicky();
            }
        });

    }
    public void openClickyClicky(){
        Intent intent = new Intent(this, ClickyClicky.class);
        startActivity(intent);

    }

}