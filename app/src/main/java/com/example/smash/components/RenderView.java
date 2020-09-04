package com.example.smash.components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;

public class RenderView extends View {
    Random random = new Random();
    public static final int DEFAULT_COLOR_RANGE = 256;

    public RenderView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(
                this.random.nextInt(RenderView.DEFAULT_COLOR_RANGE),
                this.random.nextInt(RenderView.DEFAULT_COLOR_RANGE),
                this.random.nextInt(RenderView.DEFAULT_COLOR_RANGE)
        );
        this.invalidate();
    }
}
