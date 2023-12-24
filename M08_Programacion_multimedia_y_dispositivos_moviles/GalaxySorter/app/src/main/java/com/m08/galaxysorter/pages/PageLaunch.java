package com.m08.galaxyevader.pages;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.m08.galaxyevader.R;
import com.m08.galaxyevader.controllers.Level;
import com.m08.galaxyevader.objects.Background;
import com.m08.galaxyevader.custom.CustomViewIdle;

import java.util.Timer;
import java.util.TimerTask;

public class PageLaunch extends AppCompatActivity {
    private CustomViewIdle idle_JOO;
    private final Handler handler_JOO = new Handler();
    private EditText editText_nameInput_JOO;
    private SeekBar seekBar_level;
    private Button button_launchButton;
    private TextView textView_DifficultySelected;
    private TextView textView_CurrentDifficultyLevel_JOO;
    private TextView textView_EnemyPeriod_JOO;
    private TextView textView_EnemyMinSpeed_JOO;
    private TextView textView_EnemyMaxSpeed_JOO;
    private Level level_JOO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        // Initialize UI elements
        idle_JOO = findViewById(R.id.view_idle_launch);
        editText_nameInput_JOO = findViewById(R.id.editText_nameInput);
        seekBar_level = findViewById(R.id.seekBar_level);
        button_launchButton = findViewById(R.id.button_Launch);
        textView_DifficultySelected = findViewById(R.id.textView_DifficultySelected);
        textView_CurrentDifficultyLevel_JOO = findViewById(R.id.textView_CurrentDifficultyLevel);
        textView_EnemyPeriod_JOO = findViewById(R.id.textView_EnemyPeriod);
        textView_EnemyMinSpeed_JOO = findViewById(R.id.textView_EnemyMinSpeed);
        textView_EnemyMaxSpeed_JOO = findViewById(R.id.textView_EnemyMaxSpeed);

        // Initialize background
        Background background_JOO = new Background();
        background_JOO.initializeStars();
        idle_JOO.setBackground(background_JOO);


        // On page load update UI based on seekBar progress
        updateUI(seekBar_level.getProgress());

        // Set up a listener for seekBar changes
        seekBar_level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateUI(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Set up a click listener for the launch button
        button_launchButton.setOnClickListener(view_JOO -> {
            String userInput_JOO = editText_nameInput_JOO.getText().toString();
            if (userInput_JOO.length() > 0) goToPageInGame(userInput_JOO);
            else
                Toast.makeText(this, "Must introduce a new username to play", Toast.LENGTH_LONG).show();

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

    // Update the UI elements based on the seekBar progress
    private void updateSelectedDifficulty(String difficultyText_JOO, int diffcultyColor_JOO) {
        int color_JOO = ContextCompat.getColor(this, diffcultyColor_JOO);
        textView_DifficultySelected.setText(difficultyText_JOO);
        textView_DifficultySelected.setTextColor(color_JOO);
        textView_CurrentDifficultyLevel_JOO.setTextColor(color_JOO);
        textView_EnemyPeriod_JOO.setTextColor(color_JOO);
        textView_EnemyMinSpeed_JOO.setTextColor(color_JOO);
        textView_EnemyMaxSpeed_JOO.setTextColor(color_JOO);
        seekBar_level.setProgressTintList(ColorStateList.valueOf(color_JOO));
        seekBar_level.setThumbTintList(ColorStateList.valueOf(color_JOO));
        seekBar_level.setSecondaryProgressTintList(ColorStateList.valueOf(color_JOO));
    }

    // Update the selected difficulty and associated UI elements
    private void updateUI(int progress_JOO) {
        progress_JOO += 1;
        level_JOO = new Level(progress_JOO);
        textView_CurrentDifficultyLevel_JOO.setText("" + progress_JOO);
        textView_EnemyPeriod_JOO.setText((level_JOO.getEnemyPeriod() / 1000F) + " e/s");
        textView_EnemyMinSpeed_JOO.setText(level_JOO.getEnemyMinXSpeed() + " p/s");
        textView_EnemyMaxSpeed_JOO.setText(level_JOO.getEnemyMaxXSpeed() + " p/s");

        // Update the selected difficulty color
        if (progress_JOO > 8) updateSelectedDifficulty("!*@?", R.color.vivid_dark_red);
        else if (progress_JOO > 6) updateSelectedDifficulty("Hell", R.color.vivid_dark_orange);
        else if (progress_JOO > 4) updateSelectedDifficulty("Heck", R.color.vivid_dark_yellow);
        else updateSelectedDifficulty("Haha", R.color.vivid_dark_green);
    }

    // Navigate to the in-game page
    private void goToPageInGame(String userInput_JOO) {
        Intent intent_JOO = new Intent(this, PageInGame.class);
        intent_JOO.putExtra("username", userInput_JOO);
        intent_JOO.putExtra("level", level_JOO.getActualLevel());
        this.finish();
        startActivity(intent_JOO);
    }
}
