package com.example.smash.framework.impl;

import android.graphics.Bitmap;

import com.example.smash.framework.core.Graphics;

public class Pixmap implements com.example.smash.framework.core.Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;

    public Pixmap (
            Bitmap bitmap,
            Graphics.PixmapFormat format
    ) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return this.bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return this.bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return this.format;
    }

    @Override
    public void dispose() {
        this.bitmap.recycle();
    }
}
