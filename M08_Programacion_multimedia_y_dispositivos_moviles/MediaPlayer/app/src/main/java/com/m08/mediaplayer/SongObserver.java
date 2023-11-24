package com.m08.mediaplayer;

import android.media.MediaPlayer;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SongObserver implements Runnable {
    // UI elements to be updated by the observer
    private final ProgressBar progressBar_JOO;
    private final TextView remainingTime_JOO;

    // MediaPlayer to observe
    private final MediaPlayer media_JOO;

    // Flag to control the observer loop
    private boolean playingSong_JOO = true;

    SongObserver(ProgressBar progressBar_JOO, MediaPlayer media_JOO, TextView remainingTime_JOO) {
        this.progressBar_JOO = progressBar_JOO;
        this.media_JOO = media_JOO;
        this.remainingTime_JOO = remainingTime_JOO;
    }

    public void run() {
        // Continuously update UI while the song is playing
        while(playingSong_JOO) {
            // Get the current position of the song being played
            int currentPlayed_JOO = media_JOO.getCurrentPosition();

            // Update the remaining time TextView on the UI thread
            remainingTime_JOO.post(() -> remainingTime_JOO.setText(Song.milisecondsToDuration(currentPlayed_JOO)));

            // Update the progress bar on the UI thread
            progressBar_JOO.post(() -> progressBar_JOO.setProgress(currentPlayed_JOO * 100 / media_JOO.getDuration()));

            // Pause for a short duration before the next update
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Creates and starts a new observer if its called
    protected void startObserver() {
        playingSong_JOO = true;
        new Thread(this).start();
    }

    // Stops the while loop
    protected void stopObserver() {
        playingSong_JOO = false;
    }
}
