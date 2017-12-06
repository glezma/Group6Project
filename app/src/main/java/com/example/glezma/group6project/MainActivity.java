package com.example.glezma.group6project;
//
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {
//test
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // 1. Define the objects
    private EditText editTextName;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Link the objects
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        // 4. Activate click on my button

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    @Override
    public void onClick(View v) {

        // Catch the values
        String email = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        //Figure which button has been clicked
        if (v == buttonLogin){
            signIn(email,password);
        } else if (v==buttonRegister){
            createAccount(email,password);
        }

//        // 5. Login process
//        if (editTextName.getText().toString().equals("ross") &&
//                editTextPassword.getText().toString().equals("to426")) {
//            textViewMessage.setText("Login Successful");
//            // 6. Go to activity
//            Intent intentHomePage = new Intent(this, ActivityHomePage.class);
//            this.startActivity(intentHomePage);
//
//        }
//        else   {
//            textViewMessage.setText("Access Denied for" + editTextName.getText());
//        }

    }


    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication successful",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // If signin fails, display a message to the user. If signin succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();

                            // 6. Go to activity
                            Intent intentHomePage = new Intent(MainActivity.this, WelcomeScreen.class);
                            MainActivity.this.startActivity(intentHomePage);
                        }
                        // If signin fails, display a message to the user. If signin succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
            //  Intent intentLogOut = new Intent(this, LogOut.class);
            // this.startActivity(intentLogOut);
        }

        return super.onOptionsItemSelected(item);
    }
}
