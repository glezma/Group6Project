package com.example.glezma.group6project;
//
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button buttonWelcomeScreen;
    private Button buttonBullSellInfo;
    private Button buttonAccoverview;
    private Button buttonBuyLayout;
    private Button buttonGameListing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonWelcomeScreen = (Button) findViewById(R.id.buttonWelcomeScreen);
        buttonWelcomeScreen.setOnClickListener(this);

        buttonBullSellInfo = (Button) findViewById(R.id.buttonBullSellInfo);
        buttonBullSellInfo.setOnClickListener(this);

        buttonAccoverview = (Button) findViewById(R.id.buttonAccoverview);
        buttonAccoverview.setOnClickListener(this);

        buttonBuyLayout = (Button) findViewById(R.id.buttonBuyLayout);
        buttonBuyLayout.setOnClickListener(this);

        buttonGameListing = (Button) findViewById(R.id.buttonGameListing);
        buttonGameListing.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonWelcomeScreen) {
            Intent intentWelcome = new Intent(this, WelcomeScreen.class);
            this.startActivity(intentWelcome);
        } else if (v.getId() == R.id.buttonBullSellInfo) {
            Intent intentBuySellInfo = new Intent(this, ActivityBuySellInfo.class);
            this.startActivity(intentBuySellInfo);
        } else if (v.getId() == R.id.buttonAccoverview) {
            Intent intentAccountOverview = new Intent(this, AccOverview.class);
            this.startActivity(intentAccountOverview);
        }

    }
}
