package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActivityBuySellInfo extends Activity implements View.OnClickListener{

    private Button buttonBuy;
    private Button buttonSell;
    private ListView listViewBuy;
    private ListView listViewSell;
    private TextView textViewGameName;
    private TextView textViewGameDate;

    private String[] BUYTICKETNO = {"20","12","8","6","4"};
    private String[] BUYTICKETPRICE = {"$100","$95","$92","$85","$80"};
    private String[] SELLTICKETNO = {"22","16","14","12","5"};
    private String[] SELLTICKETPRICE = {"$120","$115","$110","$105","$102"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell_info);

        buttonBuy = findViewById(R.id.buttonBuy);
        buttonSell = findViewById(R.id.buttonSell);
        listViewBuy = findViewById(R.id.listViewBuy);
        listViewSell = findViewById(R.id.listViewSell);
        textViewGameName = findViewById(R.id.textViewGameName);
        textViewGameDate = findViewById(R.id.textViewGameDate);

        CustomAdapter1 customAdapter1 = new CustomAdapter1();
        CustomAdapter2 customAdapter2 = new CustomAdapter2();
        listViewBuy.setAdapter(customAdapter1);
        listViewSell.setAdapter(customAdapter2);
        // listViewGameName.setAdapter(customAdapter3);

        buttonBuy.setOnClickListener(this);
        buttonSell.setOnClickListener(this);

        // Change what happens when the elements of ListView are clicked here
        listViewBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActivityBuySellInfo.this,"You clicked" + BUYTICKETPRICE[i], Toast.LENGTH_SHORT).show();
            }
        });

        listViewSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ActivityBuySellInfo.this,"You clicked" + SELLTICKETPRICE[i],Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View view) {

    }

    class CustomAdapter1 extends BaseAdapter{

        @Override
        public int getCount() {
            return BUYTICKETNO.length;
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

            view = getLayoutInflater().inflate(R.layout.buylayout,null);
            TextView textViewBuyNumber = view.findViewById(R.id.textViewBuyNumber);
            TextView textViewBuyPrice = view.findViewById(R.id.textViewBuyPrice);

            textViewBuyNumber.setText(BUYTICKETNO[i]);
            textViewBuyPrice.setText(BUYTICKETPRICE[i]);

            return view;
        }

    }


    class CustomAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return BUYTICKETNO.length;
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

            view = getLayoutInflater().inflate(R.layout.selllayout, null);
            TextView textViewSellNumber = view.findViewById(R.id.textViewSellNumber);
            TextView textViewSellPrice = view.findViewById(R.id.textViewSellPrice);

            textViewSellNumber.setText(SELLTICKETNO[i]);
            textViewSellPrice.setText(SELLTICKETPRICE[i]);

            return view;
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
            Intent intentMyTickets = new Intent(this, AccOverview.class);
            this.startActivity(intentMyTickets);
        } else if (item.getItemId() == R.id.menuItemLogOut) {
              Intent intentLogOut = new Intent(this, MainActivity.class);
             this.startActivity(intentLogOut);
        }

        return super.onOptionsItemSelected(item);
    }
}
