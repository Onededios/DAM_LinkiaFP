package com.m08.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerPage extends AppCompatActivity {
    private MediaPlayer media_JOO; // Media player for playing the current song
    private SongObserver observer_JOO; // Observer for updating UI based on song progress

    // UI components
    private Button buttonReturn_JOO;
    private TextView textViewSongName_JOO;
    private TextView textViewSongAuthor_JOO;
    private TextView textViewSongAlbum_JOO;
    private ImageView imageViewPortrait_JOO;
    private TextView textViewRemaining_JOO;
    private TextView textViewTotalTime_JOO;
    private ProgressBar progressBar_JOO;
    private Button buttonPrevSong_JOO;
    private Button buttonBackward_JOO;
    private Button buttonStop_JOO;
    private Button buttonPlayPause_JOO;
    private Button buttonForward_JOO;
    private Button buttonNextSong_JOO;

    // Method to initialize UI components
    private void initView() {
        buttonReturn_JOO = findViewById(R.id.buttonReturn);
        textViewSongName_JOO = findViewById(R.id.textViewSongName);
        textViewSongAuthor_JOO = findViewById(R.id.textViewSongAuthor);
        textViewSongAlbum_JOO = findViewById(R.id.textViewSongAlbum);
        imageViewPortrait_JOO = findViewById(R.id.imageViewPortrait);
        textViewRemaining_JOO = findViewById(R.id.textViewRemaining);
        textViewTotalTime_JOO = findViewById(R.id.textViewTotalTime);
        progressBar_JOO = findViewById(R.id.progressBar);
        buttonPrevSong_JOO = findViewById(R.id.buttonPrevSong);
        buttonBackward_JOO = findViewById(R.id.buttonBackward);
        buttonStop_JOO = findViewById(R.id.buttonStop);
        buttonPlayPause_JOO = findViewById(R.id.buttonPlayPause);
        buttonForward_JOO = findViewById(R.id.buttonForward);
        buttonNextSong_JOO = findViewById(R.id.buttonNextSong);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);
        setContentView(R.layout.player); // Set the content view to the "player" layout
        initView();

        setSong(); // Set up the initial song

        // Listener to return to the list page
        buttonReturn_JOO.setOnClickListener(view_JOO -> {
            startActivity(new Intent(this, ListPage.class));
            finish();
        });

        // Listener stop and replay the current song
        buttonStop_JOO.setOnClickListener(view_JOO -> replaySong());

        // Listener to start or pause the current song
        buttonPlayPause_JOO.setOnClickListener(view_JOO -> startStopSong());

        // Listener to skip forward in the current song by 10 seconds
        buttonForward_JOO.setOnClickListener(view_JOO -> changeMediaCurrentDuration(10000));

        // Listener to skip backward in the current song by 10 seconds
        buttonBackward_JOO.setOnClickListener(view_JOO -> changeMediaCurrentDuration(-10000));

        // Listener to play the next song in the list
        buttonNextSong_JOO.setOnClickListener(view_JOO -> {
            Song.setNextSong();
            setSong();
        });

        // Listener to play the previous song in the list
        buttonPrevSong_JOO.setOnClickListener(view_JOO -> {
            Song.setPrevSong();
            setSong();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        endSong();
    }

    // Method to set up and start playing a new song
    private void setSong() {
        endSong(); // Release resources from the previous song
        Song actualSong_JOO = Song.getSelectedSong(); // Get the selected song

        // Update UI with song details
        textViewSongName_JOO.setText(actualSong_JOO.getName());
        textViewSongAuthor_JOO.setText(actualSong_JOO.getAuthor());
        textViewSongAlbum_JOO.setText(actualSong_JOO.getAlbum());
        textViewTotalTime_JOO.setText(actualSong_JOO.getDuration());
        SongAdapter.setDecodedImage(actualSong_JOO.getAlbumImage(), imageViewPortrait_JOO);

        // Create a new media player with the selected song's URI
        media_JOO = MediaPlayer.create(this, actualSong_JOO.getUri());

        // Create a new observer to update the progress bar and time labels
        observer_JOO = new SongObserver(progressBar_JOO, media_JOO, textViewRemaining_JOO);

        // Set listener to automatically play the next song when the current one completes
        media_JOO.setOnCompletionListener(mediaplayer_JOO -> {
            Song.setNextSong();
            setSong();
        });

        startStopSong(); // Start playing the new song
    }

    // Method to release resources when the song ends or the activity is destroyed
    private void endSong() {
        // Stop and release the observer
        if (observer_JOO != null) {
            observer_JOO.stopObserver();
        }
        // Release the media player and clears the media variable
        if (media_JOO != null) {
            if (media_JOO.isPlaying()) media_JOO.stop();
            media_JOO.release(); // Release resources after stopping
            media_JOO = null;
        }
    }

    // Method to start or pause the currently playing song
    private void startStopSong() {
        if (media_JOO != null) {
            if (media_JOO.isPlaying()) {
                // Pause the song and flip the button start/stop
                buttonPlayPause_JOO.setBackgroundResource(R.drawable.baseline_play_circle_24);
                media_JOO.pause();
            }
            else {
                // Start playing the song and flip the button start/stop
                buttonPlayPause_JOO.setBackgroundResource(R.drawable.baseline_pause_circle_24);

                // Calls the song observer to observer the media progress
                observer_JOO.startObserver();
                media_JOO.start();
            }
        }
    }

    // Method to replay the current song from the beginning
    private void replaySong() {
        media_JOO.seekTo(0);
    }

    // Method to change the current position of the song by a specified duration
    private void changeMediaCurrentDuration(int duration_JOO) {
        media_JOO.seekTo(media_JOO.getCurrentPosition()+duration_JOO);
    }
}
