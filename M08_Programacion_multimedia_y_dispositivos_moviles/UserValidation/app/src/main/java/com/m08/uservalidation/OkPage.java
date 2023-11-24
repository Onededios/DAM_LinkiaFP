package com.m08.uservalidation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OkPage extends AppCompatActivity {

    private Button buttonReturnLogin_JOO; // Button to log out
    private Button buttonReload_JOO; // Button to reload the info
    private ListView listViewUsers_JOO; // List to view all the users

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);
        setContentView(R.layout.ok); // Set the content view to the "ok" layout

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
        buttonReload_JOO.setOnClickListener(view_JOO -> rechargeInfo(listViewUsers_JOO));
    }

    // Method to load user information into the ListView
    private void rechargeInfo(ListView listViewUsers_JOO) {
        ArrayList<String> allRegisteredUsers_JOO = Connection.allUsers();
        String first_JOO = allRegisteredUsers_JOO.get(0);

        // Check if the retrieved data matches the expected format
        if (first_JOO.matches("^(\\w+);(\\w+)$")) {
            UserAdapter userAdapter_JOO = new UserAdapter(this, User.getStringUsersToUserList(allRegisteredUsers_JOO), false);
            listViewUsers_JOO.setAdapter(userAdapter_JOO);
        }
        // If the data doesn't match the expected format, display an error message
        else Toast.appearToast(listViewUsers_JOO, first_JOO);
    }
}
