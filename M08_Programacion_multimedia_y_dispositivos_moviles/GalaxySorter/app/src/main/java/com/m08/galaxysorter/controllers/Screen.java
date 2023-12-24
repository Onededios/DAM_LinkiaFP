package com.m08.galaxyevader.controllers;

import android.graphics.Bitmap;

// Singleton class representing the screen in the game
public class Screen {
    // Singleton instance
    private static Screen instance_JOO;
    // Margin around the screen in percentage
    public final float screenMarginInPercent_JOO = 2.5F;

    // Screen properties
    public float screenWidth_JOO;
    public float screenHeight_JOO;
    public float screenProportion_JOO;
    public float screenMinY_JOO;
    public float screenMaxY_JOO;
    public float screenMinX_JOO;
    public float screenMaxX_JOO;

    // Arrays for storing sprites
    public Bitmap[] shipSprites_JOO;
    public Bitmap[] backgroundStarsSprites_JOO;
    public Bitmap[] foregroundStartsSprites_JOO;

    // Private constructor to prevent external instantiation
    private Screen() {
    }

    // Initialize screen properties and sprites
    public void initialize(float screenWidth_JOO, float screenHeight_JOO, Bitmap[] shipSprites_JOO, Bitmap[] backgroundStarsSprites_JOO, Bitmap[] foregroundStartsSprites_JOO) {
        this.screenWidth_JOO = screenWidth_JOO;
        this.screenHeight_JOO = screenHeight_JOO;
        screenProportion_JOO = (float) Math.sqrt(screenWidth_JOO * screenHeight_JOO);
        screenMinY_JOO = (this.screenHeight_JOO * screenMarginInPercent_JOO) / 100;
        screenMaxY_JOO = this.screenHeight_JOO - screenMinY_JOO;
        screenMinX_JOO = (this.screenWidth_JOO * screenMarginInPercent_JOO) / 100;
        screenMaxX_JOO = this.screenWidth_JOO - screenMinX_JOO;
        this.shipSprites_JOO = shipSprites_JOO;
        this.backgroundStarsSprites_JOO = backgroundStarsSprites_JOO;
        this.foregroundStartsSprites_JOO = foregroundStartsSprites_JOO;
    }

    // Get the singleton instance of the Screen
    public static Screen getInstance() {
        if (instance_JOO == null) {
            instance_JOO = new Screen();
        }
        return instance_JOO;
    }

    public float getScreenMarginInPercent() {
        return screenMarginInPercent_JOO;
    }

    public float getScreenWidth() {
        return screenWidth_JOO;
    }

    public float getScreenHeight() {
        return screenHeight_JOO;
    }

    public float getScreenProportion() {
        return screenProportion_JOO;
    }

    public float getScreenMinY() {
        return screenMinY_JOO;
    }

    public float getScreenMaxY() {
        return screenMaxY_JOO;
    }

    public float getScreenMinX() {
        return screenMinX_JOO;
    }

    public float getScreenMaxX() {
        return screenMaxX_JOO;
    }

    public Bitmap[] getShipSprites() {
        return shipSprites_JOO;
    }

    public Bitmap[] getBackgroundStarsSprites() {
        return backgroundStarsSprites_JOO;
    }

    public Bitmap[] getForegroundStartsSprites() {
        return foregroundStartsSprites_JOO;
    }
}
