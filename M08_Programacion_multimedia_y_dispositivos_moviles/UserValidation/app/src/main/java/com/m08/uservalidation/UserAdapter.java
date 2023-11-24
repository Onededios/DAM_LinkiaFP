package com.m08.uservalidation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context_JOO;
    private boolean useDateLayout_JOO;

    UserAdapter(Context context_JOO, ArrayList<User> userList_JOO, boolean useDateLayout_JOO) {
        super(context_JOO, 0, userList_JOO);
        this.context_JOO = context_JOO;
        this.useDateLayout_JOO = useDateLayout_JOO;
    }

    @Override
    public View getView(int position_JOO, View convertView_JOO, ViewGroup parent_JOO) {
        if (convertView_JOO == null) {
            // Determine the layout to inflate based on the useDateLayout flag
            int layoutRes = useDateLayout_JOO ? R.layout.userdate_list : R.layout.user_list;
            convertView_JOO = LayoutInflater.from(context_JOO).inflate(layoutRes, parent_JOO, false);
        }

        TextView usernameTextView_JOO = convertView_JOO.findViewById(R.id.TextViewUsername);
        TextView passwordTextView_JOO = convertView_JOO.findViewById(R.id.TextViewPassword);

        User user_JOO = getItem(position_JOO);

        if (user_JOO != null) {
            // Set the username and password text views based on user data
            usernameTextView_JOO.setText(user_JOO.getUsername_JOO());
            passwordTextView_JOO.setText(user_JOO.getPassword_JOO());

            if (useDateLayout_JOO) {
                // If the date layout is used, set the date text view
                TextView dateTextView_JOO = convertView_JOO.findViewById(R.id.TextViewDate);
                dateTextView_JOO.setText(user_JOO.getDate_JOO());
            }
        }

        return convertView_JOO;
    }
}
