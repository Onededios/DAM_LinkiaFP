package com.m08.galaxyevader.controllers;

import android.content.ContentResolver;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.m08.galaxyevader.R;

import java.io.IOException;

// Singleton class for handling sound effects and background music
public class Sound {
    // Singleton instance
    private static Sound instance_JOO;

    // Media player for handling sound playback
    private MediaPlayer mediaPlayer_JOO;

    // Application context for resource retrieval
    private Context context_JOO;

    // Private constructor to prevent external instantiation
    public Sound() {
    }

    // Initialize the Sound instance with the application context
    public void initialize(Context context) {
        this.context_JOO = context;
    }

    // Get the singleton instance of the Sound class
    public static Sound getInstance() {
        if (instance_JOO == null) {
            instance_JOO = new Sound();
        }
        return instance_JOO;
    }

    // Get the URI for a sound resource
    private Uri getSoundUri(int soundId) {
        return Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE +
                        "://" + context_JOO.getPackageName() +
                        "/" + soundId);
    }

    // Set and play a background song
    private void setSong(int songId_JOO) throws IOException {
        if (mediaPlayer_JOO != null) {
            // Stop, reset, and release the existing media player
            mediaPlayer_JOO.stop();
            mediaPlayer_JOO.reset();
            mediaPlayer_JOO.release();
        }
        mediaPlayer_JOO = new MediaPlayer();
        mediaPlayer_JOO.setDataSource(context_JOO, getSoundUri(songId_JOO));
        mediaPlayer_JOO.prepare();
        mediaPlayer_JOO.start();

        // Set a completion listener to restart the song when it completes
        mediaPlayer_JOO.setOnCompletionListener(mediaPlayer -> {
            try {
                // Recursive call to restart the song
                setSong(songId_JOO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    // Play a short sound effect
    private void playSound(int soundId_JOO) {
        // Create a new and different media player for the specified sound resource
        MediaPlayer soundMedia_JOO = MediaPlayer.create(context_JOO, getSoundUri(soundId_JOO));
        soundMedia_JOO.start();
    }

    public void setSongMain() throws IOException {
        setSong(R.raw.main);
    }

    public void setSongInGame() throws IOException {
        setSong(R.raw.ingame);
    }

    public void setSongEnd() throws IOException {
        setSong(R.raw.gameover);
    }

    public void playSoundExplosion() throws IOException {
        playSound(R.raw.explosion);
    }
}
