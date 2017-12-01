package com.example.glezma.group6project;
// dfdfdfd
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button buttonWelcomeScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonWelcomeScreen = (Button) findViewById(R.id.buttonWelcomeScreen);
        buttonWelcomeScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonWelcomeScreen) {
            Intent intentWelcome = new Intent(this, WelcomeScreen.class);
            this.startActivity(intentWelcome);
        }

    }
}
