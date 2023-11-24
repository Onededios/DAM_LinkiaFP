package com.m08.mediaplayer;

import android.annotation.SuppressLint;
import android.net.Uri;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Song {
    private final int id_JOO; // Unique identifier for each song
    // Song attributes
    private final String name_JOO;
    private final String author_JOO;
    private final String album_JOO;
    private final int duration_JOO;
    private final byte[] albumImage_JOO;
    private final Uri uri_JOO;

    // List to store all saved songs
    private static final ArrayList<Song> savedSongs_JOO = new ArrayList<>();

    // Currently selected song
    private static Song selectedSong_JOO = null;

    protected Song(String name_JOO, String author_JOO, String album_JOO, int duration_JOO, byte[] albumImage_JOO, Uri uri_JOO) {
        // Generate a unique ID for the song based on the last saved song's ID
        this.id_JOO = getLastSavedSongId()+1;
        this.name_JOO = name_JOO;
        this.author_JOO = author_JOO;
        this.album_JOO = album_JOO;
        this.duration_JOO = duration_JOO;
        this.albumImage_JOO = albumImage_JOO;
        this.uri_JOO = uri_JOO;
    }

    // Getter methods for song attributes
    protected Uri getUri() {return uri_JOO;}
    protected int getId() {return id_JOO;}
    protected String getName() {
        return name_JOO;
    }
    protected String getAlbum() {
        return album_JOO;
    }
    protected String getAuthor() {
        return author_JOO;
    }
    protected String getDuration() {
        return Song.milisecondsToDuration(duration_JOO);
    }
    protected byte[] getAlbumImage() {return albumImage_JOO;}

    // Method to clear the list of saved songs
    protected static void dropSavedSongs() {
        savedSongs_JOO.clear();
    }

    // Method to get the ID of the last saved song
    protected static int getLastSavedSongId() {
        return savedSongs_JOO.size() > 0 ? savedSongs_JOO.get(savedSongs_JOO.size()-1).getId() : -1;
    }

    protected static void setSelectedSong(Song song_JOO) {
        selectedSong_JOO = song_JOO;}
    protected static Song getSelectedSong() {return selectedSong_JOO;}
    protected static ArrayList<Song> getSongs() {
        return savedSongs_JOO;
    }
    protected static void setNextSong() {
        int songId_JOO = selectedSong_JOO.getId();
        setSelectedSong(savedSongs_JOO.size()-1 < (songId_JOO+1) ? savedSongs_JOO.get(0) : savedSongs_JOO.get((songId_JOO+1)));
    }

    protected static void setPrevSong() {
        int songId_JOO = selectedSong_JOO.getId();
        setSelectedSong(0 > songId_JOO-1 ? savedSongs_JOO.get(savedSongs_JOO.size()-1) : savedSongs_JOO.get(songId_JOO-1));
    }

    // Method to convert milliseconds to a formatted duration string
    @SuppressLint("DefaultLocale")
    protected static String milisecondsToDuration(int milis_JOO) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milis_JOO),
                TimeUnit.MILLISECONDS.toMinutes(milis_JOO) % 60,
                TimeUnit.MILLISECONDS.toSeconds(milis_JOO) % 60);
    }
}
