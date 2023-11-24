package com.m08.uservalidation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TriesDB extends SQLiteOpenHelper {
    Context context; // Store the application context
    private static final int database_version_JOO = 1; // Define the database version
    private static final String database_name_JOO = "tries.db"; // Define the database name
    private static final String table_combinations_JOO = "t_combinations"; // Define the table name

    public TriesDB(@Nullable Context context_JOO) {
        super(context_JOO, database_name_JOO, null, database_version_JOO);
        this.context = context_JOO; // Initialize the context for the database
    }

    @Override
    public void onCreate(SQLiteDatabase db_JOO) {
        // Create the database table with columns for login attempts
        db_JOO.execSQL("CREATE TABLE " + table_combinations_JOO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date DATE NOT NULL," +
                "user VARCHAR NOT NULL," +
                "password VARCHAR NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db_JOO, int oldVersion_JOO, int newVersion_JOO) {
        // Upgrade the database when the schema changes
        db_JOO.execSQL("DROP TABLE IF EXISTS " + table_combinations_JOO);
        onCreate(db_JOO);
    }

    // Insert a new login attempt into the database
    public void insertNewTry(String username_JOO, String password_JOO) {
        Date currentDate_JOO = new Date();
        SimpleDateFormat isoFormatter_JOO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        isoFormatter_JOO.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

        try {
            SQLiteDatabase db_JOO = getWritableDatabase();  // Use the existing instance

            ContentValues values_JOO = new ContentValues();
            values_JOO.put("date", isoFormatter_JOO.format(currentDate_JOO));
            values_JOO.put("user", username_JOO); // Change to "user" for the column name
            values_JOO.put("password", password_JOO);

            db_JOO.insert(table_combinations_JOO, null, values_JOO);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
        }
    }

    // Delete all stored tries in the database table
    public void deleteAllTries() {
        try {
            SQLiteDatabase db_JOO = getWritableDatabase();
            // Execute an SQL command to delete all rows from the specified table
            db_JOO.execSQL("DELETE FROM " + table_combinations_JOO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Retrieve all login attempts from the database and return them as User objects
    public ArrayList<User> getAllTries() {
        ArrayList<User> users_JOO = new ArrayList<>();

        try {
            SQLiteDatabase db_JOO = getReadableDatabase();
            Cursor cursor_JOO = db_JOO.query(table_combinations_JOO, null, null, null, null, null, null);

            while (cursor_JOO.moveToNext()) {
                String date_JOO = cursor_JOO.getString(cursor_JOO.getColumnIndex("date"));
                String user_JOO = cursor_JOO.getString(cursor_JOO.getColumnIndex("user"));
                String password_JOO = cursor_JOO.getString(cursor_JOO.getColumnIndex("password"));

                // Create a User object and add it to the ArrayList
                User newUser_JOO = new User(user_JOO, password_JOO, date_JOO);
                users_JOO.add(newUser_JOO);
            }

            cursor_JOO.close();

        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return users_JOO;
    }
}
