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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeScreen2 extends Activity {

    private ListView listViewGames; // 1. Declare LsitViewObject

    //3. Staticly defining data
    private String[] GAMES = {"Cincinnati", "Air Force", "Michigan State", "Rutgers","Minnesota","Ohio State"};
    private String[] DATES = {"09/09","09/16","10/07","10/28","11/04","11/25"};

    // optional include images
    private int[] IMAGES = {R.drawable.football,R.drawable.football,R.drawable.football,R.drawable.football};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen2);

        listViewGames = findViewById(R.id.listViewGames);// 2. Linking ListViewObject

        //  12. Define Custom adapter object and connect it
        CustomAdapter customAdapter = new CustomAdapter();
        listViewGames.setAdapter(customAdapter);

        // 13. Add Listener to program click events (new Onclick...
        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(WelcomeScreen2.this, "You clicked "+GAMES[position], Toast.LENGTH_SHORT).show();
                Intent intentBuySellActivity = new Intent(WelcomeScreen2.this, ActivityBuySellInfo.class);
                WelcomeScreen2.this.startActivity(intentBuySellActivity);

            }
        });

    }




    // 4. Custom adapter class
    class CustomAdapter extends BaseAdapter {

        // 5. automaticly generating template
        @Override
        public int getCount() {

            return GAMES.length; // 6. override getcoung
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 7. Generate layout go to xml and create one (new, layout resource file
            // 8. Inflate layout
            convertView = getLayoutInflater().inflate(R.layout.games_list_item,null);
            // 9. Connect layout widgets which live inside convertView
            TextView textViewGame = convertView.findViewById(R.id.textViewGameName);
            TextView textViewDate = convertView.findViewById(R.id.textViewGameDate);


            // 10. Populate data
            textViewGame.setText(GAMES[position]);
            textViewDate.setText(DATES[position]);

            // 11. Return view
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
