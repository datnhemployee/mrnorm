package com.example.smash.framework.core;
import com.example.smash.framework.core.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();
    public int getHeight();

    public PixmapFormat getFormat();

    public  void dispose();
}
