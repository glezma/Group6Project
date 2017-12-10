package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ActivityBuySellInfo extends Activity implements View.OnClickListener{

    private Button buttonBuy;
    private Button buttonSell;
    private ListView listViewBuy;
    private ListView listViewSell;
    private TextView textViewGameName;
    private TextView textViewGameDate;
    // Array list of transaction intents
    ArrayList<TradeIntent> list_buy = new ArrayList<TradeIntent>();
    ArrayList<TradeIntent> list_sell = new ArrayList<TradeIntent>();


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

        // Create the adapter and connecting it to the listview
        final CustomAdapterBuy adapter_buy = new CustomAdapterBuy(this, list_buy);
        listViewBuy.setAdapter(adapter_buy);
        final CustomAdapterSell adapter_sell = new CustomAdapterSell(this, list_sell);
        listViewSell.setAdapter(adapter_sell);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TradeIntents");


        Bundle extras = getIntent().getExtras();
        String name = extras.getString("game");
        String date = extras.getString("game_date");
        textViewGameName.setText(name);
        textViewGameDate.setText(date);


        buttonBuy.setOnClickListener(this);
        buttonSell.setOnClickListener(this);

        Query ref0_buy = myRef.orderByChild("type").equalTo("buy");
        Query ref_buy = ref0_buy.getRef().orderByChild("game").equalTo(name);

        Query ref0_sell = myRef.orderByChild("type").equalTo("sell");
        Query ref_sell = ref0_sell.getRef().orderByChild("game").equalTo(name);

        ref_buy.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TradeIntent ti;
                ti = dataSnapshot.getValue(TradeIntent.class);
                if (ti.type.equals("buy") && ti.status.equals("Pending")) {
                    list_buy.add(0, ti);
                    adapter_buy.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref_sell.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TradeIntent ti;
                ti = dataSnapshot.getValue(TradeIntent.class);
                if (ti.type.equals("sell") && ti.status.equals("Pending")) {
                    list_sell.add(0, ti);
                    adapter_sell.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Change what happens when the elements of ListView are clicked here
        listViewBuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ActivityBuySellInfo.this,"You clicked " + i, Toast.LENGTH_SHORT).show();
                Intent intentTC = new Intent(ActivityBuySellInfo.this, TransactionConfirmation.class);
                intentTC.putExtra("game", list_buy.get(i).game);
                intentTC.putExtra("game_date", list_buy.get(i).game_date);
                intentTC.putExtra("price", list_buy.get(i).price.toString());
                intentTC.putExtra("type", list_buy.get(i).type);
                intentTC.putExtra("quantity", list_buy.get(i).quantity.toString());
                intentTC.putExtra("user", list_buy.get(i).user);
//                intentTC.putExtra("list_buy",list_buy);

                ActivityBuySellInfo.this.startActivity(intentTC);
            }
        });

        listViewSell.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ActivityBuySellInfo.this,"You clicked " +i,Toast.LENGTH_SHORT).show();
                Intent intentTC = new Intent(ActivityBuySellInfo.this, TransactionConfirmation.class);
                intentTC.putExtra("game", list_sell.get(i).game);
                intentTC.putExtra("game_date", list_sell.get(i).game_date);
                intentTC.putExtra("price", list_sell.get(i).price.toString());
                intentTC.putExtra("type", list_sell.get(i).type);
                intentTC.putExtra("quantity", list_sell.get(i).quantity.toString());
                intentTC.putExtra("user", list_sell.get(i).user);
//                intentTC.putExtra("list_buy",list_buy);

                ActivityBuySellInfo.this.startActivity(intentTC);
            }
        });


    }

    class CustomAdapterBuy extends ArrayAdapter<TradeIntent>{
        public CustomAdapterBuy(@NonNull Context context, ArrayList<TradeIntent> ti) {
            super(context, 0,ti);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            TradeIntent ti = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.buylayout, parent, false);
            }
            // Lookup view for data population
            TextView tvQuantity = (TextView) convertView.findViewById(R.id.textViewBuyNumber);
            TextView tvPrice = (TextView) convertView.findViewById(R.id.textViewBuyPrice);


            // Populate the data into the template view using the data object
            tvQuantity.setText(ti.quantity.toString());
            tvPrice.setText(ti.price.toString());

            // Return the completed view to render on screen
            return convertView;
        }
    }

    class CustomAdapterSell extends ArrayAdapter<TradeIntent>{
        public CustomAdapterSell(@NonNull Context context, ArrayList<TradeIntent> ti) {
            super(context, 0,ti);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            TradeIntent ti = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.selllayout, parent, false);
            }
            // Lookup view for data population
            TextView tvQuantity = (TextView) convertView.findViewById(R.id.textViewSellNumber);
            TextView tvPrice = (TextView) convertView.findViewById(R.id.textViewSellPrice);


            // Populate the data into the template view using the data object
            tvQuantity.setText(ti.quantity.toString());
            tvPrice.setText(ti.price.toString());

            // Return the completed view to render on screen
            return convertView;
        }
    }

    @Override
    public void onClick(View v) {
        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        if (v==buttonBuy) {
            Intent intentBuy = new Intent(this,Confirmation.class   );
            intentBuy.putExtra("type", "buy");
            String name = extras.getString("game");
            intentBuy.putExtra("game", name);
            intentBuy.putExtra("date", date);
            this.startActivity(intentBuy);
        } else if (v==buttonSell){
            Intent intentSell = new Intent(this,Confirmation.class   );
            intentSell.putExtra("type", "sell");
            String name = extras.getString("game");
            intentSell.putExtra("game", name);
            intentSell.putExtra("date", date);
            this.startActivity(intentSell);
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
            Intent intentHome = new Intent(this, WelcomeScreen2.class);
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
