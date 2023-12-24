package com.m08.galaxyevader.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

// SQLite helper class for managing database operations
public class SQLite extends SQLiteOpenHelper {
    private static final int database_version_JOO = 1; // Define the database version
    private static final String database_name_JOO = "galaxyevader.db"; // Define the database name
    private static final String table_plays_JOO = "t_plays"; // Define the table name

    public SQLite(@Nullable Context context_JOO) {
        super(context_JOO, database_name_JOO, null, database_version_JOO);
    }


    @Override
    public void onCreate(SQLiteDatabase db_JOO) {
        db_JOO.execSQL("CREATE TABLE " + table_plays_JOO +
                "(date DATE PRIMARY KEY, user VARCHAR, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db_JOO, int oldVersion_JOO, int newVersion_JOO) {
        db_JOO.execSQL("DROP TABLE IF EXISTS " + table_plays_JOO);
        onCreate(db_JOO);
    }

    // Insert a new play record into the database
    public void insertNewPlay(String username_JOO, int score_JOO) {
        Date currentDate_JOO = new Date();
        SimpleDateFormat isoFormatter_JOO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        isoFormatter_JOO.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        try {
            SQLiteDatabase db_JOO = getWritableDatabase();

            // Create ContentValues to store the play data
            ContentValues values_JOO = new ContentValues();
            values_JOO.put("date", isoFormatter_JOO.format(currentDate_JOO));
            values_JOO.put("user", username_JOO);
            values_JOO.put("score", score_JOO);

            // Insert the new play record into the database
            db_JOO.insert(table_plays_JOO, null, values_JOO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve all plays from the database sorted by score
    public ArrayList<JSONObject> getAllPlaysSortedByScore() {
        // ArrayList to store JSON objects representing plays
        ArrayList<JSONObject> json_JOO = new ArrayList<>();

        try {
            SQLiteDatabase db_JOO = getReadableDatabase();

            // Query the database to get all plays, sorted by score in descending order
            Cursor cursor_JOO = db_JOO.query(table_plays_JOO, null, null, null, null, null, "score DESC");

            // Iterate through the cursor to retrieve play data
            while (cursor_JOO.moveToNext()) {
                String date_JOO = cursor_JOO.getString(cursor_JOO.getColumnIndexOrThrow("date"));
                String username_JOO = cursor_JOO.getString(cursor_JOO.getColumnIndexOrThrow("user"));
                Integer score_JOO = cursor_JOO.getInt(cursor_JOO.getColumnIndexOrThrow("score"));

                // Create a JSON object for each play and add it to the ArrayList
                json_JOO.add(new JSONObject() {{
                    this.put("date", date_JOO);
                    this.put("username", username_JOO);
                    this.put("score", score_JOO);
                }});
            }
            cursor_JOO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json_JOO;
    }
}
