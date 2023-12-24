package com.m08.galaxyevader.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.m08.galaxyevader.R;
import com.m08.galaxyevader.data.SQLite;

import org.json.JSONObject;

public class CustomAdapterPlay extends ArrayAdapter<JSONObject> {
    private Context context_JOO;
    private TextView textView_username_JOO;
    private TextView textView_score_JOO;
    private TextView textView_date_JOO;
    public CustomAdapterPlay(Context context_JOO) {
        super(context_JOO, 0, new SQLite(context_JOO).getAllPlaysSortedByScore());
        this.context_JOO = context_JOO;
    }

    @Override
    public View getView(int position_JOO, View convertView_JOO, ViewGroup parent_JOO) {
        // Inflate the view for each item in the Adapter
        if (convertView_JOO == null) {
            convertView_JOO = LayoutInflater.from(context_JOO).inflate(R.layout.laderboard_entry, parent_JOO, false);
        }

        textView_username_JOO = convertView_JOO.findViewById(R.id.textView_username);
        textView_score_JOO = convertView_JOO.findViewById(R.id.textView_score);
        textView_date_JOO = convertView_JOO.findViewById(R.id.textView_date);

        JSONObject currentPlay_JOO = getItem(position_JOO);

        if (currentPlay_JOO != null) {
            textView_username_JOO.setText(currentPlay_JOO.optString("username"));
            textView_score_JOO.setText(""+currentPlay_JOO.optInt("score"));
            textView_date_JOO.setText(currentPlay_JOO.optString("date").split("T")[0]);
        }
        return convertView_JOO;
    }
}
