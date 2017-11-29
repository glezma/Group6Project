package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends Activity implements View.OnClickListener {

    private TextView textViewWelcome;
    private Button buttonGame1;
    private Button buttonGame2;
    private Button buttonGame3;
    private Button buttonGame4;
    private Button buttonGame5;
    private Button buttonGame6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        buttonGame1 = (Button) findViewById(R.id.buttonGame1);
        buttonGame1.setOnClickListener(this);

        buttonGame2 = (Button) findViewById(R.id.buttonGame2);
        buttonGame2.setOnClickListener(this);

        buttonGame3 = (Button) findViewById(R.id.buttonGame3);
        buttonGame3.setOnClickListener(this);

        buttonGame4 = (Button) findViewById(R.id.buttonGame4);
        buttonGame4.setOnClickListener(this);

        buttonGame5 = (Button) findViewById(R.id.buttonGame5);
        buttonGame5.setOnClickListener(this);

        buttonGame6 = (Button) findViewById(R.id.buttonGame6);
        buttonGame6.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonGame1) {
            Intent intentGame1 = new Intent(this, Game1.class);
            this.startActivity(intentGame1);
        }
        else if (view.getId() == R.id.buttonGame2) {
            Intent intentGame2 = new Intent(this, Game2.class);
            this.startActivity(intentGame2);
        }
        else if (view.getId() == R.id.buttonGame3) {
            Intent intentGame3 = new Intent(this, Game1.class);
            this.startActivity(intentGame3);
        }
        else if (view.getId() == R.id.buttonGame4) {
            Intent intentGame4 = new Intent(this, Game2.class);
            this.startActivity(intentGame4);
        }
        else if (view.getId() == R.id.buttonGame5) {
            Intent intentGame5 = new Intent(this, Game1.class);
            this.startActivity(intentGame5);
        }
        else if (view.getId() == R.id.buttonGame6) {
            Intent intentGame6 = new Intent(this, Game2.class);
            this.startActivity(intentGame6);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuItemHome){
            Intent intentHome = new Intent(this, WelcomeScreen.class);
            this.startActivity(intentHome);
        } else if (item.getItemId() == R.id.menuItemMyTickets) {
            Intent intentMyTickets = new Intent(this, MyTickets.class);
            this.startActivity(intentMyTickets);
        } else if (item.getItemId() == R.id.menuItemLogOut) {
            Intent intentLogOut = new Intent(this, LogOut.class);
            this.startActivity(intentLogOut);
        }

        return super.onOptionsItemSelected(item);
    }
}
