package com.m08.galaxyevader.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    // Method to get a Bitmap from a resource ID
    public static Bitmap getBitmap(Context context_JOO, int id_JOO) {
        BitmapFactory.Options bitmapOptions_JOO = new BitmapFactory.Options();
        bitmapOptions_JOO.inScaled = false; // Disable scaling for better image quality
        return BitmapFactory.decodeResource(context_JOO.getResources(), id_JOO, bitmapOptions_JOO);
    }

    // Method to get a trimmed version of a Bitmap based on a specified Rect
    public static Bitmap getTrimmedBitmap(Bitmap bitmap_JOO, Rect rectToTrim_JOO) {
        // Create a new Bitmap with the specified region of the original bitmap
        return Bitmap.createBitmap(bitmap_JOO, rectToTrim_JOO.left, rectToTrim_JOO.top, rectToTrim_JOO.width(), rectToTrim_JOO.height());
    }
}
