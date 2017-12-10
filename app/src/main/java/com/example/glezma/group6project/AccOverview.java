package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AccOverview extends Activity {

    // Array list of transaction intents
    ArrayList<TradeIntent> list = new ArrayList<TradeIntent>();

    private ListView transactionsList;
    private  TextView emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accoverview);
        emailText = findViewById(R.id.EmailText);


        // Create the adapter and connecting it to the listview
        final CustomAdapter2 adapter = new CustomAdapter2(this, list);
        ListView transactionsList = (ListView) findViewById(R.id.listViewTransactionsList);
        transactionsList.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        emailText.setText(userEmail);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TradeIntents");


        myRef.orderByChild("user").equalTo(userEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TradeIntent ti;
                ti = dataSnapshot.getValue(TradeIntent.class);
                list.add(0,ti);
                adapter.notifyDataSetChanged();
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
//        TransactionsList = findViewById(R.id.TransactionsList);
//
//        CustomAdapter customAdapter = new CustomAdapter();
//        TransactionsList.setAdapter(customAdapter);

    }


    class CustomAdapter2 extends ArrayAdapter<TradeIntent>{
        public CustomAdapter2(@NonNull Context context, ArrayList<TradeIntent> ti) {
            super(context, 0,ti);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            TradeIntent ti = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.gamelistings, parent, false);
            }
            // Lookup view for data population
            TextView tvGame = (TextView) convertView.findViewById(R.id.textViewGame);
            TextView tvDate = (TextView) convertView.findViewById(R.id.textViewDate);
            TextView tvType = (TextView) convertView.findViewById(R.id.textViewTType);
            TextView tvQuantity = (TextView) convertView.findViewById(R.id.textViewTickets);
            TextView tvPrice = (TextView) convertView.findViewById(R.id.textViewPrice);

            // Populate the data into the template view using the data object
            tvGame.setText(ti.game);
            tvDate.setText(ti.game_date);
            tvType.setText(ti.type);
            tvQuantity.setText(ti.quantity.toString());
            tvPrice.setText(ti.price.toString());

            // Return the completed view to render on screen
            return convertView;
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
