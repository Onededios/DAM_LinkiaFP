package com.m08.galaxyevader.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.m08.galaxyevader.controllers.Screen;

import java.util.ArrayList;

public class Background {

    // Lists to manage background and foreground layers of stars
    private final ArrayList<Star> backgroundLayers_JOO = new ArrayList<>();
    private final ArrayList<Star> foregroundLayers_JOO = new ArrayList<>();
    private float minSpeed_JOO;

    public void setMinSpeed(float speed) {
        this.minSpeed_JOO = speed;
    }

    // Initialize background and foreground stars
    public void initializeStars() {
        initializeBackgroundStars();
        initializeForegroundStars();
    }

    // Initialize background stars
    private void initializeBackgroundStars() {
        float posX = 0;
        for (Bitmap bitmap : Screen.getInstance().getBackgroundStarsSprites()) {
            backgroundLayers_JOO.add(new Star(posX, bitmap));
            posX += Screen.getInstance().getScreenWidth();
        }
    }

    // Initialize foreground stars
    private void initializeForegroundStars() {
        float posX = 0;
        for (Bitmap bitmap : Screen.getInstance().getForegroundStartsSprites()) {
            foregroundLayers_JOO.add(new Star(posX, bitmap));
            posX += Screen.getInstance().getScreenWidth();
        }
    }

    public Background() {
    }

    // Draw the background with a black color
    private void drawBackground(Canvas canvas) {
        // Draw a black background
        Paint background = new Paint();
        background.setColor(Color.BLACK);
        background.setStyle(Paint.Style.FILL);
        canvas.drawRect(new RectF(0, 0, Screen.getInstance().getScreenWidth(), Screen.getInstance().getScreenHeight()), background);
    }

    // Method to draw the in-game background
    public void drawInGameBackground(Canvas canvas) {
        drawBackground(canvas);

        // Update and draw background stars
        for (Star backgroundStar : backgroundLayers_JOO) {
            backgroundStar.updateXLinearMovement(minSpeed_JOO);
            backgroundStar.drawStar(canvas);
        }

        // Update and draw foreground stars with a slightly faster speed
        float foregroundSpeed = minSpeed_JOO * 1.25F;
        for (Star foregroundStar : foregroundLayers_JOO) {
            foregroundStar.updateXLinearMovement(foregroundSpeed);
            foregroundStar.drawStar(canvas);
        }
    }

    // Draw the idle background with flashing stars
    public void drawIdleBackground(Canvas canvas) {
        drawBackground(canvas);
        backgroundLayers_JOO.get(0).drawFlashingStar(canvas);
        foregroundLayers_JOO.get(0).drawFlashingStar(canvas);
    }
}
