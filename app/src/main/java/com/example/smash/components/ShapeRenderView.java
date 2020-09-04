package com.example.smash.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class ShapeRenderView extends RenderView {
    Paint paint;

    public ShapeRenderView(Context context) {
        super(context);
        this.paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRGB(
            RenderView.DEFAULT_COLOR_RANGE,
            RenderView.DEFAULT_COLOR_RANGE,
            RenderView.DEFAULT_COLOR_RANGE
        );

        this.paint.setColor(Color.RED);
        canvas.drawLine(
                0,
                0,
                canvas.getWidth() -1,
                canvas.getHeight() -1,
                this.paint
        );

        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(0xff00ff00);
        canvas.drawCircle(
                canvas.getWidth() / 2,
                canvas.getHeight() / 2,
                canvas.getWidth() / 2,
                this.paint
        );

        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(0x770000ff);
        canvas.drawRect(
                100,
                100,
                200,
                200,
                this.paint
        );

        this.invalidate();
    }

}
