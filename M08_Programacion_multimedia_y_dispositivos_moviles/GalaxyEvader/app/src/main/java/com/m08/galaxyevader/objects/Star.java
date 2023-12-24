package com.m08.galaxyevader.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.m08.galaxyevader.controllers.Screen;

public class Star {
    // Bitmap representing the star
    private final Bitmap bitmap_JOO;

    // Paint object for drawing the star
    private Paint star_JOO = new Paint();
    // Current X position of the star
    private float posX_JOO;
    private int alphaValue_JOO = 200;

    Star(float startingXPos_JOO, Bitmap bitmap_JOO) {
        this.posX_JOO = startingXPos_JOO;
        this.bitmap_JOO = bitmap_JOO;
    }

    // Method to draw the star on the canvas with a specified alpha (transparency)
    public void drawStar(Canvas canvas_JOO) {
        // Configure paint for drawing the star
        star_JOO.setFilterBitmap(false);
        star_JOO.setAlpha(alphaValue_JOO);

        // Draw the star bitmap on the canvas with transparency and across the screen width
        canvas_JOO.drawBitmap(bitmap_JOO, new Rect(0, 0, bitmap_JOO.getWidth(), bitmap_JOO.getHeight()), new RectF(posX_JOO, 0, Screen.getInstance().getScreenWidth() + posX_JOO, Screen.getInstance().getScreenHeight()), star_JOO);
    }

    public void drawFlashingStar(Canvas canvas_JOO) {
        star_JOO.setFilterBitmap(false);
        alphaValue_JOO = (int) (Math.random() * 151) + 50;
        star_JOO.setAlpha(alphaValue_JOO);
        canvas_JOO.drawBitmap(bitmap_JOO, new Rect(0, 0, bitmap_JOO.getWidth(), bitmap_JOO.getHeight()), new RectF(posX_JOO, 0, Screen.getInstance().getScreenWidth() + posX_JOO, Screen.getInstance().getScreenHeight()), star_JOO);
    }

    // Method to update the X position of the star based on linear movement and screen wrap-around
    public void updateXLinearMovement(float speed_JOO) {
        // If the star goes off the left side of the screen, wrap it around to the right side
        if (posX_JOO < -Screen.getInstance().getScreenWidth())
            posX_JOO = Screen.getInstance().getScreenWidth();

        // Update the X position based on the specified speed
        posX_JOO -= speed_JOO;
    }
}
