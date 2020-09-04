package com.example.smash.components;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class BitmapRenderView extends View {
    public static final String DEFAULT_IMAGE_PATH = "images/";
    public static final String DEFAULT_IMAGE_SPEEDY = "speedy.png";

    Bitmap speedy565;
    Bitmap speedy4444;
    Rect dst = new Rect();
    InputStream inputStream = null;

    public BitmapRenderView(Context context) {
        super(context);

        try {
            AssetManager assetManager = context.getAssets();
            this.inputStream = assetManager.open(
            BitmapRenderView.DEFAULT_IMAGE_PATH +
                    BitmapRenderView.DEFAULT_IMAGE_SPEEDY
            );
            this.speedy565 = BitmapFactory.decodeStream(this.inputStream);
            this.inputStream.close();
            Log.d("BitmapTest",
                    "speedy.png rgb888 format: "+
                    this.speedy565.getConfig());

            this.inputStream = assetManager.open(
            BitmapRenderView.DEFAULT_IMAGE_PATH +
                    BitmapRenderView.DEFAULT_IMAGE_SPEEDY
            );
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            this.speedy4444 = BitmapFactory
                    .decodeStream(
                            this.inputStream,
                            null,
                            options);
            Log.d("BitmapTest",
                    "speedy.png rgb888 format: "+
                            this.speedy565.getConfig());
        } catch (IOException e) {
            Log.d("BitmapTest",
                    "Something went wrong: "+
                    e.getMessage());
        } finally {
            try {
                if (this.inputStream != null) {
                    this.inputStream.close();
                }
            } catch (IOException e) {
                Log.d("BitmapTest",
                        "Cannot close input stream: "+
                                e.getMessage());
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(0,0,0);
        int canvasWidth = canvas.getWidth() - 1;
//        Log.d("canvas#","getWidth = "+canvasWidth);
        this.dst.set(
                0,
                0,
                canvasWidth,
                canvasWidth);
        canvas.drawBitmap(this.speedy565, null, this.dst, null);
//        canvas.drawBitmap(this.speedy4444, 100, 100, null);
        invalidate();
    }
}
