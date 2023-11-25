package com.m08.mediaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class SongAdapter extends ArrayAdapter<Song> {
    private final Context context_JOO;
    SongAdapter(Context context_JOO) {
        super(context_JOO, 0, Song.getSongs());
        this.context_JOO = context_JOO;
    }

    // Override the getView method to customize the appearance of each item in the list
    @NonNull
    @Override
    public View getView(int position_JOO, View convertView_JOO, @NonNull ViewGroup parent_JOO) {
        // Check if a recycled view is available
        if (convertView_JOO == null) {
            convertView_JOO = LayoutInflater.from(context_JOO).inflate(R.layout.song_list, parent_JOO, false);
        }

        // Get references to UI elements within the list item layout
        ImageView imageViewPortrait_JOO = convertView_JOO.findViewById(R.id.imageViewPortrait);
        TextView nameTextView_JOO = convertView_JOO.findViewById(R.id.TextViewName);
        TextView authorTextView_JOO = convertView_JOO.findViewById(R.id.TextViewAuthor);
        TextView albumTextView_JOO = convertView_JOO.findViewById(R.id.TextViewAlbum);
        TextView durationTextView_JOO = convertView_JOO.findViewById(R.id.TextViewDuration);

        // Get the current Song object based on its position in the list
        Song song_JOO = getItem(position_JOO);

        // Update UI elements with information from the current Song object
        if (song_JOO != null) {
            setDecodedImage(song_JOO.getAlbumImage(), imageViewPortrait_JOO);
            nameTextView_JOO.setText(song_JOO.getName());
            authorTextView_JOO.setText(song_JOO.getAuthor());
            albumTextView_JOO.setText(song_JOO.getAlbum());
            durationTextView_JOO.setText(song_JOO.getDuration());
        }

        return convertView_JOO; // Return the updated view for display
    }

    // Method to set the album image for an ImageView in case of receiving null
    protected static void setDecodedImage(byte[] albumImage_JOO, ImageView imageView) {
        if (albumImage_JOO != null) {
            // Decode the byte array into a Bitmap and set it on the ImageView
            Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage_JOO, 0, albumImage_JOO.length);
            imageView.setImageBitmap(bitmap);
        } else {
            // Set default image in the selected song has no album art
            imageView.setImageResource(R.drawable.baseline_question_mark_24);
        }
    }
}
