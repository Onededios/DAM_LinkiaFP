package com.m08.galaxyevader.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m08.galaxyevader.R;
import com.m08.galaxyevader.controllers.Sound;
import com.m08.galaxyevader.data.SQLite;
import com.m08.galaxyevader.objects.Background;
import com.m08.galaxyevader.custom.CustomViewIdle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PageEnd extends AppCompatActivity {
    private CustomViewIdle idle_JOO;
    private final Handler handler_JOO = new Handler();
    private Button button_return_JOO;
    private TextView textView_User_JOO;
    private TextView textView_Score_JOO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endgame);

        // Initialize UI elements
        idle_JOO = findViewById(R.id.view_idle_end);
        button_return_JOO = findViewById(R.id.button_return);
        textView_User_JOO = findViewById(R.id.textView_User);
        textView_Score_JOO = findViewById(R.id.textView_Score);

        // Initialize background for idle view
        Background background_JOO = new Background();
        background_JOO.initializeStars();
        idle_JOO.setBackground(background_JOO);

        try {
            // Set the end game background music
            Sound.getInstance().setSongEnd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Retrieve username and score from the previous activity
        String username_JOO = getIntent().getStringExtra("username");
        int score_JOO = getIntent().getIntExtra("score", 0);

        // Set username and score on the UI
        textView_User_JOO.setText(username_JOO);
        textView_Score_JOO.setText(String.valueOf(score_JOO));

        // Insert the new play record into the SQLite database
        new SQLite(this).insertNewPlay(username_JOO, score_JOO);

        button_return_JOO.setOnClickListener(view_JOO -> goToMainMenu());

        // Set up a timer for updating the game state and refreshing the view
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Post a task to the handler for updating and refreshing the view
                handler_JOO.post(() -> {
                    // Update enemies and refresh the idle view
                    idle_JOO.invalidate();
                });
            }
        }, 0, 200); // Schedule the task to run every 25 milliseconds
    }

    // Method to navigate back to the main menu
    private void goToMainMenu() {
        Intent intent_JOO = new Intent(this, PageMainMenu.class);
        this.finish();
        startActivity(intent_JOO);
    }
}
