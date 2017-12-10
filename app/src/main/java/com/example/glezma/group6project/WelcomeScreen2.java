package com.example.glezma.group6project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen2 extends Activity {
    // Array list of games
    ArrayList<Game> list = new ArrayList<Game>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen2);


        // Create the adapter and connecting it to the listview
        final CustomAdapter adapter = new CustomAdapter(this, list);
        ListView listViewGames = (ListView) findViewById(R.id.listViewGames);
        listViewGames.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Games");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Game game;
                game = dataSnapshot.getValue(Game.class);
                list.add(0,game);
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

        // Go to BuySellInfo if any element in listview is clicked
        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentMain2 = new Intent(WelcomeScreen2.this, ActivityBuySellInfo.class);
                intentMain2.putExtra("game", list.get(position).name);
                intentMain2.putExtra("game_date", list.get(position).date);
                WelcomeScreen2.this.startActivity(intentMain2);

            }
        });


    }


    class CustomAdapter extends ArrayAdapter<Game>{
        public CustomAdapter(@NonNull Context context, ArrayList<Game> games) {
            super(context, 0,games);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Get the data item for this position
            Game game = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.games_list_item, parent, false);
            }
            // Lookup view for data population
            TextView gameName = (TextView) convertView.findViewById(R.id.textViewGameName);
            TextView gameHome = (TextView) convertView.findViewById(R.id.textViewGameDate);
            // Populate the data into the template view using the data object
            gameName.setText(game.name);
            gameHome.setText(game.date);
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
