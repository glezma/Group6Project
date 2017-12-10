package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Confirmation extends Activity implements View.OnClickListener {

    private Button buttonConfirm;
    private EditText editTextQuantity, editTextPrice; //number of tickets
    private TextView textViewType, textViewGame; // I want to buy or sell
    private TextView buttonCancelConfirm;  // Game:


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextPrice = findViewById(R.id.editTextPrice);
        textViewGame = findViewById(R.id.textViewGame);
        textViewType = findViewById(R.id.textViewType);
        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonCancelConfirm = (Button) findViewById(R.id.buttonCancelConfirm);

        buttonConfirm.setOnClickListener(this);
        buttonCancelConfirm.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        String tiType = extras.getString("type");
        String tiGame = extras.getString("game");
        textViewGame.setText(tiGame);
        textViewType.setText(tiType);


    }

    @Override
    public void onClick(View v) {
        Bundle extras = getIntent().getExtras();
        String tiType = extras.getString("type");
        String tiGame = extras.getString("game");
        String date = extras.getString("date");
        String tiDateGame = date;
        if (v == buttonConfirm) {
            //Initializing Firebase database
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference tiRef = db.getReference("TradeIntents");

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userEmail = user.getEmail();


            Integer tiPrice = Integer.parseInt(editTextPrice.getText().toString());
            Integer tiQuantity = Integer.parseInt( editTextQuantity.getText().toString());
            String tiUser = userEmail;
            String tiStatus = "Pending";

            TradeIntent myTradeIntent = new TradeIntent(tiGame,tiDateGame,tiType, tiPrice, tiQuantity, tiUser,tiStatus);
            tiRef.push().setValue(myTradeIntent);
            Toast.makeText(this, "Intent of Transaction added", Toast.LENGTH_SHORT).show();

            Intent intentConfirm = new Intent(this, ActivityBuySellInfo.class);
            intentConfirm.putExtra("game",tiGame);
            intentConfirm.putExtra("date",date);

            this.startActivity(intentConfirm);
        } else if (v == buttonCancelConfirm){
            Intent intentCancel= new Intent(this, ActivityBuySellInfo.class);
            intentCancel.putExtra("game",tiGame);
            intentCancel.putExtra("date",date);
            this.startActivity(intentCancel);
        }
    }
}
