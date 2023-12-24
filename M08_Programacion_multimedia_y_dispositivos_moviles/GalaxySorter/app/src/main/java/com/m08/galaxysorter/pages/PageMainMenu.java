package com.m08.galaxyevader.pages;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.m08.galaxyevader.R;
import com.m08.galaxyevader.controllers.Screen;
import com.m08.galaxyevader.controllers.Sound;
import com.m08.galaxyevader.custom.CustomAdapterPlay;
import com.m08.galaxyevader.graphics.SpriteSheet;
import com.m08.galaxyevader.objects.Background;
import com.m08.galaxyevader.custom.CustomViewIdle;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PageMainMenu extends AppCompatActivity {
    private CustomViewIdle idle_JOO;
    private final Handler handler_JOO = new Handler();
    private Button button_StartGame_JOO;
    private ListView listView_Laderboard_JOO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Initialize UI components
        button_StartGame_JOO = findViewById(R.id.button_StartGame);
        listView_Laderboard_JOO = findViewById(R.id.listView_Laderboard);
        idle_JOO = findViewById(R.id.view_idle);

        // Set up the leaderboard list view with a custom adapter
        listView_Laderboard_JOO.setAdapter(new CustomAdapterPlay(this));

        button_StartGame_JOO.setOnClickListener(view_JOO -> goToLaunchPage());

        // Initialize and set up background music
        Sound.getInstance().initialize(this);
        try {
            // Set the in-game background music
            Sound.getInstance().setSongMain();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set up a listener to get the dimensions of the idle view and initialize Screen and Background
        ViewTreeObserver obs = idle_JOO.getViewTreeObserver();
        obs.addOnGlobalLayoutListener(() -> {
            Screen.getInstance().initialize(idle_JOO.getWidth(), idle_JOO.getHeight(), new Bitmap[]{SpriteSheet.getBitmap(this, R.drawable.sp_ships),}, new Bitmap[]{SpriteSheet.getBitmap(this, R.drawable.sp_bg_stars_1), SpriteSheet.getBitmap(this, R.drawable.sp_bg_stars_2),}, new Bitmap[]{SpriteSheet.getBitmap(this, R.drawable.sp_fg_stars_1), SpriteSheet.getBitmap(this, R.drawable.sp_fg_stars_2)});

            Background background = new Background();
            background.initializeStars();
            idle_JOO.setBackground(background);
        });

        // Set up a timer for updating the game state and refreshing the view
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler_JOO.post(() -> {
                    // Update enemies and refresh the idle view
                    idle_JOO.invalidate();
                });
            }
        }, 0, 200); // Schedule the task to run every 25 milliseconds
    }

    // Navigate to the launch page
    private void goToLaunchPage() {
        Intent intent_JOO = new Intent(this, PageLaunch.class);
        this.finish();
        startActivity(intent_JOO);
    }
}
