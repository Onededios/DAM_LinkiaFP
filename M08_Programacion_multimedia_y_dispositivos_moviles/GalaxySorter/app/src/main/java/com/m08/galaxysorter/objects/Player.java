package com.m08.galaxyevader.objects;

import android.graphics.Rect;

import com.m08.galaxyevader.controllers.Screen;
import com.m08.galaxyevader.graphics.SpriteSheet;

public class Player {
    // Size of the player's ship as a percentage of the screen width
    public final float sizeInPercentOfScreen_JOO = 7.5F;
    public final float startingXPos_JOO = (Screen.getInstance().getScreenWidth() * 2) / 100;
    public final float startingYPos_JOO = (Screen.getInstance().getScreenHeight() / 2) - (int) (((sizeInPercentOfScreen_JOO * Screen.getInstance().getScreenProportion()) / 100) / 2);
    private final String name_JOO;
    // Rectangular region on the sprite sheet representing the player's ship
    private final Rect spriteSheetRect_JOO = new Rect(8, 0, 16, 8);
    private final Ship ship_JOO;
    private int score_JOO;
    private boolean crashed_JOO = false;

    public Player(String name_JOO) {
        this.name_JOO = name_JOO;
        this.ship_JOO = new Ship(startingXPos_JOO, startingYPos_JOO, sizeInPercentOfScreen_JOO, 0, SpriteSheet.getTrimmedBitmap(Screen.getInstance().getShipSprites()[0], spriteSheetRect_JOO));
    }

    public String getName() {
        return name_JOO;
    }

    public Ship getShip() {
        return ship_JOO;
    }

    public int getScore() {
        return score_JOO;
    }

    public boolean hasCrashed() {
        return crashed_JOO;
    }

    public void setCrashed() {
        crashed_JOO = true;
    }

    // Method to update the player's score by adding points
    public void updatePlayerScore(int points_JOO) {
        score_JOO += points_JOO;
    }


}
