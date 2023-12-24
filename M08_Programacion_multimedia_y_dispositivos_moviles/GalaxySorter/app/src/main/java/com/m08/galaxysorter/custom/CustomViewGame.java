package com.m08.galaxyevader.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.m08.galaxyevader.R;
import com.m08.galaxyevader.controllers.Level;
import com.m08.galaxyevader.controllers.Screen;
import com.m08.galaxyevader.objects.Enemy;
import com.m08.galaxyevader.objects.Player;

// Custom view for the in-game display
public class CustomViewGame extends View {
    // Typeface for custom fonts
    private final Typeface customTypeface_JOO;
    private Player player_JOO;
    private Level level_JOO;

    public CustomViewGame(Context context_JOO) {
        super(context_JOO);
        customTypeface_JOO = ResourcesCompat.getFont(context_JOO, R.font.pixelfont);
    }

    public CustomViewGame(Context context_JOO, AttributeSet attrs_JOO) {
        super(context_JOO, attrs_JOO);
        customTypeface_JOO = ResourcesCompat.getFont(context_JOO, R.font.pixelfont);
    }

    public CustomViewGame(Context context_JOO, AttributeSet attrs_JOO, int defStyle_JOO) {
        super(context_JOO, attrs_JOO, defStyle_JOO);
        customTypeface_JOO = ResourcesCompat.getFont(context_JOO, R.font.pixelfont);
    }

    public void setPlayer(Player player_JOO) {
        this.player_JOO = player_JOO;
    }

    public void setLevel(Level level_JOO) {
        this.level_JOO = level_JOO;
    }

    // Handle touch events for controlling the player's ship
    @Override
    public boolean onTouchEvent(MotionEvent event_JOO) {
        if (!player_JOO.hasCrashed()) {
            switch (event_JOO.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    // Update the position of the player's ship based on touch input
                    player_JOO.getShip().setPosY(event_JOO.getY());
                    this.invalidate(); // Force a redraw
                    break;
            }
        }
        return true;
    }

    // Draw the game components on the canvas
    @Override
    protected void onDraw(@NonNull Canvas canvas_JOO) {
        super.onDraw(canvas_JOO);
        drawBackground(canvas_JOO);
        drawPlayer(canvas_JOO);
        drawEnemies(canvas_JOO);
        drawGameInfo(canvas_JOO);
    }


    private void drawBackground(Canvas canvas_JOO) {
        level_JOO.getBackground().drawInGameBackground(canvas_JOO);
    }

    public void drawPlayer(Canvas canvas_JOO) {
        player_JOO.getShip().drawShip(canvas_JOO, 90);
    }

    public void drawEnemies(Canvas canvas_JOO) {
        for (Enemy enemy_JOO : level_JOO.getEnemies()) {
            enemy_JOO.getShip().drawShip(canvas_JOO, 270);
        }
    }

    public void drawGameInfo(Canvas canvas_JOO) {
        Paint clockPaint_JOO = new Paint();
        clockPaint_JOO.setColor(Color.WHITE);
        clockPaint_JOO.setTextSize(Screen.getInstance().getScreenProportion() / 15);
        clockPaint_JOO.setTypeface(customTypeface_JOO);
        String textClock_JOO = level_JOO.getFormattedActualTime();

        float clockX_JOO = (Screen.getInstance().getScreenWidth() - clockPaint_JOO.measureText("mm:ss:SSS")) / 2;
        float clockY_JOO = Screen.getInstance().getScreenMinY() - clockPaint_JOO.getFontMetrics().top;

        canvas_JOO.drawText(textClock_JOO, clockX_JOO, clockY_JOO, clockPaint_JOO);

        Paint scorePaint_JOO = new Paint();
        scorePaint_JOO.setColor(Color.WHITE);
        scorePaint_JOO.setTextSize(Screen.getInstance().getScreenProportion() / 20);
        scorePaint_JOO.setTypeface(customTypeface_JOO);
        String scoreText_JOO = "" + player_JOO.getScore();

        canvas_JOO.drawText(scoreText_JOO, (clockX_JOO - (Screen.getInstance().getScreenWidth() * 0.225F)), clockY_JOO, scorePaint_JOO);

        Paint levelPaint_JOO = new Paint();
        levelPaint_JOO.setColor(Color.WHITE);
        levelPaint_JOO.setTextSize(Screen.getInstance().getScreenProportion() / 20);
        levelPaint_JOO.setTypeface(customTypeface_JOO);
        String levelText_JOO = "Level " + level_JOO.getActualLevel();

        canvas_JOO.drawText(levelText_JOO, (clockX_JOO + (Screen.getInstance().getScreenWidth() * 0.4F)), clockY_JOO, levelPaint_JOO);
    }


}