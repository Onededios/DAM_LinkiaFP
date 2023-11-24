package com.m08.uservalidation;

import java.util.ArrayList;

public class User {
    private String username_JOO;
    private String password_JOO;
    private String date_JOO;

    User(String username_JOO, String password_JOO) {
        this.username_JOO = username_JOO;
        this.password_JOO = password_JOO;
    }

    User(String username_JOO, String password_JOO, String date_JOO) {
        this.username_JOO = username_JOO;
        this.password_JOO = password_JOO;
        this.date_JOO = date_JOO;
    }

    // Getter for username
    public String getUsername_JOO() {
        return username_JOO;
    }

    // Getter for password
    public String getPassword_JOO() {
        return password_JOO;
    }

    // Getter for date
    public String getDate_JOO() {
        return date_JOO;
    }

    // Parse a user string into a User object
    private static User parseUserFromString(String userString_JOO) {
        String[] parts_JOO = userString_JOO.split(";");
        if (parts_JOO.length == 2) return new User(parts_JOO[0], parts_JOO[1]);
        else if (parts_JOO.length == 3) return new User(parts_JOO[0], parts_JOO[1], parts_JOO[2]);
        return null; // Invalid format
    }

    // Convert a list of user strings into a list of User objects
    public static ArrayList<User> getStringUsersToUserList(ArrayList<String> userStrings_JOO) {
        ArrayList<User> users_JOO = new ArrayList<>();
        for (String userString_JOO : userStrings_JOO) {
            User user_JOO = parseUserFromString(userString_JOO);
            if (user_JOO != null) users_JOO.add(user_JOO);
        }
        return users_JOO;
    }
}
