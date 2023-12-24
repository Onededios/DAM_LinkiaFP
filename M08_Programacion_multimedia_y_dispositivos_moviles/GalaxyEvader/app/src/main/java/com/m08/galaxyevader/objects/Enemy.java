package com.m08.galaxyevader.objects;

import android.graphics.Rect;

import com.m08.galaxyevader.controllers.Screen;
import com.m08.galaxyevader.graphics.SpriteSheet;

public class Enemy {
    // Size of the enemy ship as a percentage of the screen width
    public final float sizeInPercentOfScreen_JOO = 10;
    // Initial X position of the enemy ship
    public final float startingXPos_JOO = Screen.getInstance().getScreenWidth() - sizeInPercentOfScreen_JOO;
    // Ship object associated with the enemy
    private final Ship ship_JOO;
    // Area around the ship used for collision detection
    private final int area_JOO = (int) ((sizeInPercentOfScreen_JOO * Screen.getInstance().getScreenProportion()) / 100) * 2;
    // Initial points awarded for sorting the enemy
    private final int initialPointsForBeingSorted_JOO = 50;
    // Rectangular region on the sprite sheet representing the enemy ship
    private final Rect spriteSheetRect_JOO = new Rect(8, 25, 16, 32);
    // Points awarded for sorting the enemy (scaled with the level)
    private final int pointsForBeingSorted_JOO;
    // Range of Y positions where the enemy ship can appear
    private final float minYRange_JOO = (float) ((Screen.getInstance().getScreenHeight() * 2.5) / 100);
    private final float maxYRange_JOO = (float) (Screen.getInstance().getScreenHeight() - ((Screen.getInstance().getScreenHeight() * 2.5) / 100) - ((sizeInPercentOfScreen_JOO * Screen.getInstance().getScreenProportion()) / 100));
    // Speed of the enemy ship in the X direction
    public float speedInX_JOO;

    public Enemy(float speedInX_JOO, int level_JOO) {
        this.speedInX_JOO = speedInX_JOO;
        this.pointsForBeingSorted_JOO = initialPointsForBeingSorted_JOO * level_JOO;
        this.ship_JOO = new Ship(startingXPos_JOO, getDiffStartingYPos(), sizeInPercentOfScreen_JOO, speedInX_JOO, SpriteSheet.getTrimmedBitmap(Screen.getInstance().getShipSprites()[0], spriteSheetRect_JOO));
    }

    public Ship getShip() {
        return this.ship_JOO;
    }

    // Method to generate a random Y position for the enemy ship
    public float getRandomYPosition() {
        return (float) (area_JOO / 2 + Math.random() * ((Screen.getInstance().getScreenMaxY() - area_JOO / 2) - area_JOO / 2));
    }

    // Method to get a valid starting Y position for the enemy ship
    public float getDiffStartingYPos() {
        float newPos;
        do {
            newPos = getRandomYPosition();
        } while (isOutsideBoundsInY(newPos));

        return newPos;
    }

    public int getEnemyPoints() {
        return pointsForBeingSorted_JOO;
    }

    // Method to check if the ship is outside the vertical bounds of the screen
    private boolean isOutsideBoundsInY(float newPos_JOO) {
        float shipTop_JOO = newPos_JOO;
        float shipBot_JOO = newPos_JOO + ((sizeInPercentOfScreen_JOO * Screen.getInstance().getScreenHeight()) / 100);

        return shipTop_JOO < minYRange_JOO || shipBot_JOO > maxYRange_JOO;
    }

    // Method to check if the ship is outside the left bounds of the screen
    public boolean isOutsideScreenInX() {
        return getShip().getPosX() < -(area_JOO / 2);
    }

}
