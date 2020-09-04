package com.example.smash.framework.impl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.smash.framework.core.Pixmap;

import java.io.IOException;
import java.io.InputStream;

public class Graphics implements com.example.smash.framework.core.Graphics {
    AssetManager assetManager;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public Graphics(
            AssetManager assetManager,
            Bitmap frameBuffer
    ) {
        this.assetManager = assetManager;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(this.frameBuffer);
        this.paint = new Paint();
    }
    @Override
    public Pixmap newPixmap(
            String fileName,
            PixmapFormat format
    ) {
        Bitmap.Config config = null;
        if (format == PixmapFormat.RGB565) {
            config = Bitmap.Config.RGB_565;
        } else {
            config = Bitmap.Config.ARGB_8888;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;

        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            inputStream = this.assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap == null) {
                throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }

        if (bitmap.getConfig() == Bitmap.Config.RGB_565) {
            format = PixmapFormat.RGB565;
        } else {
            format = PixmapFormat.ARGB8888;
        }

        return new com.example.smash.framework.impl.Pixmap(
                bitmap,
                format
        );
    }

    @Override
    public void clear(int color) {
        this.canvas.drawRGB(
                (color & 0xff0000) >> 16,
                (color & 0xff00) >> 8,
                (color & 0xff)
        );
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        this.paint.setColor(color);
        this.canvas.drawPoint(x,y,this.paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        this.paint.setColor(color);
        this.canvas.drawLine(x, y, x2, y2, this.paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawRect(x,y,x + width - 1,y + width -1,this.paint);
    }

    @Override
    public void drawPixmap(
            Pixmap pixmap,
            int x,
            int y,
            int srcX,
            int srcY,
            int srcWidth,
            int srcHeight
    ) {
        this.srcRect.left = srcX;
        this.srcRect.top = srcY;
        this.srcRect.right = srcX + srcWidth - 1;
        this.srcRect.bottom = srcY + srcHeight - 1;

        this.dstRect.left = x;
        this.dstRect.top = y;
        this.dstRect.right = x + srcWidth  - 1;
        this.dstRect.bottom = y + srcHeight - 1;

        this.canvas.drawBitmap(
                ((com.example.smash.framework.impl.Pixmap) pixmap).bitmap,
                srcRect,
                dstRect,
                null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        this.canvas.drawBitmap(
                ((com.example.smash.framework.impl.Pixmap) pixmap).bitmap,
                x,
                y,
                null);
    }

    @Override
    public int getWidth() {
        return this.frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return this.frameBuffer.getHeight();
    }
}
