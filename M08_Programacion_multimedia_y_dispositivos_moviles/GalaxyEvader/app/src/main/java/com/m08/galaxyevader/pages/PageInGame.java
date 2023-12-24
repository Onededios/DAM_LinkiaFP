package com.m08.galaxyevader.pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m08.galaxyevader.controllers.Level;
import com.m08.galaxyevader.controllers.Sound;
import com.m08.galaxyevader.objects.Player;
import com.m08.galaxyevader.custom.CustomViewGame;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PageInGame extends AppCompatActivity {
    // Handler for managing UI updates on the main thread
    private final Handler handler_JOO = new Handler();
    // View representing the game
    private CustomViewGame game_JOO;
    // Level controller for managing game levels
    private Level level_JOO;
    // Player object representing the user
    private Player player_JOO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Because there are no needed elements rather than the View, setting my own view as content view
        game_JOO = new CustomViewGame(this);
        setContentView(game_JOO);

        // Set player and level objects
        player_JOO = new Player(getIntent().getStringExtra("username"));
        level_JOO = new Level(getIntent().getIntExtra("level", 1));

        try {
            // Set the in-game background music
            Sound.getInstance().setSongInGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set player and level for the game view
        game_JOO.setPlayer(player_JOO);
        game_JOO.setLevel(level_JOO);
        level_JOO.setPlayer(player_JOO);

        // Start the game timer
        level_JOO.startGameTimer();

        // Set up a timer for updating the game state and refreshing the view
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler_JOO.post(() -> {
                    // Check if the player has crashed; stop the timer and go to end page if true
                    if (player_JOO.hasCrashed()) {
                        this.cancel();
                        try {
                            // Play explosion sound and wait for a moment
                            Sound.getInstance().playSoundExplosion();
                            Thread.sleep(1500);
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        goToPageEnd();
                    }

                    // Update enemies and refresh the game view
                    level_JOO.updateEnemies();
                    game_JOO.invalidate();
                });
            }
        }, 0, 25); // Schedule the task to run every 25 milliseconds
    }

    // Method to navigate to the end page
    private void goToPageEnd() {
        Intent intent_JOO = new Intent(this, PageEnd.class);
        intent_JOO.putExtra("username", player_JOO.getName());
        intent_JOO.putExtra("score", player_JOO.getScore());
        this.finish();
        startActivity(intent_JOO);
    }
}

