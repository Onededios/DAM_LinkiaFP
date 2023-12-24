package com.m08.galaxyevader.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.m08.galaxyevader.objects.Background;

public class CustomViewIdle extends View {
    Background background;

    public void setBackground(Background background) {
        this.background = background;
    }

    public CustomViewIdle(Context context) {
        super(context);
    }

    public CustomViewIdle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewIdle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        background.drawIdleBackground(canvas);
    }
}
