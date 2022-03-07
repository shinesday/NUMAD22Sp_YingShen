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
    Button linkCollector;
    Button buttonLocator;
    Button buttonService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutMe();
            }
            /**@Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Ying Shen\nshen.ying@northeastern.edu",
                        Toast.LENGTH_LONG).show();
            }*/
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openClickyClicky();
            }
        });

        linkCollector = findViewById(R.id.linkCollector);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLinkCollector();
            }
        });

        buttonLocator = findViewById(R.id.btn_locator);
        buttonLocator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserLocator();
            }
        });

        buttonService = findViewById(R.id.btn_service);
        buttonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAtYourService();
            }
        });

    }
    public void openClickyClicky(){
        Intent intent = new Intent(this, ClickyClicky.class);
        startActivity(intent);

    }

    public void openAboutMe(){
        Intent intentAboutMe = new Intent(this, AboutMe.class);
        startActivity(intentAboutMe);
    }

    public void openLinkCollector() {
        Intent intentLinkCollector = new Intent(this, LinkCollector.class);
        startActivity(intentLinkCollector);
    }

    public void openUserLocator(){
        Intent intentLocator = new Intent(this, UserLocator.class);
        startActivity(intentLocator);

    }

    public void openAtYourService(){
        Intent intentAtYourService = new Intent(this, AtYourService.class);
        startActivity(intentAtYourService);
    }

}