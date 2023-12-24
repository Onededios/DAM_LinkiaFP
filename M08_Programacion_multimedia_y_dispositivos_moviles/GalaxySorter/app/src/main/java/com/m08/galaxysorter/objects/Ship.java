package com.m08.galaxyevader.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.m08.galaxyevader.controllers.Screen;

public class Ship {
    // Size of the ship as a percentage of the screen width
    private final float size_JOO;
    // Speed of the ship
    private final float speed_JOO;
    // Bitmap representing the ship
    private final Bitmap bitmap_JOO;
    // Current X and Y positions of the ship
    private float posX_JOO;
    private float posY_JOO;
    // Rectangular region defining the ship's position and size
    private RectF shipRect_JOO;

    public Ship(float posX_JOO, float posY_JOO, float size_JOO, float speed_JOO, Bitmap bitmap_JOO) {
        this.posX_JOO = posX_JOO;
        this.posY_JOO = posY_JOO;
        this.size_JOO = size_JOO;
        this.speed_JOO = speed_JOO;
        this.bitmap_JOO = bitmap_JOO;
    }

    // Method to draw the ship on the canvas with a specified rotation angle
    public void drawShip(Canvas canvas_JOO, float rotation_JOO) {
        // Paint object for drawing the ship
        Paint ship_JOO = new Paint();
        ship_JOO.setFilterBitmap(false);

        // Rectangular region defining the ship's position and size
        shipRect_JOO = new RectF(posX_JOO, posY_JOO, posX_JOO + ((size_JOO * Screen.getInstance().getScreenProportion()) / 100), posY_JOO + ((size_JOO * Screen.getInstance().getScreenProportion()) / 100));

        // Apply rotation to the ship's bitmap
        Matrix matrix_JOO = new Matrix();
        matrix_JOO.postRotate(rotation_JOO);
        Bitmap rotated_JOO = Bitmap.createBitmap(bitmap_JOO, 0, 0, bitmap_JOO.getWidth(), bitmap_JOO.getHeight(), matrix_JOO, true);

        // Draw the rotated ship bitmap on the canvas
        canvas_JOO.drawBitmap(rotated_JOO, new Rect(0, 0, rotated_JOO.getWidth(), rotated_JOO.getHeight()), shipRect_JOO, ship_JOO);
    }

    public float getPosX() {
        return posX_JOO;
    }

    // Method to update the X position of the ship based on its speed
    public void setPosX() {
        this.posX_JOO -= speed_JOO;
    }

    // Setter method for the Y position of the ship, with bounds checking
    public void setPosY(float posY_JOO) {
        if (posY_JOO > Screen.getInstance().getScreenMinY() && posY_JOO < (Screen.getInstance().getScreenMaxY() - ((size_JOO * Screen.getInstance().getScreenProportion()) / 100))) {
            this.posY_JOO = posY_JOO;
        }
    }

    // Method to check for collisions with another RectF (assumed to be another object's position and size)
    public boolean shipCollisions(RectF ship_JOO) {
        if (shipRect_JOO == null) return false;
        return shipRect_JOO.intersect(ship_JOO);
    }

    public RectF getShipRect() {
        return shipRect_JOO;
    }
}
