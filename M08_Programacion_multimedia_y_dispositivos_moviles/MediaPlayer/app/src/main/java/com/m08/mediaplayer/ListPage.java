package com.m08.mediaplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

// Activity to display a list of songs
public class ListPage extends AppCompatActivity{
    // Unique code for requesting file read permissions
    private static final int REQUEST_CODE_PERMISSION = 123;

    // UI components
    private ListView listViewSongs_JOO;
    private Button buttonRecharge_JOO;

    // Method to initialize UI components
    private void initView() {
        listViewSongs_JOO = findViewById(R.id.listViewSongs);
        buttonRecharge_JOO = findViewById(R.id.buttonRecharge);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState_JOO) {
        super.onCreate(savedInstanceState_JOO);
        setContentView(R.layout.list); // Set the content view to the "list" layout
        initView(); // Initialize the layout elements

        // Check and request file read permissions
        checkAndRequestPermissions();

        // Load and display songs
        rechargeSongs(listViewSongs_JOO);

        // Listener to recharge all songs
        buttonRecharge_JOO.setOnClickListener(view_JOO -> rechargeSongs(listViewSongs_JOO));

        // Listener for item on listview to open the media player page
        listViewSongs_JOO.setOnItemClickListener((parent_JOO, view_JOO, position_JOO, id_JOO) -> {
            Song.setSelectedSong((Song) parent_JOO.getItemAtPosition(position_JOO));
            startActivity(new Intent(this, PlayerPage.class));
            finish();
        });
    }

    @Override
    // Called when the result of a permission request is received
    public void onRequestPermissionsResult(int requestCode_JOO, @NonNull String[] permissions_JOO, @NonNull int[] grantResults_JOO) {
        super.onRequestPermissionsResult(requestCode_JOO, permissions_JOO, grantResults_JOO);
        if (requestCode_JOO == REQUEST_CODE_PERMISSION) {
            if (grantResults_JOO.length > 0 && grantResults_JOO[0] == PackageManager.PERMISSION_GRANTED) {
                // If permission is granted, recharge and display songs
                rechargeSongs(listViewSongs_JOO);
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "You must accept file read permissions to load songs", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to check and request file read permissions
    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request file read permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_PERMISSION);
        }
    }

    // Method to recharge and display songs in the list view
    private void rechargeSongs(ListView Songs_JOO) {
        // Load songs from the downloads folder
        FileUtils.getAllDownloadsSongs();
        int songsQTY_JOO = Song.getSongs().size();

        // Show a toast message based on the number of loaded songs
        Toast.makeText(this, songsQTY_JOO > 0 ? "Loaded "+songsQTY_JOO+" .mp3 songs from downloads folder" : "Cannot find any mp3 songs in your downloads folder", Toast.LENGTH_SHORT).show();

        // Create and set the adapter for the list view
        SongAdapter songAdapter_JOO = new SongAdapter(this);
        Songs_JOO.setAdapter(songAdapter_JOO);
    }
}
