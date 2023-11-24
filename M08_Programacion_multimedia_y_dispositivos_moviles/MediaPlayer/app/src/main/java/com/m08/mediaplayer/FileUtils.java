package com.m08.mediaplayer;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

public class FileUtils {
    // Array of allowed file extensions
    private static final String[] allowedExt = new String[]{"mp3"};

    // Method to get all files in the Downloads directory and create songs from them
    protected static void getAllDownloadsSongs() {
        // Clear the existing list of saved songs
        Song.dropSavedSongs();

        // Get the absolute path of the Downloads directory
        String downloadsPath_JOO = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

        // Create a File object for the Downloads directory
        File downloadsDirectory_JOO = new File(downloadsPath_JOO);

        // Check if external storage is mounted and Downloads directory exists
        if (isExternalStorageMounted() && downloadsDirectory_JOO.exists() && downloadsDirectory_JOO.isDirectory()) {
            // Get the list of files in the Downloads directory
            File[] files_JOO = downloadsDirectory_JOO.listFiles();
            if (files_JOO != null) {
                // Iterate through each file in the Downloads directory
                for (File file_JOO : files_JOO) {
                    // Check if the file has an allowed extension to create a song from the media file and add it to the list of songs
                    if (isSongFileExtensionAllowed(file_JOO))
                        Song.getSongs().add(createSongFromMediaFile(file_JOO));
                }
            }
        }
    }

    // Method to check if external storage is mounted
    private static boolean isExternalStorageMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // Method to check if the file extension is allowed
    private static boolean isSongFileExtensionAllowed(File file_JOO) {
        String fileName_JOO = file_JOO.getName();
        int lastDotIndex_JOO = fileName_JOO.lastIndexOf(".");

        if (lastDotIndex_JOO > 0) {
            // Get the file extension
            String fileExtension_JOO = fileName_JOO.substring(lastDotIndex_JOO + 1);

            // Check if the file extension is in the allowed array
            for (String allowedExtension_JOO : allowedExt) {
                if (fileExtension_JOO.equalsIgnoreCase(allowedExtension_JOO)) return true;
            }
        }

        return false;
    }

    // Method to create a Song object from a media file
    private static Song createSongFromMediaFile(File file_JOO) {
        try {
            // Create a MediaMetadataRetriever and set its data source to the file
            MediaMetadataRetriever retriever_JOO = new MediaMetadataRetriever();
            retriever_JOO.setDataSource(file_JOO.getAbsolutePath());

            // Extract metadata from the file
            String title_JOO = retriever_JOO.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String artist_JOO = retriever_JOO.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String album_JOO = retriever_JOO.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            int duration_JOO = Integer.parseInt(Objects.requireNonNull(retriever_JOO.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)));
            byte[] image_JOO = retriever_JOO.getEmbeddedPicture();

            // Create a Song object and return it
            return new Song(
                    title_JOO != null ? title_JOO : file_JOO.getName(),
                    artist_JOO != null ? artist_JOO : "undefined",
                    album_JOO != null ? album_JOO : "undefined",
                    duration_JOO,
                    image_JOO,
                    Uri.parse(file_JOO.getAbsolutePath())
            );
        } catch (Exception e) {
            // Handle the case where reading file metadata fails
            e.printStackTrace();
            return null;
        }
    }
}
