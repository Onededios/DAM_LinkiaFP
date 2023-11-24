package com.m08.uservalidation;

import android.view.View;

public class Toast {
    // Display a Toast message with an error message
    protected static void appearToast(View view_JOO, String error_JOO) {
        android.widget.Toast.makeText(view_JOO.getContext(), toastTextError(error_JOO), android.widget.Toast.LENGTH_LONG).show();
    }

    // Generate the appropriate error message for the given error code
    private static String toastTextError(String error_JOO) {
        switch (error_JOO) {
            case "connection_error":
                return "Failed to connect to server";
            case "request_failed":
                return "Failed to send the request";
            case "parsing_error":
                return "Failed to connect with the database";
            case "user_not_introduced":
                return "You must enter a username";
            case "password_not_introduced":
                return "You must enter a password";
            case "invalid":
                return "Entered an invalid login user or password";
            case "ko":
                return "Incorrect login";
            default:
                return "Not controlled error";
        }
    }
}
