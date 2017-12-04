package com.example.glezma.group6project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AccOverview extends Activity {

    private ListView TransactionsList;

    private String[] DATE = {"11-02-2017", "10-20-2017"};
    private String[] GAME = {"Michigan v Rutgers", "Michigan v OSU"};
    private String[] TTYPE = {"Buy", "Sell"};
    private String[] TICKETS = {"1","2"};
    private String[] PRICE = {"$20","$80"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accoverview);

        TransactionsList = findViewById(R.id.TransactionsList);

        CustomAdapter customAdapter = new CustomAdapter();
        TransactionsList.setAdapter(customAdapter);

    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return DATE.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.gamelistings,null);
            TextView textViewDate = view.findViewById(R.id.textViewDate);
            TextView textViewGame = view.findViewById(R.id.textViewGame);
            TextView textViewTType = view.findViewById(R.id.textViewTType);
            TextView textViewTickets = view.findViewById(R.id.textViewTickets);
            TextView textViewPrice = view.findViewById(R.id.textViewPrice);

            textViewDate.setText(DATE[i]);
            textViewGame.setText(GAME[i]);
            textViewTType.setText(TTYPE[i]);
            textViewTickets.setText(TICKETS[i]);
            textViewPrice.setText(PRICE[i]);


            return view;
        }
    }
}
