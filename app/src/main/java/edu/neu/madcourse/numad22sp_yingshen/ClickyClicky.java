package edu.neu.madcourse.numad22sp_yingshen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity implements View.OnClickListener{

    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);

        message = findViewById(R.id.pressed);

        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonD = findViewById(R.id.buttonD);
        Button buttonE = findViewById(R.id.buttonE);
        Button buttonF = findViewById(R.id.buttonF);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonA:
                message.setText("Pressed: A");
                break;
            case R.id.buttonB:
                message.setText("Pressed: B");
                break;
            case R.id.buttonC:
                message.setText("Pressed: C");
                break;
            case R.id.buttonD:
                message.setText("Pressed: D");
                break;
            case R.id.buttonE:
                message.setText("Pressed: E");
                break;
            case R.id.buttonF:
                message.setText("Pressed: F");
                break;
            default:
                message.setText("Pressed:-");
                break;
        }

    }

}