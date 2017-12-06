package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Confirmation extends Activity implements View.OnClickListener {

    private Button buttonConfirm, buttonCancelConfirm;
    private EditText editTextConfirmationNumber; //number of tickets
    private TextView textViewConfirmation; // I want to buy or sell
    private TextView textViewGame;  // Game:
    private TextView textViewPrice; //Price

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonCancelConfirm = (Button) findViewById(R.id.buttonCancelConfirm);

        buttonConfirm.setOnClickListener(this);
        buttonCancelConfirm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == buttonConfirm) {
            Intent intentConfirm = new Intent(this, ActivityBuySellInfo.class);
            this.startActivity(intentConfirm);
        } else if (v == buttonCancelConfirm){
            Intent intentCancel= new Intent(this, ActivityBuySellInfo.class);
            this.startActivity(intentCancel);
        }
    }
}
