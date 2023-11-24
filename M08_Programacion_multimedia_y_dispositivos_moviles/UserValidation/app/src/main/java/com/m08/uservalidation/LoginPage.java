package com.m08.uservalidation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class LoginPage extends AppCompatActivity {
    private EditText editTextUserInp_JOO; // EditText to get the username
    private EditText editTextPassInp_JOO; // EditText to get the password
    private Button buttonSubmit_JOO; // Button to make the login action

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);
        setContentView(R.layout.login); // Set the content view to the "login" layout

        // Create Intent objects to access other pages
        Intent okPage_JOO = new Intent(this, OkPage.class);
        Intent koPage_JOO = new Intent(this, KoPage.class);


        // Initialize UI elements
        editTextUserInp_JOO = findViewById(R.id.editTextUserInp); // Find and reference the username input field
        editTextPassInp_JOO = findViewById(R.id.editTextPassInp); // Find and reference the password input field
        buttonSubmit_JOO = findViewById(R.id.buttonSubmit); // Find and reference the submit button

        TriesDB triesDB_JOO = new TriesDB(getApplicationContext());

        // Define a click event for the submit button
        buttonSubmit_JOO.setOnClickListener(view_JOO -> {
            // Get the username and password from input fields
            String username_JOO = editTextUserInp_JOO.getText().toString();
            String password_JOO = editTextPassInp_JOO.getText().toString();

            // Validate the username and password
            String userVal_JOO = validateUsername(username_JOO);
            String passVal_JOO = validatePassword(password_JOO);

            // Initialize flags to check if the username and password are valid
            boolean userOk_JOO = false;
            boolean passOk_JOO = false;

            // If username validation is "ok," set the userOk_JOO flag to true; otherwise, show a toast message
            if (userVal_JOO.equals("ok")) userOk_JOO = true;
            else Toast.appearToast(view_JOO, userVal_JOO);

            // If password validation is "ok," set the passOk_JOO flag to true; otherwise, show a toast message
            if (passVal_JOO.equals("ok")) passOk_JOO = true;
            else Toast.appearToast(view_JOO, passVal_JOO);

            // If both username and password are valid, proceed with user validation
            if (userOk_JOO && passOk_JOO) {
                // Validate the user's credentials using a connection and check the status
                String status_JOO = Connection.validateUser(username_JOO, password_JOO);

                // If validation is successful, start the "okPage_JOO" activity and finish the current activity
                if (status_JOO.equals("ok")) {
                    startActivity(okPage_JOO);
                    finish();
                }
                // If validation fails, show an error toast, insert a new try into the database, and start the "koPage_JOO" activity
                else {
                    Toast.appearToast(view_JOO, status_JOO);
                    triesDB_JOO.insertNewTry(username_JOO, password_JOO);
                    startActivity(koPage_JOO);
                    finish();
                }
            }
            // If either the username or password is invalid, insert a new try into the database
            else triesDB_JOO.insertNewTry(username_JOO, password_JOO);

        });
    }

    // Method to validate the username
    private String validateUsername(String username_JOO) {
        if (username_JOO.equals("")) return "user_not_introduced";
        else if (!username_JOO.matches("^[a-zA-Z0-9]+$")) return "invalid";
        return "ok";
    }

    // Method to validate the password
    private String validatePassword(String password_JOO) {
        if (password_JOO.equals("")) return "password_not_introduced";
        else if (password_JOO.length() < 4 || password_JOO.length() > 8) return "invalid";
        return "ok";
    }
}
