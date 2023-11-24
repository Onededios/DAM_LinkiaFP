package com.m08.uservalidation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class KoPage extends AppCompatActivity {

    private Button buttonReturnLogin_JOO; // Button to log out
    private Button buttonReload_JOO; // Button to reload the info
    private ListView listViewUsers_JOO; // List to view the user intents

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);
        setContentView(R.layout.ko);

        // Create the objects for accessing the other pages
        Intent loginPage = new Intent(this, LoginPage.class);

        // Initialize UI elements
        buttonReturnLogin_JOO = findViewById(R.id.buttonReturnLogin);
        buttonReload_JOO = findViewById(R.id.buttonReload);
        listViewUsers_JOO = findViewById(R.id.listViewUsers);

        // Load user information into the ListView
        rechargeInfo(listViewUsers_JOO);

        // Define an action for the "Return to Login" button
        buttonReturnLogin_JOO.setOnClickListener(view_JOO -> startActivity(loginPage));

        // Define an action for the "Reload" button
        buttonReload_JOO.setOnClickListener(view_JOO -> deleteInfo(listViewUsers_JOO));
    }

    // Method to load user information into the ListView
    private void rechargeInfo(ListView listViewUsers_JOO) {
        TriesDB tries_JOO = new TriesDB(listViewUsers_JOO.getContext());
        listViewUsers_JOO.setAdapter(new UserAdapter(this, tries_JOO.getAllTries(), true));
    }

    // Method to delete and load user information into the ListView

    private void deleteInfo(ListView listViewUsers_JOO) {
        TriesDB tries_JOO = new TriesDB(listViewUsers_JOO.getContext());
        tries_JOO.deleteAllTries();
        rechargeInfo(listViewUsers_JOO);
    }
}
