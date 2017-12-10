package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TransactionConfirmation extends Activity implements View.OnClickListener {

    private Button buttonConfirm1, buttonCancelConfirm1;
    private EditText editTextQuantity1, editTextPrice1; //number of tickets
    private TextView textViewGame1, textViewType1, textViewContactEmail1;  // Game:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_confirmation);

        editTextQuantity1 = findViewById(R.id.editTextQuantity1);
        editTextPrice1 = findViewById(R.id.editTextPrice1);
        textViewGame1 = findViewById(R.id.textViewGame1);
        textViewType1 = findViewById(R.id.textViewType1);
        buttonConfirm1 = (Button) findViewById(R.id.buttonConfirm1);
        textViewContactEmail1 = (TextView) findViewById(R.id.textViewContactEmail1) ;

        buttonConfirm1.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        String tiType = extras.getString("type");
        String tiGame = extras.getString("game");
        String tiuser = extras.getString("user");
        String tiPrice = extras.getString("price");
        String tiQuantity = extras.getString("quantity");
//        TradeIntent list_buy = extras.getParcelable("list_buy");
        textViewGame1.setText(tiGame);
        textViewType1.setText(tiType);
        textViewContactEmail1.setText(tiuser);
        textViewType1.setText(tiType);
        editTextPrice1.setText(tiPrice);
        editTextQuantity1.setText(tiQuantity);
    }

    @Override
    public void onClick(View v) {
        Intent intentGoBackToList = new Intent(this, ActivityBuySellInfo.class);
        Bundle extras = getIntent().getExtras();
        String tigame_date = extras.getString("game_date");
        String tiGame = extras.getString("game");
        intentGoBackToList.putExtra("game",tiGame);
        intentGoBackToList.putExtra("game_date",tigame_date);
        this.startActivity(intentGoBackToList);


    }
}
